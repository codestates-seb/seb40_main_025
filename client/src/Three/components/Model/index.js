import { useGLTF } from '@react-three/drei';
export default function Model(props) {
  const { nodes, materials } = useGLTF('/gallery/scene.gltf');

  return (
    <group {...props} dispose={null}>
      <group rotation={[-Math.PI / 2, 0, 0]}>
        <group rotation={[Math.PI / 2, 0, 0]}>
          <group position={[393.68, 0, 337.26]} rotation={[0, Math.PI / 2, 0]}>
            <group
              position={[34.5, 48.98, 107.5]}
              rotation={[0, -Math.PI / 2, 0]}
            >
              <mesh
                geometry={nodes.Handles_Frame_0.geometry}
                material={materials.Frame}
              />
              <mesh
                geometry={nodes.Handles_Frame_0_1.geometry}
                material={materials.Frame}
              />
              <mesh
                geometry={nodes.Handles_Frame_0_2.geometry}
                material={materials.Frame}
              />
            </group>
            <group position={[-129.67, 45, -265.14]}>
              <mesh
                geometry={nodes.Door_2_Frame_0.geometry}
                material={materials.Frame}
              />
            </group>
            <group position={[0, -0.2, 6.25]}>
              <mesh
                geometry={nodes.Frame_Frame_0.geometry}
                material={materials.Frame}
              />
            </group>
          </group>
          <group position={[0, 146.09, 0]}>
            <group position={[390, 0, 533.33]} rotation={[0, -Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_2_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_Image_1_0.geometry}
                  material={materials.Image_1}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[390, 0, 800]} rotation={[0, -Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_3_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_2_Image_2_0.geometry}
                  material={materials.Image_2}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_2_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group
              position={[240, 0, 987.41]}
              rotation={[Math.PI, 0, -Math.PI]}
            >
              <mesh
                geometry={nodes.Frame_4_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_3_Image_3_0.geometry}
                  material={materials.Image_3}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_3_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[0, 0, 987.41]} rotation={[Math.PI, 0, -Math.PI]}>
              <mesh
                geometry={nodes.Frame_5_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_4_Image_4_0.geometry}
                  material={materials.Image_4}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_4_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group
              position={[-240, 0, 987.41]}
              rotation={[Math.PI, 0, -Math.PI]}
            >
              <mesh
                geometry={nodes.Frame_6_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_5_Image_5_0.geometry}
                  material={materials.Image_5}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_5_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[-390, 0, 800]} rotation={[0, Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_7_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_6_Image_6_0.geometry}
                  material={materials.Image_6}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_6_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[-390, 0, 533.33]} rotation={[0, Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_8_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_7_Image_7_0.geometry}
                  material={materials.Image_7}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_7_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[-390, 0, 266.67]} rotation={[0, Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_9_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_8_Image_8_0.geometry}
                  material={materials.Image_8}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_8_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[-390, 0, 0]} rotation={[0, Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_10_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_9_Image_9_0.geometry}
                  material={materials.Image_9}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_9_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[-390, 0, -266.67]} rotation={[0, Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_11_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_10_Image_10_0.geometry}
                  material={materials.Image_10}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_10_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[-390, 0, -533.33]} rotation={[0, Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_12_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_11_Image_11_0.geometry}
                  material={materials.Image_11}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_11_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[-390, 0, -800]} rotation={[0, Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_13_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_12_Image_12_0.geometry}
                  material={materials.Image_12}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_12_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[-240, 0, -989.97]}>
              <mesh
                geometry={nodes.Frame_14_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_13_Image_13_0.geometry}
                  material={materials.Image_13}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_13_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[0, 0, -989.97]}>
              <mesh
                geometry={nodes.Frame_15_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_14_Image_14_0.geometry}
                  material={materials.Image_14}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_14_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[240, 0, -989.97]}>
              <mesh
                geometry={nodes.Frame_16_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_15_Image_15_0.geometry}
                  material={materials.Image_15}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_15_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[390, 0, -749.62]} rotation={[0, -Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_17_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_16_Image_16_0.geometry}
                  material={materials.Image_16}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_16_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[390, 0, -482.95]} rotation={[0, -Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_18_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_17_Image_17_0.geometry}
                  material={materials.Image_17}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_17_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[390, 0, -216.28]} rotation={[0, -Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_19_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_18_Image_18_0.geometry}
                  material={materials.Image_18}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_18_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
            <group position={[390, 0, 50.38]} rotation={[0, -Math.PI / 2, 0]}>
              <mesh
                geometry={nodes.Frame_20_Orange_Plastic_2_0.geometry}
                material={materials.Orange_Plastic_2}
              />
              <group position={[0, 0, 1.41]}>
                <mesh
                  geometry={nodes.Image_19_Image_19_0.geometry}
                  material={materials.Image_19}
                />
              </group>
              <group position={[0, 0, 0.62]}>
                <mesh
                  geometry={nodes.Border_19_Greyish_0.geometry}
                  material={materials.Greyish}
                />
              </group>
            </group>
          </group>
          <mesh
            geometry={nodes['__Floor_1_--Floor_0'].geometry}
            material={materials['--Floor']}
          />
          <group position={[0, 289.47, 0.05]}>
            <mesh
              geometry={nodes.Steelwork_Steelwork_0.geometry}
              material={materials.Steelwork}
            />
          </group>
          <group position={[0, 413.13, 0]}>
            <mesh
              geometry={nodes.Walls_Walls_0.geometry}
              material={materials.Walls}
            />
          </group>
          <group position={[0, 410.04, 0.66]}>
            <mesh
              geometry={nodes.Glass_Dirty_White_Glass_0.geometry}
              material={materials.Dirty_White_Glass}
            />
          </group>
          <group position={[55.06, 250, -52]}>
            <mesh
              geometry={nodes.Window_Frames_Window_Frames_0.geometry}
              material={materials.Window_Frames}
            />
          </group>
        </group>
      </group>
    </group>
  );
}
