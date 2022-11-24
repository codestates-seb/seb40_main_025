import { useMutation, useQueryClient } from "@tanstack/react-query";
import apis from '../api'

const useUpload = (img: File, title: string, content: string, galleryId:number) => {
    const queryClient = useQueryClient();

    // const { data } = useMutation(['useUpload'], apis.postImageAndContent(img, title, content, galleryId));
    const { data, error, status } = useMutation(['useUpload'], () => apis.postImageAndContent(img, title, content, galleryId), {
        onMutate() {
            return true;
        },
        
    });
    

}

export default useUpload;