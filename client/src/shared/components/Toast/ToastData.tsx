import { ALLOW_FILE_EXTENSION } from 'shared/libs/uploadHelper';

export interface ToastData {
  time: number;
  content: string[];
  color: string;
}

const PROFILE_MODIFY_SUCCESS: ToastData = {
  time: 1500,
  content: ['프로필이 변경되었습니다.', ''],
  color: 'green',
};
const CHECK_FILE_INFO: ToastData = {
  time: 4000,
  content: [
    '확장자와 파일크기를 확인해주세요',
    `확장자 : ${ALLOW_FILE_EXTENSION}, 크기: 10MB이하`,
  ],
  color: 'red',
};

const CHECK_FORM: ToastData = {
  time: 3000,
  content: [
    '입력하지 않은곳이 있는지 확인해주세요',
    '제목은 15글자, 설명은 60글자 이하여야합니다.',
  ],
  color: 'red',
};

const CHECK_NICKNAME: ToastData = {
  time: 3000,
  content: [
    '입력하지 않은곳이 있는지 확인해주세요',
    '닉네임은 최대 7글자까지 가능합니다',
  ],
  color: 'red',
};

const CLIPBOARD_COPY_SUCCESS: ToastData = {
  time: 3000,
  content: ['클립보드에 복사되었습니다.', '친구에게 해당 링크를 공유해주세요!'],
  color: 'green',
};

const CHECK_MAKE_GALLERY: ToastData = {
  time: 3000,
  content: ['만들어진 나의 전시관이 없습니다', "먼저 '전시관'을 만들어주세요"],
  color: 'red',
};

const CLIPBOARD_COPY_FAIL = (URL: string) => {
  return {
    time: 3000,
    content: [
      '복사가 지원되지 않는 브라우저입니다. 아래링크를 복사하세요',
      URL,
    ],
    color: 'red',
  };
};

const DELETE_GALLERY: ToastData = {
  time: 3000,
  content: ['전시관이 삭제되었습니다', '새로운 전시관을 만들어 보세요'],
  color: 'green',
};

const LOGOUT: ToastData = {
  time: 1500,
  content: ['로그아웃 되었습니다.', ''],
  color: 'green',
};

const DELETE_USER: ToastData = {
  time: 3000,
  content: ['회원탈퇴가 완료되었습니다', '이용해주셔서 감사합니다'],
  color: 'green',
};

const UPLOAD_SUCCESSE: ToastData = {
  time: 1500,
  content: ['작품이 등록되었습니다.', ''],
  color: 'green',
};

const GALLERY_SETTING: ToastData = {
  time: 1500,
  content: ['전시관 이름과 소개를 작성해주세요!.', ''],
  color: 'red',
};

const ARTWORK_MODIFY_SUCCESS: ToastData = {
  time: 1500,
  content: ['작품 수정이 완료되었습니다', ''],
  color: 'green',
};

export {
  PROFILE_MODIFY_SUCCESS,
  CHECK_FILE_INFO,
  CHECK_FORM,
  CLIPBOARD_COPY_SUCCESS,
  CHECK_MAKE_GALLERY,
  CLIPBOARD_COPY_FAIL,
  DELETE_GALLERY,
  LOGOUT,
  DELETE_USER,
  UPLOAD_SUCCESSE,
  CHECK_NICKNAME,
  GALLERY_SETTING,
  ARTWORK_MODIFY_SUCCESS,
};
