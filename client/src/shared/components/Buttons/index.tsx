import React from 'react';
import styled from 'styled-components';
import { rem } from 'polished';

interface Props {
  border: string;
  bgColor: string;
  color: string;
  children?: React.ReactNode;
  height: string;
  onClick: () => void;
  radius: string;
  width: string;
}

// 아예 이놈을 초록색으로 바꿔버려
const GreenBtn: React.FC<Props> = ({
  border,
  bgColor,
  color,
  children,
  height,
  onClick,
  radius,
  width,
}) => {
  return (
    <button
      onClick={onClick}
      style={{
        backgroundColor: bgColor,
        border,
        borderRadius: radius,
        color,
        height,
        width,
      }}
    >
      {children}
    </button>
  );
};

const Btn = styled.button`
  width: 185px;
  height: 40px;
  ${({ theme }) => theme.mixins.flexBox('column', 'center', 'center')}
  background-color: ${({ color, theme }) => {
    return color ? theme.colors.black_007 : theme.colors.green_002;
  }};
  border: 1px solid ${({ theme }) => theme.colors.green_002};
  border-radius: 20px;
  color: ${({ theme }) => theme.colors.black_007};
  font-size: ${rem('14px')};
  text-align: center;
  padding: 5px;

  &.square {
    border-radius: 5px;
  }

  &.white {
    background-color: ${({ theme }) => theme.colors.black_007};
    color: ${({ theme }) => theme.colors.green_002};
  }
`;

export { GreenBtn, Btn };
