import styled from 'styled-components';
import GalleryInfo from './components/GalleryInfo';
import FilterBox from './components/FilterBox';
import DynamicGallery from './components/DynamicGallery';

const Container = styled.div`
  width: 100vw;
  height: 95vh;
  ${({ theme }) => theme.mixins.flexBox('column', 'center', 'flex-start')}
`;
const GalleryAllPic = () => {
  return (
    <Container>
      <GalleryInfo />
      <FilterBox />
      <DynamicGallery />
    </Container>
  );
};

export default GalleryAllPic;
