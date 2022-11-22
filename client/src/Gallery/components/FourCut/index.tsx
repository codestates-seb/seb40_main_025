import * as S from './style';
import { Btn } from 'shared/components/Buttons';

const index = () => {
  return (
    <S.Container>
      <S.FourCut>
        <S.Frame className='box tl' src='/images/1.jpg'></S.Frame>
        <S.Frame className='box tr' src='/images/2.jpg'></S.Frame>
        <S.Frame className='box bl' src='/images/3.jpg'></S.Frame>
        <S.Frame className='box br' src='/images/4.jpg'></S.Frame>
      </S.FourCut>

      <S.BtnContainer>
        <Btn className='square'>전체 작품 보기</Btn>
        <Btn className='square white'>나도 전시관 만들기</Btn>
      </S.BtnContainer>
      <div>
        <p>여러분만의 1년이 담긴 전시회도 만들고</p>
        <p>친구들에게 공유해보세요!</p>
      </div>
    </S.Container>
  );
};

export default index;