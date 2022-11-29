import create from 'zustand';
// import { UserType, ID, Login } from './types';

interface UserType {
  nickname?: string;
  profile?: string;
}

interface Login {
  isLoggedin: boolean;
  setIsLoggedIn: () => void;
  // user: UserType | undefined;
  // setLoggedIn: (data: UserType) => void;
  // setLoggedOut: () => void;
}

const loginStore = create<Login>((set) => ({
  isLoggedin: false,
  setIsLoggedIn: () => set(() => ({ isLoggedin: true })),

  // user: {},
  // setLoggedIn: () => set((state) => ({ user: state })),
  // setLoggedOut: () => set(() => ({ user: {} })),
}));

// const IDStore = create<ID>((set) => ({
//   galleryId: 0,
//   setGalleryId: (galleryId: number) => {
//     set((state: any) => ({ galleryId: galleryId }));
//   },
// }));

// export { loginStore, IDStore };
export { loginStore };
