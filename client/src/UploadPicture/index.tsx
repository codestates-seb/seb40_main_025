import * as C from './components/Container';
import Upload from './components/Upload';
import { Input } from './components/Input';
import { ModalStore, UploadStore, ToastStore } from 'store/store';
import { Alert } from 'shared/components/Modal/components/Alert';
import ModalBackdrop from 'shared/components/Modal/components/ModalBackdrop';
import { useRef } from 'react';
import { formdataInstance } from 'shared/utils/axios';

const UploadPicture = () => {
  const { target, openModal, closeModal } = ModalStore();
  const { addToast, removeToast } = ToastStore();
  const { UploadData } = UploadStore();
  const formRef = useRef<HTMLFormElement>(null);
  
  const onClick = () => {
    //여기에 progressBtn을 눌렀을때 필요한 로직 작성
    closeModal("AlertModal");
  };

  const data = {
    title: '작품을 등록하시겠습니까?',
    content: '등록하기',
    color: 'green',
    onClick: onClick,
  };

  const handlePostbtn = (event: React.FormEvent<HTMLFormElement>) => {
    // const handlePostbtn = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (Object.values(UploadData).filter(el => !el).length > 0) {
      const obj = {
        time: 3000, //ms
        content: ['입력하지 않은곳이 있는지 확인해주세요', '제목과 설명은 30자 이하여야합니다.'], //위,아래에 들어갈 원하는 내용 작성
      };
      addToast(obj); //ToastStore에 Toast 추가
      setTimeout(() => removeToast(), 3000); //ToastStroe에서 만든 Toast요소제거
      return;
    }

    const data = new FormData();
    data.append('img', UploadData.img!);
    data.append('title', UploadData.title);
    data.append('content', UploadData.content);

    // const data2 = new FormData(formRef.current!)
    formdataInstance.post('/', data);

  }

  return (
    <>
      <C.DefualtContainer onSubmit={handlePostbtn} ref={formRef}>
        <Upload/>
        <C.InputContainer>
          <Input/>
        </C.InputContainer>
        <C.UploadBtnContainer>
          <button type='submit'>등록하기</button>
        </C.UploadBtnContainer>
      </C.DefualtContainer>
      {/* 모달 생성부분 */}
      {target.AlertModal ? (
        <ModalBackdrop>
          <Alert data={data}/>
        </ModalBackdrop>
      ) : null}
    </>
  );
};
export default UploadPicture;
