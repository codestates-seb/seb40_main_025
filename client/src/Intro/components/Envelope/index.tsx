import { useState } from 'react';
import * as S from './style';
const Index = () => {
  const [isOpen, setIsOpen] = useState<boolean>(false);
  // TODO: 상태값에 따라 클래스 이름 달라지도록

  const onClick = () => {
    setIsOpen(!isOpen);
  };

  return (
    <S.Container>
      <S.OpenBtn onClick={onClick}>Reset</S.OpenBtn>
      <S.EnvelopeWrapper>
        <S.Envelope className={isOpen ? 'open' : 'close'}>
          <S.Front className='flap'></S.Front>
          <S.Front className='pocket'></S.Front>
          <S.Letter className={isOpen ? 'open' : 'close'}>
            {/* D25팀의 올해 네컷에 방문해주신 여러분 환영합니다 */}
          </S.Letter>
        </S.Envelope>
      </S.EnvelopeWrapper>
    </S.Container>
  );
};

export default Index;
