import * as S from './Single Comments.style';
import SingleComment from './SingleComment/SingleComment';
import COMMENTS_SCRIPT from 'SingleComments/SingleComment/sampledata';
import XIcon from 'shared/components/Icons/XIcon';

import CommentStore from 'shared/components/PicFooter/OpenComment';

const CommentsList = () => {
  const { setCloseModal } = CommentStore();
  return (
    <S.CommentBody>
      <S.PicTitle>
        <S.CommentCount>댓글 8</S.CommentCount>
        <div onClick={() => setCloseModal()}>
          <XIcon />
        </div>
      </S.PicTitle>
      {COMMENTS_SCRIPT.map((el) => {
        return (
          <SingleComment
            nickname={el.NICKNAME}
            time={el.TIME}
            comment={el.COMMENT}
          />
        );
      })}
    </S.CommentBody>
  );
};

export default CommentsList;
