import { Canvas } from '@react-three/fiber';
import { PerspectiveCamera, PresentationControls } from '@react-three/drei';
import * as S from './style';

import { Suspense } from 'react';
import Model from './components/Model';

const Index = () => {
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
