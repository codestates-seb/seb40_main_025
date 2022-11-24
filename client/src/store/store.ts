import create from 'zustand';

interface ModalState {
  AlertModal: boolean;
  ProfileModal: boolean;
}

interface Modal {
  target: ModalState;
  openModal: (key: string) => void;
  closeModal: (key: string) => void;
  resetTarget: () => void;
}
const initTarget: ModalState = {
  AlertModal: false,
  ProfileModal: false,
};

const ModalStore = create<Modal>((set, get) => ({
  target: { ...initTarget },
  openModal: (key) =>
    set({ target: { ...Object.assign({ ...get().target }, { [key]: true }) } }),
  closeModal: (key) =>
    set({
      target: { ...Object.assign({ ...get().target }, { [key]: false }) },
    }),
  resetTarget: () =>
    set(() => {
      return { target: { ...initTarget } };
    }),
}));

interface Alarm {
  alarmIsOpen: boolean;
  openAlarm: () => void;
  closeAlarm: () => void;
}

const AlarmStore = create<Alarm>((set) => {
  return {
    alarmIsOpen: false,
    openAlarm: () =>
      set(() => {
        return { alarmIsOpen: true };
      }),
    closeAlarm: () =>
      set(() => {
        return { alarmIsOpen: false };
      }),
  };
});

interface SubToastState {
  time: number;
  content: string[];
}
interface ToastState extends SubToastState {
  id: number;
}

interface Components {
  ToastList: ToastState[];
  addToast: (data: SubToastState) => void;
  removeToast: () => void;
}

const ToastStore = create<Components>((set, get) => ({
  ToastList: [],
  addToast: (data) =>
    set(() => {
      let arr = get().ToastList.slice();
      arr.push({ ...Object.assign({}, data, { id: Math.random() }) });
      return {
        ToastList: arr,
      };
    }),
  removeToast: () => set({ ToastList: [...get().ToastList.slice(1)] }),
}));

interface UploadState {
  img: File | undefined;
  title: string;
  content: string;
  [key: string]: File | undefined | string;
}

interface Upload {
  UploadData: UploadState;
  setData: (key: string, data: File | string) => void;
  removeData: () => void;
}

const initUploadData = {
  img: undefined,
  title: '',
  content: '',
};

const UploadStore = create<Upload>((set, get) => ({
  UploadData: { ...initUploadData },
  setData: (key, data) =>
    set({
      UploadData: { ...Object.assign(get().UploadData, { [key]: data }) },
    }),
  removeData: () => set({ UploadData: { ...initUploadData } }),
}));
export { ModalStore, AlarmStore, ToastStore, UploadStore };
