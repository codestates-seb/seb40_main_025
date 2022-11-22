import { useEffect, useState } from 'react';
import * as T from './components/ToastContainer';

const Toast = ({ time, content }: { time: number, content: string[] }) => {
  const [show, setShow] = useState(true);
  return (
    <>
      {show && (
        <T.ToastBox time = {time}>
          <div className='ToastContent'>
            <label>
              {content[0]} <br></br>{content[1]}
            </label>
            <T.OptionSVG onClick={() => setShow(false)} />
          </div>
          <T.ProgressBar className='ProgressBar' time = {time}></T.ProgressBar>
        </T.ToastBox>
      )}
    </>
  );
};

export default Toast;
