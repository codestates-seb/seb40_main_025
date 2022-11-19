import React from 'react';
import * as S from './style';

const index = () => {
  return (
    // 나중에 column별로 데이터가 map으로 돌아가면서 순차적으로 추가되도록 해야 함.
    <S.Thumbnails>
      <S.Column>
        <S.ThumbnailImg src='/images/3.jpg' />
        <S.ThumbnailImg src='/images/6.jpg' />
        <S.ThumbnailImg src='/images/2.jpg' />
        <S.ThumbnailImg src='/images/4.jpg' />
        <S.ThumbnailImg src='/images/1.jpg' />
      </S.Column>
      <S.Column>
        <S.ThumbnailImg src='/images/1.jpg' />
        <S.ThumbnailImg src='/images/4.jpg' />
        <S.ThumbnailImg src='/images/3.jpg' />
        <S.ThumbnailImg src='/images/5.jpg' />
        <S.ThumbnailImg src='/images/2.jpg' />
      </S.Column>
      <S.Column>
        <S.ThumbnailImg src='/images/5.jpg' />
        <S.ThumbnailImg src='/images/2.jpg' />
        <S.ThumbnailImg src='/images/4.jpg' />
        <S.ThumbnailImg src='/images/3.jpg' />
        <S.ThumbnailImg src='/images/6.jpg' />
      </S.Column>
    </S.Thumbnails>
  );
};

export default index;
