import * as C from './components/Container';
import Upload from './components/Upload';
import { Input } from './components/Input';
const UploadPicture = () => {
  return (
    <C.DefualtContainer>
        <Upload></Upload>
      <C.InputContainer>
        <Input></Input>
      </C.InputContainer>
      <C.UploadBtnContainer>
        <button>등록하기</button>
      </C.UploadBtnContainer>
    </C.DefualtContainer>
  );
};
export default UploadPicture;
