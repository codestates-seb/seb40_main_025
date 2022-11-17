import React from 'react';
import styled from 'styled-components';
import { Btn } from 'shared/components/Buttons';
import { rem } from 'polished';

// pixel 단위 rem으로 바꾸기
// mixin 써서 props 들어온 거에 따라서 해당 border-radius 위치만 적용할 수는 없을까?
const Container = styled.div`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center', 'wrap')}
  width: 440px;

  p {
    color: ${({theme}) => theme.colors.green_002};
    font-size: ${rem('16px')};
    text-align: center;
  }
`;

const Layout = styled.div`
  width: 170px;
  height: 150px;
  margin: 5px;
  border: 1px solid ${({ theme }) => theme.colors.black_001};

  &.tl {
    border-top-left-radius: 35px;
  }
  &.tr {
    border-top-right-radius: 35px;
  }
  &.bl {
    border-bottom-left-radius: 35px;
  }
  &.br {
    border-bottom-right-radius: 35px;
  }
`;

const BtnContainer = styled.div`
  ${({ theme }) => theme.mixins.flexBox('column', 'center', 'center')}
`;

const index = () => {
  // api에서 title, content 받아오기

  return (
    <Container>
      <Layout className='tl' />
      <Layout className='tr' />
      <Layout className='bl' />
      <Layout className='br' />
      <BtnContainer>
        <Btn className='square'>전체 작품 보기</Btn>
        <Btn className='square white'>나도 전시관 만들기</Btn>
      </BtnContainer>
      <div>
        <p>여러분만의 1년이 담긴 전시회도 만들고</p>
        <p>친구들에게 공유해보세요!</p>
      </div>
    </Container>
  );
};

export default index;
