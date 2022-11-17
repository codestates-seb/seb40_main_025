import React from 'react';
import styled from 'styled-components';
import { Btn } from 'shared/components/Buttons';
import { rem } from 'polished';

const BtnContainer = styled.div`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center')}
`;

const index = () => {
  // api에서 title, content 받아오기
  return (
    <div>
      <h2>오은의 1년 졸업 전시회</h2>
      <div>저의 1년에 대해 재밌는 사진들이나 추억을 올려주세요</div>
      <BtnContainer>
        <Btn>3D 전시관 보러가기</Btn>
        <Btn className='white'>사진 올려주기</Btn>
      </BtnContainer>
      <div>
        전시기간은 11월 17일까지입니다
      </div>
    </div>
  );
};

export default index;
