import * as B from './components/ModalContainer';
import * as S from './components/SvgComponents';
import { loginStore } from 'store/store';
import useHandleService from './hooks/useHandleService';
import useClipboardCopy from './hooks/useClipboardCopy';

const Profile = () => {
  const { textareaRef, handleCopy, URL } = useClipboardCopy();
  const { handleCloseGallery, handleDeleteUser, handleLogout, navigateSearch } =
    useHandleService();
  const { isLoggedin } = loginStore();

  return (
    <B.HambergurBox>
      <B.ProfileBox>
        <div>
          <img src='/images/2.jpg' alt='profileimg'></img>
        </div>
        <S.ModifyProfileImg />
      </B.ProfileBox>
      <h4>
        {isLoggedin ? '팀장승필' : '로그인이 필요합니다.'}
        {isLoggedin && <S.ModifyNickname />}
      </h4>
      {isLoggedin && (
        <>
          <ul>
            <li onClick={() => navigateSearch('/gallerySetting', {})}>
              전시관 편집하기
            </li>
            <li onClick={handleCopy}>전시회 공유하기</li>
            <li onClick={handleLogout}>로그아웃</li>
            <li onClick={handleCloseGallery}>전시회 삭제</li>
            <li onClick={handleDeleteUser}>회원탈퇴</li>
          </ul>
          <B.TextBox readOnly={true} ref={textareaRef} value={URL} />
        </>
      )}
    </B.HambergurBox>
  );
};

export { Profile };
