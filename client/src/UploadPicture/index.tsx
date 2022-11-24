import * as C from './components/Container';
import Upload from './components/Upload';
import { Input } from './components/Input';
import { ModalStore, UploadStore } from 'store/store';
import { Alert } from 'shared/components/Modal/components/Alert';
import ModalBackdrop from 'shared/components/Modal/components/ModalBackdrop';
import { useRef } from 'react';
import useToast from 'shared/components/Toast/hooks/useToast';
import { UploadAlert } from '../../src/shared/components/Modal/AlertData';

const UploadPicture = () => {
  const { target, openModal, closeModal } = ModalStore();
  const { UploadData } = UploadStore();
  const { setToast } = useToast();
  const formRef = useRef<HTMLFormElement>(null);

  const onClick = () => {
    //여기에 progressBtn을 눌렀을때 필요한 로직 작성
    closeModal('AlertModal');
  };

  const data = {
    title: '작품을 등록하시겠습니까?',
    content: '등록하기',
    color: 'green',
    onClick: onClick,
  };

  const handlePostbtn = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (
      Object.values(UploadData).filter((el) => !el).length > 0 ||
      UploadData.content.length > 30 ||
      UploadData.title.length > 15
    ) {
      setToast(3000, [
        '입력하지 않은곳이 있는지 확인해주세요',
        '제목은 15글자, 설명은 30글자 이하여야합니다.',
      ]);
      return;
    }
    else {
      openModal("AlertModal")
    }
  };
  // const data2 = new FormData(formRef.current!) 데이터 어떻게 들어가는지 확인해보기

  return (
    <>
      <C.DefualtContainer onSubmit={handlePostbtn} ref={formRef}>
        <Upload />
        <C.InputContainer>
          <Input />
        </C.InputContainer>
        <C.UploadBtnContainer>
          <button type='submit'>등록하기</button>
        </C.UploadBtnContainer>
      </C.DefualtContainer>
      {/* 모달 생성부분 */}
      {target.AlertModal ? (
        <ModalBackdrop>
          <Alert data={UploadAlert(onClick)} />
        </ModalBackdrop>
      ) : null}
    </>
  );
};
export default UploadPicture;
