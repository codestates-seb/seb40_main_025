import Input from './components/Input';
import { patchGallery, postGallery, deleteGalleryById } from './api';
import { loginStore } from 'store/store';
import { getUser } from 'Intro/api';

const GallerySetting = () => {
  const { user } = loginStore();
  const setUser = loginStore((state) => state.setUser);
  const galleryId = user?.galleryId;

  const onSubmit = (form: { title: string; content: string }) => {
    galleryId !== null
      ? patchGallery(form)
      : postGallery(form).then((res) => {
          setUser(res.data);
        });
  };

  const onClick = () => {
    deleteGalleryById();
    // TODO: galleryId null 처리 목적 - GET 요청 안 보내는 방향으로 추후 수정
    getUser().then((res) => {
      setUser(res.data);
    });
  };

  return (
    <div>
      <button onClick={onClick}>전시관 폐쇄</button>
      <Input onSubmit={onSubmit} />
    </div>
  );
};

export default GallerySetting;
