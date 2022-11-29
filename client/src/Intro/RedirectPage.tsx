import { ReactElement, useEffect } from 'react';
import { StyledLink } from 'shared/components/LinkButton/style';
import { setStoredToken } from './hooks/tokenStorage';
import { GetUser } from './hooks/useUserData';
import { loginStore } from 'store/store';

const RedirectPage = (): ReactElement => {
  useEffect(() => {
    let params = new URL(document.location.toString()).searchParams;
    let access_token = params.get('access_token'); // access_token
    let refresh_token = params.get('refresh_token'); // refresh_token

    setStoredToken(
      JSON.stringify({
        access_token: access_token,
        refresh_token: refresh_token,
      }),
    );
  }, []);

  const { isLoggedin, setIsLoggedIn, user } = loginStore();
  const setUser = loginStore((state) => state.setUser);
  const onSuccess = (data: any) => {
    // 유저데이터 저장
    setIsLoggedIn();
    setUser(data);
  };

  const onError = () => {
    console.log('perform side effect after encountering error');
  };
  const { isLoading, data, isError } = GetUser(onSuccess, onError);

  if (isLoading) {
    return <h2>Loading....</h2>;
  }
  if (isError) {
    return <h2>에러 발생</h2>;
  }

  const Child = (props: any) => {
    const user = props.user;
    return (
      <>
        <div>{user.nickname}</div>
        <img src={user.profile}></img>
      </>
    );
  };

  return (
    <>
      로그인 완료 후 넘어오는 화면입니다
      <Child user={data}></Child>
      <button>
        <StyledLink to={'/gallerySetting'}>전시관 구경 가기</StyledLink>
      </button>
      <button>
        <StyledLink to={'/gallerySetting'}>
          이전 페이지로 돌아가기(아직 구현 x)
        </StyledLink>
      </button>
    </>
  );
};

export default RedirectPage;