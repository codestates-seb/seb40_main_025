import create from 'zustand';

interface Comment {
  open: boolean;
  setOpenModal: () => void;
  setCloseModal: () => void;
}

const CommentStore = create<Comment>((set) => ({
  open: false,
  setOpenModal: () => set(() => ({ open: true })),
  setCloseModal: () => set(() => ({ open: false })),
}));

export default CommentStore;
