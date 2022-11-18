import styled from 'styled-components';
import { rem } from 'polished';

const DefualtContainer = styled.div`
  width: ${rem(428)};
  height: 95vh;
  border: solid 1px red;
  display: flex;
  flex-direction: column;
`;

const UploadPictureContainer = styled.div`
  width: ${rem(428)};
  height: ${rem(400)};
  border: solid 1px blue;
  margin-top: ${rem(38)};
  padding: 0 ${rem(40)} 0 ${rem(40)};
  position: relative;

  h2 {
    margin-bottom: ${rem(20)};
  }
  .UploadSvg {
    font-size: ${rem(12)};
    display: flex;
    flex-direction: column;
    align-items: center;
    position: absolute;
    top: 45%;
    left: 30%;
  }
`;
const InputContainer = styled.div`
  width: ${rem(428)};
  height: ${rem(223)};
  border: solid 1px green;
  margin-top: ${rem(53)};
`;
const UploadBtnContainer = styled.div`
    widthL ${rem(428)};
    height: ${rem(40)};
    border: solid 1px purple;
    margin-top: ${rem(59)};
    margin-bottom: ${rem(65)};
    button{
        width: ${rem(100)};
        height: ${rem(40)};
        background-color: ${({ theme }) => theme.colors.green_002};
        border-radius: ${rem(5)};
        color: white;
        font-size: ${rem(16)};
        margin-left: ${rem(294)};
    }


`;

export {
  DefualtContainer,
  UploadPictureContainer,
  InputContainer,
  UploadBtnContainer,
};
