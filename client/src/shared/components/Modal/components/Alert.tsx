import * as S from './SvgComponents';
import { ModalStore } from 'store/store';
import { ModalViewBox, ModalbtnBox } from './ModalContainer';
import useToast from '../../Toast/hooks/useToast';

interface Data {
  title: string;
  content: string;
  color: string;
  onClick: () => void;
}

const Alert = ({ data }: { data: Data }) => {
  const { closeModal } = ModalStore();
  const { setToast } = useToast();
  const handleOnClick = () => {
    closeModal('AlertModal');
    setToast(3000, ['작품이 등록되었습니다.', '내 전시관도 만들어보기']);
  };

  return (
    <ModalViewBox color={data.color}>
      <S.ApplySVG></S.ApplySVG>
      <h3>{data.title}</h3>
      <ModalbtnBox>
        <button onClick={() => closeModal('AlertModal')}>취소</button>
        <button className='Progressbtn' onClick={handleOnClick}>
          {data.content}{' '}
        </button>
      </ModalbtnBox>
    </ModalViewBox>
  );
};

export { Alert };
