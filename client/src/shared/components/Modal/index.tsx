import { ModalBackdrop } from "./components/ModalContainer"
import { Alert } from "./components/Alert";
// { children }: { children: React.ReactNode }
const ModalContainer = () =>{
    return(
        <ModalBackdrop>
            <Alert></Alert>
        </ModalBackdrop>
    )
}

export default ModalContainer;  