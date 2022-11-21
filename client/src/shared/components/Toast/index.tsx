import { useEffect, useState } from 'react';
import * as T from './components/ToastContainer';
const Toast = ({ time }: { time: number }) => {
  const [show, setShow] = useState(false);
  useEffect(() => {
    setTimeout(() => setShow(true), time);
    setTimeout(() => setShow(false), time+2000);
  },[]);
  return (
    <>
      {show && (
        <T.ToastBox>
          <div className='ToastContent'>
            <label>
              작품이 등록되었습니다! <br></br>내 전시관도 만들어보기
            </label>
            <T.OptionSVG onClick={() => setShow(false)} />
          </div>
          <T.ProgressBar className='ProgressBar'></T.ProgressBar>
        </T.ToastBox>
      )}
    </>
  );
};

export default Toast;
