import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const AutoRedirect = (galleryId: number) => {
  const navigate = useNavigate();
  useEffect(() => {
    galleryId ? navigate(`/fourPic/${galleryId}`) : navigate(`/gallerySetting`);
  });
};

export default AutoRedirect;
