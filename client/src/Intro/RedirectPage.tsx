import { ReactElement, useEffect } from 'react';
import { StyledLink } from 'shared/components/LinkButton/style';
import { setStoredToken, getStoredToken } from './hooks/tokenStorage';
import { GetUser } from './hooks/useUserData';
import { loginStore } from 'store/store';
import { jsonInstance } from 'shared/utils/axios';
import { getUser } from './api';
import axios from 'axios';
import AutoRedirect from './hooks/AutoRedirect';

const RedirectPage = (): ReactElement => {
  const { isLoggedin, setIsLoggedIn, user } = loginStore();
  const setUser = loginStore((state) => state.setUser);

  let params = new URL(document.location.toString()).searchParams;
  let access_token = params.get('access_token'); // access_token
  let refresh_token = params.get('refresh_token'); // refresh_token

  setStoredToken(
    JSON.stringify({
      access_token: access_token,
      refresh_token: refresh_token,
    }),
  );

  axios
    .get<any>(`${process.env.REACT_APP_SERVER_URL}/members/me`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: access_token,
      },
    })
    .then((res) => {
      setIsLoggedIn();
      setUser(res.data);
    });

  AutoRedirect(user?.galleryId!);

  return <div></div>;
};

export default RedirectPage;
