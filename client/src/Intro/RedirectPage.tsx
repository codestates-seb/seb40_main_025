import { ReactElement, useEffect } from 'react';
import { StyledLink } from 'shared/components/LinkButton/style';
import { getStoredToken, setStoredToken } from './hooks/tokenStorage';
import { GetUser } from './hooks/useUser';

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

  console.log(GetUser());

  return (
    <>
      로그인이 성공적으로 되었다는 소리다
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
