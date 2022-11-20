import create from 'zustand';

interface ModalState {
  AlertModal: boolean;
  ProfileModal: boolean;
}

const initTarget: ModalState = {
  AlertModal: false,
  ProfileModal: false,
};

interface Modal {
  target: ModalState;
  openModal: (key: string) => void;
  closeModal: (key: string) => void;
  resetTarget: () => void;
}

const ModalStore = create<Modal>((set, get) => ({
  target: { ...initTarget },
  openModal: (key) =>
    set({ target: { ...Object.assign({ ...get().target }, { [key]: true }) } }),
  closeModal: (key) =>
    set({ target: { ...Object.assign({ ...get().target }, { [key]: false }) } }),
  resetTarget: () =>
    set(() => {
      return { target: { ...initTarget } };
    }),
}));

export { ModalStore };
