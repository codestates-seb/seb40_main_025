import React from 'react';
import { rem } from 'polished';
import styled from 'styled-components';

export const EnvelopeWrapper = styled.div`
  ${({ theme }) => theme.mixins.flexBox('column', 'center', 'center')};

  body {
    background-color: #a8e2ff;
  }

  height: 380px;
`;

export const Envelope = styled.div`
  position: relative;
  width: 280px;
  height: 180px;
  border-bottom-left-radius: 6px;
  border-bottom-right-radius: 6px;
  margin-left: auto;
  margin-right: auto;
  top: 150px;
  background-color: ${({ theme }) => theme.colors.red_006};
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);

  animation: swing ease-in-out 0.2s infinite alternate;
  transform-origin: center -20px;
  float: left;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  &.open .flap {
    transform: rotateX(180deg);
    transition: transform 0.4s ease, z-index 0.6s;
    z-index: 1;
  }

  &.close .flap {
    transform: rotateX(0deg);
    transition: transform 0.4s 0.6s ease, z-index 1s;
    z-index: 5;
  }
  &.open {
    animation-play-state: paused;
  }

  @keyframes swing {
    0% {
      transform: rotate(3deg);
    }
    100% {
      transform: rotate(-3deg);
    }
  }
`;

export const Front = styled.div`
  position: absolute;
  width: 0;
  height: 0;
  z-index: 3;

  &.flap {
    border-left: 140px solid transparent;
    border-right: 140px solid transparent;
    border-bottom: 82px solid transparent;
    /* a little smaller */
    border-top: 98px solid ${({ theme }) => theme.colors.red_006};
    /* a little larger */
    transform-origin: top;
    pointer-events: none;
  }

  &.pocket {
    border-left: 140px solid ${({ theme }) => theme.colors.red_001};
    border-right: 140px solid ${({ theme }) => theme.colors.red_001};
    border-bottom: 90px solid ${({ theme }) => theme.colors.red_007};
    border-top: 90px solid transparent;
    border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
  }
`;
