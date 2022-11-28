import React from 'react';
import * as S from './ts-practice/singlepage.style';
import LikeButton from 'shared/components/Buttons/likeButton';
// import { rem } from 'polished';

import CommentStore from 'shared/components/PicFooter/OpenComment';
// import SingleComment from 'SingleComments/index';

const SinglePicture = ({
  picture,
  title,
  scrpit,
  username,
  idx,
  array,
}: {
  picture: string;
  title: string;
  scrpit: string;
  username: string;
  idx: number;
  array: number;
}) => {
  const { open } = CommentStore();
  const Check = (): void => {
    console.log(open);
  };

  return (
    <S.Body>
      <S.PageCount>
        {idx + 1}/{array}
      </S.PageCount>
      <S.PicZone>
        <S.SinglePic
          style={{
            background: `url(${process.env.PUBLIC_URL + picture})`,
            backgroundSize: 'cover',
            backgroundRepeat: 'no-repeat',
            backgroundPosition: 'center',
          }}
        >
          <LikeButton></LikeButton>
        </S.SinglePic>
        {/* <S.NextPic /> */}
      </S.PicZone>
      <S.Delete onClick={() => Check()}>삭제</S.Delete>
      <S.PicIntroduct>
        <S.PicTitle>{title}</S.PicTitle>
        <S.PicDiscription>{scrpit}</S.PicDiscription>
      </S.PicIntroduct>
    </S.Body>
  );
};

export default SinglePicture;
