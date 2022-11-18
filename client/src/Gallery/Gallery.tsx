import React from 'react';
import styled from 'styled-components';
import GalleryInfo from './components/GalleryInfo';
import FourCut from './components/FourCut';


const Container = styled.div`
  width: 100vw;
  height: 95vh;
  ${({ theme }) => theme.mixins.flexBox('column', 'center', 'flex-start')}
`;
const Gallery = () => {
  return (
    <Container>
      <GalleryInfo />
      <FourCut />
    </Container>
  );
};

export default Gallery;