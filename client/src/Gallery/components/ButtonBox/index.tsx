import * as S from './style';
import Camera from 'assets/Icon/camera';
import { Btn, IconBtn } from 'shared/components/Buttons';
import { StyledLink } from 'shared/components/LinkButton/style';
import { useNavigate } from 'react-router-dom';
import GalleryType from 'GallerySetting/galleryType';
import { useGalleryData } from 'GallerySetting/hooks/useGalleryData';

const Index = ({ galleryId }: GalleryType) => {
  const { data } = useGalleryData(galleryId!);
  const navigate = useNavigate();
  const handleClick = () => {
    navigate('/uploadPicture', { state: galleryId });
  };

  const disabledClick = () => {
    alert('서비스를 준비 중입니다');
  };
  return (
    <div>
      <S.BtnContainer>
        <Btn className='mr disabled' onClick={disabledClick}>
          3D 전시관 보러가기
        </Btn>
        <IconBtn onClick={handleClick} className='white' icon={<Camera />}>
          <p>사진 올려주기</p>
        </IconBtn>
      </S.BtnContainer>

      <S.Time>전시기간은 {data.createdAt}까지입니다</S.Time>
    </div>
  );
};

export default Index;
