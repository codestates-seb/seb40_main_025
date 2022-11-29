import { jsonInstance } from 'shared/utils/axios';
// import { User } from './userType';

const apis = {
  logout: async () => {
    const response = await jsonInstance.post<any>(`/logout`);
    return response.data;
  },
  getUser: async () => {
    return await jsonInstance.get<any>('/members/me');
  },
};

export default apis;
