import styled from "styled-components";
import {rem} from 'polished';

const ModalBackdrop = styled.div`
    width: ${rem(428)};
    height: 100vh;
    background-color: rgba(0, 0, 0, 0.4);
    position: fixed;
    top:0;
    z-index: 99;
    ${({theme}) => theme.flex.center}
`

const ModalViewBox = styled.div`
    width: ${rem(327)};
    height: ${rem(122)};
    border: solid 1px red;
    z-index: 100;
    ${({theme}) => theme.flex.center}
`

const ModalbtnBox = styled.div`
    width: ${rem(259)};
    height: ${rem(34)};
`
export { ModalBackdrop, ModalViewBox, ModalbtnBox };