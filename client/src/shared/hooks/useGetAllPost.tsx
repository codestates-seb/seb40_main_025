import { useQuery, useQueryClient } from '@tanstack/react-query';
import { useEffect } from 'react';
import { useState } from 'react';
import axios from 'axios';

const apiClient = axios.create({
  baseURL:
    'http://ec2-43-200-124-243.ap-northeast-2.compute.amazonaws.com:8080',
  headers: {
    'Access-Control-Allow-Origin': '*',
  },
  withCredentials: true,
});

const getAllPosts = async () => {
  const { data } = await apiClient.get('/galleries/1/artworks');
  //링크 수정 필요

  return data;
};

const useGetAllPost = () => {
  const [questionsData, setQuestionsData] = useState([]);

  const queryClient = useQueryClient();

  interface IGetAllPicture {
    results: AllPicture[];
  }

  interface AllPicture {
    data: [];
    status: any;
    isLoading: any;
    isSuccess: any;
  }
  const { data, status, isLoading, isSuccess } = useQuery<IGetAllPicture>(
    ['pictures'],
    getAllPosts,
    {
      // initialData: () => {
      //   queryClient.getQueryData(['pictures']);
      // },

      refetchOnWindowFocus: false,
      retry: false,
    },
  );

  if (isSuccess) {
    console.log(data);
  }

  useEffect(() => {
    // if (isSuccess) {
    //   setQuestionsData(data);
    // }
  }, [data, isSuccess]);

  return { data, status, isLoading, isSuccess };
};

export default useGetAllPost;
