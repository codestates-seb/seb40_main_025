import { useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';

const useCreateComment = () => {
  const queryClient = useQueryClient();

  const apiClient = axios.create({
    baseURL:
      'http://ec2-43-200-124-243.ap-northeast-2.compute.amazonaws.com:8080/',
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
    withCredentials: true,
  });

  const createComment = async (context: string) => {
    const response = await apiClient.post('/galleries/1/artworks/1/comments', {
      context,
    });

    return response;
  };

  const { mutate, status } = useMutation(
    //'UseMutationResult<AxiosResponse<any, any>, unknown, void, unknown>' 형식에 'state' 속성이 없습니다.
    ['createComment'],
    (context: string) => createComment(context),
    //'void' 형식의 인수는 '{ context: string; }' 형식의 매개 변수에 할당될 수 없습니다.
    {
      onSuccess: () => queryClient.invalidateQueries(['createComment']),
    },
  );

  return { mutate, status };
};

export default useCreateComment;
