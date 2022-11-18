import React from 'react';
import styled from 'styled-components';
import { Btn } from 'shared/components/Buttons';
import { rem } from 'polished';

const index = () => {
  // api에서 title, content 받아오기

  return (
    <Container>
      <FourCut>
        <Frame className='box tl' src='/images/1.jpg'></Frame>
        <Frame className='box tr' src='/images/2.jpg'></Frame>
        <Frame className='box bl' src='/images/3.jpg'></Frame>
        <Frame className='box br' src='/images/4.jpg'></Frame>
      </FourCut>

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



const Container = styled.div`
  p {
    color: ${({ theme }) => theme.colors.green_002};
    font-size: ${rem(16)};
    text-align: center;
  }
`;

const FourCut = styled.div`
  margin: ${rem(20)} auto;
  width: ${rem(350)};
  height: ${rem(310)};
  background-color: #fff;
  display: grid;
  grid-template-columns: ${rem(170)} ${rem(170)};
  grid-template-rows: ${rem(150)} ${rem(150)};
  gap: ${rem(10)};
`;

const Frame = styled.img`
  width: ${rem(170)};
  height: ${rem(150)};

  &.box {
    background-color: #333;
    border-radius: ${rem(5)};
    color: #fff;
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: sans-serif;
  }

  &.tl {
    border-top-left-radius: ${rem(35)};
  }
  &.tr {
    border-top-right-radius: ${rem(35)};
  }
  &.bl {
    border-bottom-left-radius: ${rem(35)};
  }
  &.br {
    border-bottom-right-radius: ${rem(35)};
  }
`;

const BtnContainer = styled.div`
  ${({ theme }) => theme.mixins.flexBox('column', 'center', 'center')}
  & button {
    margin-bottom: ${rem(10)};
  }
`;
