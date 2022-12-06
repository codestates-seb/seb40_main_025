import { Canvas, useFrame } from '@react-three/fiber';
import {
  ScrollControls,
  Scroll,
  Image,
  OrbitControls,
  PerspectiveCamera,
  Stars,
  KeyboardControls,
  KeyboardControlsEntry,
  PresentationControls,
} from '@react-three/drei';
import * as S from './style';

import { Suspense, useMemo, useRef } from 'react';
import Model from './components/Model';

const Index = () => {
  // const ref = useRef<Mesh>(null!);
  // document.addEventListener('keydown', onKeyDown, false);
  // // function when a key is pressed, execute this function

  // useFrame(() => {
  //   ref.current.rotation.x += 0.01;
  // });
  // function onKeyDown(event: any) {
  //   let keycode = event.which;

  //   // right arrow key
  //   if (keycode === 39) {
  //     camera.translateX(-0.05);
  //   }
  //   // left arrow key
  //   else if (keycode === 37) {
  //     camera.translateX(0.05);
  //   }
  //   // up arrow key
  //   else if (keycode === 38) {
  //     camera.translateY(-0.05);
  //   }
  //   // down arrow key
  //   else if (keycode === 40) {
  //     camera.translateY(0.05);
  //   }
  // }
  return (
    <S.Box>
      {/* dpr은 디바이스 크기에 따라 반응형으로 보이도록 함 */}
      <Canvas>
        <PerspectiveCamera makeDefault position={[0, 150, 0]} />
        <PresentationControls
          enabled={true} // the controls can be disabled by setting this to false
          global={false} // Spin globally or by dragging the model
          cursor={true} // Whether to toggle cursor style on drag
          snap={false} // Snap-back to center (can also be a spring config)
          speed={1} // Speed factor
          zoom={1} // Zoom factor when half the polar-max is reached
          rotation={[0, 200, 0]} // Default rotation
          polar={[0, Math.PI / 2]} // Vertical limits
          azimuth={[-Infinity, Infinity]} // Horizontal limits
          config={{ mass: 1, tension: 170, friction: 26 }} // Spring config
        >
          <Suspense fallback={null}>
            <ambientLight intensity={1.2} />
            <Model />
          </Suspense>
        </PresentationControls>
      </Canvas>
    </S.Box>
  );
};

export default Index;
