import { jsonInstance, formdataInstance } from 'shared/utils/axios';
import img from 'UploadPicture/1.jpg';

const api = {
  postImageAndContent: async (title: string, content: string) => {
    let blob = new Blob([new ArrayBuffer(img)], { type: 'image/jpg' });

    let reader = new FileReader();
    reader.readAsDataURL(blob); // 1. 파일을 읽어 버퍼에 저장합니다.
    // 파일 상태 업데이트
    reader.onloadend = () => {
      // 2. 읽기가 완료되면 아래코드가 실행됩니다.
      const base64 = reader.result;
      if (base64) {
        //  images.push(base64.toString())
        var base64Sub = base64.toString();

        const setImgBase64 = base64Sub;
          console.log(setImgBase64);
          return setImgBase64
      }
    };

    console.log(reader);

    const formdata = new FormData();
    // formdata.append('img', blob2);
    formdata.append('title', title);
    formdata.append('content', content);

    console.log(formdata);

    const data = await formdataInstance.post('/galleries/1/artworks', formdata);
    console.log(data);
    return data;
  },

  getTest: async () => {
    const data = await jsonInstance.get('/galleries/1/artworks');
      console.log(data);
      return data;
    },
  
};

export default api;

// let blob = new Blob([new ArrayBuffer(img)], { type: "image/jpg" });
