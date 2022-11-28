import Footer from 'shared/components/PicFooter/PicFooter';
import SinglePicture from './SinglePicture';
import SAMPLE_PICTURES from './data/Sample';
import styled from 'styled-components';
import { rem } from 'polished';
import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';
import './styles.css';

import CommentStore from 'shared/components/PicFooter/OpenComment';
import SingleComment from 'SingleComments/index';

import useGetAllPost from '../shared/hooks/useGetAllPost';

const Body = styled.div`
  width: ${rem(420)};
  height: auto;
  display: flex;
  flex-direction: column;
`;

const SinglePicPage = () => {
  const { open } = CommentStore();
  const { data } = useGetAllPost();

  return (
    <Body>
      {open ? (
        <SingleComment></SingleComment>
      ) : (
        <Swiper
          slidesPerView={1}
          spaceBetween={10}
          centeredSlides={true}
          className='swiper'
        >
          {data.map((el, idx, array) => (
            <SwiperSlide className='swiper-slide'>
              <Body className='single'>
                <SinglePicture
                  key={el.artwork_id}
                  idx={idx}
                  array={array.length}
                  picture={el.imagePath}
                  title={el.title}
                  scrpit={el.content}
                  username={el.memberId}
                ></SinglePicture>
                <Footer like={el.likeCount} comment={el.commentCount}></Footer>
              </Body>
            </SwiperSlide>
          ))}
        </Swiper>
      )}
    </Body>
  );
};

export default SinglePicPage;
