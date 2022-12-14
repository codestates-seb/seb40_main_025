import * as B from './ModalContainer';
import * as S from './SvgComponents';
import * as TOAST from 'shared/components/Toast/ToastData';
import useToast from 'shared/components/Toast/hooks/useToast';
import { useState } from 'react';
import { uploadHelper, heicTojpeg } from 'shared/libs/uploadHelper';
import { useModifyProfile } from '../hooks/useModifyProfile';

const ProfileModify = ({
  isModifing,
  setIsModifing,
}: {
  isModifing: boolean;
  setIsModifing: (isModifing: boolean) => void;
}) => {
  const { setToast } = useToast();
  const { mutate, isLoggedin, user, profileRef } = useModifyProfile();
  const [name, setName] = useState(user?.nickname);
  const [profileimg, setProfileImg] = useState<string | undefined>(
    user?.profile,
  );

  const handleCancel = () => {
    setProfileImg(user?.profile);
    setName(user?.nickname);
    setIsModifing(!isModifing);
  };

  const handleProfileImg = async (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    if (!event.target.files?.length) return;
    else if (event.target.files! && uploadHelper(event.target.files[0]))
      setProfileImg(
        URL.createObjectURL(await heicTojpeg(event.target.files[0])),
      );
    else {
      if (profileimg === undefined || profileRef.current)
        profileRef.current!.value = ''; //onChange 이벤트 활성화를 위한 초기화

      setToast(TOAST.CHECK_FILE_INFO);
    }
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (profileRef.current?.files && name && profileimg) {
      mutate({
        img: await heicTojpeg(profileRef.current!.files[0]),
        nickname: name,
      });
      setIsModifing(!isModifing);
    } else {
      setToast(TOAST.CHECK_NICKNAME);
    }
  };

  return (
    <B.ProfileBox onSubmit={handleSubmit} isModifing={isModifing}>
      {/* 프로필 */}
      <div>
        <div className='ProfileImg'>
          <img
            src={isLoggedin ? profileimg : '/images/DefaultProfileImg.png'}
            alt='profileimg'
          ></img>
        </div>
      </div>
      <label htmlFor='profile-file'>
        {isModifing && <S.ModifyProfileImg />}
      </label>
      <input
        type='file'
        id='profile-file'
        ref={profileRef}
        onChange={handleProfileImg}
      />

      {/* 닉네임 */}
      <input
        id='Nickname'
        readOnly={!isModifing}
        value={isLoggedin ? name : '로그인이 필요합니다.'}
        maxLength={7}
        onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
          setName(e.target.value)
        }
      ></input>
      
      {isModifing && (
        <div className='modifyBox'>
          <button type='button' className='cancel' onClick={handleCancel}>
            취소
          </button>
          <button type='submit'>완료</button>
        </div>
      )}
    </B.ProfileBox>
  );
};

export default ProfileModify;
