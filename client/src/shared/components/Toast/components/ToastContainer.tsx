import styled from 'styled-components';
import { rem } from 'polished';

const ToastBox = styled.div`
  width: ${rem(395)};
  height: ${rem(64)};
  margin: ${rem(18)} ${rem(18)} 0 ${rem(18)};
  border-radius: ${rem(5)};
  box-shadow: 2px 1px 1px gray, -2px 1px 1px gray;
  display: flex;
  flex-direction: column;
  align-items: center;
  animation: ToastShow 2s linear forwards;

  .ToastContent {
    width: ${rem(250)};
    display: flex;
    align-items: center;
    margin: ${rem(10)} 0 ${rem(5)} ${rem(100)};

    label{
      margin-right: ${rem(50)};
    }
  }
  @keyframes ToastShow {
    from {
      opacity: 0;
    }
    30%{
      opacity: 1;
    } 
    70%{
        opacity: 1;
    }
    to{
      opacity: 0;
    }
  }
`;

const ProgressBar = styled.div`
  width: ${rem(395)};
  height: ${rem(4)};
  background-color: ${({ theme }) => theme.colors.green_007};
  position: relative;
  border-radius: ${rem(10)};

  @keyframes progress {
    from {
      width: ${rem(0)};
    }
    to {
      width: ${rem(395)};
    }
  }

  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 0;
    height: ${rem(4)};
    background-color: ${({ theme }) => theme.colors.green_006};
    animation: progress 2s linear forwards;
  }
`;

const OptionSVG = ({onClick} : {onClick: React.MouseEventHandler<SVGSVGElement>}) => {
  return (
    <svg
      width='24'
      height='24'
      viewBox='0 0 24 24'
      fill='none'
      xmlns='http://www.w3.org/2000/svg'
      className='OptionSVG'
      onClick = {onClick}
    >
      <path
        d='M15.6258 11.8475L9.86016 7.67566C9.83214 7.65532 9.79903 7.64313 9.76451 7.64045C9.72999 7.63778 9.6954 7.64471 9.66457 7.66048C9.63375 7.67626 9.6079 7.70026 9.58988 7.72983C9.57186 7.7594 9.56239 7.79338 9.5625 7.82801V8.92723C9.5625 9.16629 9.67735 9.39363 9.87188 9.53426L13.2797 11.9999L9.87188 14.4655C9.67735 14.6061 9.5625 14.8311 9.5625 15.0725V16.1718C9.5625 16.3241 9.73594 16.4132 9.86016 16.3241L15.6258 12.1522C15.7289 12.0772 15.7289 11.9225 15.6258 11.8475Z'
        fill='#282B34'
      />
      <path
        d='M12 1.5C6.20156 1.5 1.5 6.20156 1.5 12C1.5 17.7984 6.20156 22.5 12 22.5C17.7984 22.5 22.5 17.7984 22.5 12C22.5 6.20156 17.7984 1.5 12 1.5ZM12 20.7188C7.18594 20.7188 3.28125 16.8141 3.28125 12C3.28125 7.18594 7.18594 3.28125 12 3.28125C16.8141 3.28125 20.7188 7.18594 20.7188 12C20.7188 16.8141 16.8141 20.7188 12 20.7188Z'
        fill='#282B34'
      />
    </svg>
  );
};

export { ToastBox, ProgressBar, OptionSVG };
