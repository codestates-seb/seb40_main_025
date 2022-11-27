import User from '../../userType';

const USER_LOCALSTORAGE_KEY = 'thisyear_user';

// 로컬 스토리지 읽기
export const getStoredUser = (): User | null => {
  const storedUser = localStorage.getItem(USER_LOCALSTORAGE_KEY);
  return storedUser ? JSON.parse(storedUser) : null;
};

// 로컬 스토리지에 추가
export const setStoredUser = (user: User): void => {
  localStorage.setItem(USER_LOCALSTORAGE_KEY, JSON.stringify(user));
};

// 로컬 스토리지에서 제거
export const clearStoredUser = (): void => {
  localStorage.removeItem(USER_LOCALSTORAGE_KEY);
};
