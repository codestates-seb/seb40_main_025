// 사용자의 상태 관리
// 서버와 통신하도록 하기
import { useQuery } from '@tanstack/react-query';
import apis from '../api';
import { loginStore } from 'store/login';

const GetUser = () => {
  const { isLoggedin, setIsLoggedIn } = loginStore();
  const { data, isLoading } = useQuery(['user'], apis.getUser, {
    onSuccess: () => {
      setIsLoggedIn();
    },
  });
  return data?.data;
};

export { GetUser };
