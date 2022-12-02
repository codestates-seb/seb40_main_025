import axios, { AxiosError, AxiosRequestConfig } from 'axios';
import { setStoredToken, getStoredToken } from 'Intro/hooks/tokenStorage';

let ACCESS_TOKEN = getStoredToken()?.access_token;
const APPLICATION_JSON = 'application/json';
const MULTIPART_FORM_DATA = 'multipart/form-data';

let lock = false;
let subscribers: ((access_token: string) => void)[] = [];

function subscribeTokenRefresh(callback: (access_token: string) => void) {
  subscribers.push(callback);
}

function onRrefreshed(access_token: string) {
  subscribers.forEach((callback) => callback(access_token));
}

//json용도
const jsonInstance = axios.create({
  baseURL: process.env.REACT_APP_SERVER_URL,
  timeout: 2000,
  headers: {
    'Content-Type': APPLICATION_JSON,
    Authorization: ACCESS_TOKEN,
  },
});

//form-data용도
const formdataInstance = axios.create({
  baseURL: process.env.REACT_APP_SERVER_URL,
  timeout: 5000,
  headers: {
    'Content-Type': MULTIPART_FORM_DATA,
    Authorization: ACCESS_TOKEN,
  },
});

jsonInstance.interceptors.request.use((config: any) => {
  config.headers.Authorization = getStoredToken()?.access_token;
  return config;
});

formdataInstance.interceptors.request.use((config: any) => {
  config.headers.Authorization = getStoredToken()?.access_token;
  return config;
});

const ErrorHandler457 = (err: any) => {
  alert('로그인을 다시 해주세요');
  window.location.href = '/';
  return Promise.reject(err);
};

const originalRequestReFetch = (
  originalRequest: AxiosRequestConfig<any>,
  token: string | undefined,
  type: string,
) => {
  return axios({
    method: originalRequest.method,
    url: originalRequest.baseURL + originalRequest.url!,
    data: originalRequest.data,
    headers: {
      'Content-Type': type,
      Authorization: token,
    },
  });
};

const responseInterceptorHandle = async (err: AxiosError, type: string) => {
  const {
    config, //original request
    response,
  } = err;
  console.log(err);
  const status = response?.status;
  const originalRequest = config;
  console.log(status);
  if (status === 457) {
    return ErrorHandler457(err);
  } else if (status === 456) {
    // console.log('현재토큰 : ', getStoredToken()?.access_token);
    //true이면 구독에 추가

    if (lock) {
      console.log('lock들어옴');
      return new Promise((resolve) => {
        subscribeTokenRefresh((token: string) => {
          resolve(originalRequestReFetch(originalRequest!, token, type)); //의문 2
        });
      });
    }

    //false 이면
    //토큰재발급
    lock = true;
    try {
      const { headers } = await axios.get(
        `${process.env.REACT_APP_SERVER_URL}/auth/refresh`,
        {
          headers: {
            Authorization: getStoredToken()?.access_token,
            refresh: getStoredToken()?.refresh_token,
          },
        },
      );

      const access_token = headers.authorization;
      const refresh_token = headers.refresh;

      await setStoredToken(
        JSON.stringify({
          access_token: access_token,
          refresh_token: refresh_token,
        }),
      );
      // console.log('재발급토큰 : ', access_token);

      //요청했던 데이터 토큰 바꿔서 재요청
      const result = await originalRequestReFetch(
        originalRequest!,
        access_token,
        type,
      );

      //구독했던 모든대기하던 통신들 실행(비동기라 실행만시키고 지나감)
      onRrefreshed(access_token!);

      //초기화
      subscribers = [];
      lock = false;

      return result;
    } catch (err: any) {
      if (err.response.status === 457) return ErrorHandler457(err);
    }
  } else {
    return Promise.reject(err);
  }
};

jsonInstance.interceptors.response.use(
  (res) => res,
  async (err) => responseInterceptorHandle(err, APPLICATION_JSON),
);

formdataInstance.interceptors.response.use(
  (res) => res,
  async (err) => responseInterceptorHandle(err, MULTIPART_FORM_DATA),
);

export { jsonInstance, formdataInstance };
