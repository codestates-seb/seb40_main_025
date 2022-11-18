import { ModalViewBox, ModalbtnBox } from "./ModalContainer";
import * as S from "./SvgComponents";
const Alert = () =>{
    return (
    <ModalViewBox>
        <S.ApplySVG></S.ApplySVG>
        <h3>작품을 등록하시겠습니까?</h3>
        <ModalbtnBox></ModalbtnBox>
    </ModalViewBox>
    )
}

export {Alert};