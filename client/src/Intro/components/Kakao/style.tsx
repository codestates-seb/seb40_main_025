import { rem } from 'polished';
import styled from 'styled-components';

const Container = styled.div``;
const Btn = styled.a`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'flex-start')}
  background-color: ${({ theme }) => theme.colors.kakao_001};
  border: none;
  border-radius: ${rem(12)};
  width: ${rem(183)};
  height: ${rem(45)};
  padding: ${rem(20)};

  & .label {
    font-size: ${rem(15)};
    margin-left: 15px;
  }
`;

export { Container, Btn };
