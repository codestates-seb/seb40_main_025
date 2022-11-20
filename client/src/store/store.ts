import create from 'zustand';

interface ModalState {
  AlertModal: boolean;
  ProfileModal: boolean;
}

const initTarget : ModalState ={
  AlertModal: false,
  ProfileModal: false,
}

interface Modal {
  target: ModalState;
  setModal: (obj: ModalState) => void;
  resetTarget: ()=>void;
}

const ModalStore = create<Modal>((set) => ({
  target: {...initTarget},
  setModal: (obj) => set({ target: obj }),
  resetTarget: () =>
    set(() => {
      return { target: { ...initTarget } };
    }),
}));

export { ModalStore };
