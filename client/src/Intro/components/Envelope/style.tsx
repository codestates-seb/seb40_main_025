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

export const Letter = styled.div`
  position: relative;
  background-color: #fff;
  width: 90%;
  margin-left: auto;
  margin-right: auto;
  height: 90%;
  top: 5%;
  border-radius: 6px;
  box-shadow: 0 2px 26px rgba(0, 0, 0, 0.12);
  padding: 0.5rem;
  text-align: center;

  &.close {
    transform: translateY(0px);
    transition: transform 0.4s ease, z-index 1s;
    z-index: 1;
  }

  &.open {
    /* TODO: 여기서 translate 값과 크기 값을 늘려 화면을 가득 채우도록 변경 */
    transform: translateY(-200px);
    /* transform: translateY(-60px); */
    transition: transform 0.4s 0.6s ease, z-index 0.6s;
    z-index: 2;
  }

  &:after {
    content: '';
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-image: linear-gradient(
      180deg,
      rgba(255, 255, 255, 0) 25%,
      rgba(215, 227, 239, 0.7) 70%,
      #d7e3ef 100%
    );
  }
`;

export const Word = styled.div`
  position: absolute;
  left: 10%;
  width: 80%;
  height: 14%;
  background-color: #eeeff0;
`;

export const Container = styled.div`
  .open .flap {
    transform: rotateX(180deg);
    transition: transform 0.4s ease, z-index 0.6s;
    z-index: 1;
  }

  .close .flap {
    transform: rotateX(0deg);
    transition: transform 0.4s 0.6s ease, z-index 1s;
    z-index: 5;
  }
`;

export const OpenBtn = styled.button`
  font-weight: 800;
  font-style: normal;
  transition: all 0.1s linear;
  -webkit-appearance: none;
  background-color: transparent;
  border: solid 2px ${({ theme }) => theme.colors.red_001};
  border-radius: 4px;
  color: ${({ theme }) => theme.colors.red_001};
  display: inline-block;
  font-size: 14px;
  text-align: center;
  text-transform: uppercase;
  margin: 5px;
  padding: 10px;
  line-height: 1em;
  text-decoration: none;
  min-width: 120px;
  cursor: pointer;

  :hover {
    background-color: ${({ theme }) => theme.colors.red_001};
    color: #fff;
  }
`;
