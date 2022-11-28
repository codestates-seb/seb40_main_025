import { useQuery } from '@tanstack/react-query';
import { jsonInstance } from 'shared/utils/axios';

const useGetAllPost = (galleryId: number) => {
  const apis = {
    getAllPictures: async () => {
      return await jsonInstance.get(`galleries/${galleryId}/artworks`);
    },
  };

  const { data, status, isLoading, isSuccess } = useQuery(
    ['pictures'],
    () => apis.getAllPictures(),
    {
      enabled: false,
      onError(err) {
        console.log(err);
      },
    },
  );
  return { data, status, isLoading, isSuccess };
};

export default useGetAllPost;
