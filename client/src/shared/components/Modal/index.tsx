import { ModalBackdrop } from './components/ModalContainer';
import { Alert } from './components/Alert';
// { children }: { children: React.ReactNode }
const ModalContainer = () => {
  const onClick = () => {
    console.log('여기에 progressBtn을 눌렀을때 필요한 로직 작성');
  };
  //alert가나올지 햄버거가 나올지 마지막페이지댓글페이지가 나올지
  const data = [
    {
        title: '작품을 등록하시겠습니까?',
        content: '등록하기',
        color: 'green',
        onClick: onClick,
      }, 
      {
        title: '전시회를 삭제하시겠습니까?',
        content: '삭제하기',
        color: 'red',
        onClick: onClick,
      }, 
      {
        title: '서비스를 탈퇴하시겠습니까?',
        content: '탈퇴하기',
        color: 'red',
        onClick: onClick,
      },
      {
        title: '작품을 삭제하시곘습니까?',
        content: '삭제하기',
        color: 'red',
        onClick: onClick,
      },
  ];

  return (
    <ModalBackdrop>
      <Alert data={data[0]}></Alert>
    </ModalBackdrop>
  );
};

export default ModalContainer;
