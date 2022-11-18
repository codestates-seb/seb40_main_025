import React from 'react';
import styled from 'styled-components';
import { Btn, IconBtn } from 'shared/components/Buttons';
import { rem } from 'polished';
import Camera from 'assets/Icon/camera';

const index = () => {
  // api에서 title, content 받아오기
  return (
    <div>
      <Info>
        <h2>오은의 1년 졸업 전시회</h2>
        <div>저의 1년에 대해 재밌는 사진들이나 추억을 올려주세요</div>
      </Info>

      <BtnContainer>
        <Btn className='mr'>3D 전시관 보러가기</Btn>
        <IconBtn className='white' icon={<Camera />}>
          <p>사진 올려주기</p>
        </IconBtn>
      </BtnContainer>

      <Time>전시기간은 11월 17일까지입니다</Time>
    </div>
  );
};

export default index;

const Info = styled.div`
  margin-bottom: ${rem(20)};
`;

const BtnContainer = styled.div`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center')}

  & .mr {
    margin-right: ${rem(10)};
  }

  p {
    margin-right: ${rem(10)};
  }
`;

const Time = styled.div`
  color: ${({ theme }) => theme.colors.black_004};
  font-size:  ${rem(15)};
  text-align: end;
`;
