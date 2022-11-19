import React from 'react';
import { rem } from 'polished';
import styled from 'styled-components';

export const Container = styled.div`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center', 'wrap')}
  width: ${rem(392)};
  & img {
    margin: ${rem(5)};
  }
`;

export const Thumbnails = styled.section`
  width: ${rem(392)};
  display: flex;
`;

export const Column = styled.div`
  flex-grow: 1;
  margin: 0 0.25rem;
`;

export const ThumbnailImg = styled.img`
  width: 100%;
  border-radius: 0.2rem;
  margin-bottom: 0.25rem;
`;