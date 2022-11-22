import styled from 'styled-components';
import { rem } from 'polished';

const Body = styled.div`
  height: 40%;
  background: linear-gradient(to bottom, transparent, rgba(0, 0, 0, 0.05));
  display: flex;
  justify-content: center;
  align-items: flex-end;
  z-index: 2;
  position: absolute;
  top: 100vh;
  transform: translateY(-100%);
  width: inherit;
  pointer-events: none;
`;

const InputZone = styled.div`
  width: 80%;
  height: ${rem(48)};
  border-radius: ${rem(38)};
  border: 0;
  margin-bottom: ${rem(41)};
  padding: ${rem(12)};
  box-shadow: rgba(0, 0, 0, 0.35) 0 ${rem(5)} ${rem(15)};
  justify-content: space-between;
  background-color: ${({ theme }) => theme.colors.black_007};
  pointer-events: auto;
`;

const Input = styled.input`
  width: ${rem(250)};
  font-style: normal;
  font-weight: 400;
  font-size: ${rem(15)};
  line-height: ${rem(18)};
  border: none;
  background: transparent;
  outline: none;
  margin-left: ${rem(8)};
`;

const SubmitButton = styled.button`
  width: ${rem(60)};
  height: ${rem(20)};
  background: transparent;
  font-style: normal;
  font-weight: 400;
  font-size: ${rem(15)};
  line-height: ${rem(18)};
  text-align: center;

  border: none;
  outline: none;
  cursor: pointer;
`;

const CommentInput = () => {
  return (
    <Body>
      <InputZone>
        <Input />
        <SubmitButton type='button'>입력</SubmitButton>
      </InputZone>
    </Body>
  );
};

export default CommentInput;
