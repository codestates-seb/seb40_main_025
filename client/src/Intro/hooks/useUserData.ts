// 사용자의 상태 관리
// 서버와 통신하도록 하기
import { useQuery } from '@tanstack/react-query';
import apis from '../api';

const GetUser = (onSuccess: any, onError: any) => {
  return useQuery(['user'], apis.getUser, {
    onSuccess,
    onError,
    select: (data) => {
      const user = data?.data;
      return user;
    },
  });
};

export { GetUser };
