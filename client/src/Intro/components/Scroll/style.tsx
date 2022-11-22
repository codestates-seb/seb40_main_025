import { theme } from 'shared/styled/Theme';
import styled from 'styled-components';

export const Container = styled.div`
  margin-top: -5vh;
  display: flex;
  flex-direction: column;
`;
export const Box = styled.div`
  width: 100vw;
  height: 100vh;
  background-color: aliceblue;

  &.yellow {
    background-color: ${({ theme }) => theme.colors.green_004};
  }
`;

export const Button = styled.button`
  width: 100px;
  height: 50px;
  border: 1px solid black;
`;
