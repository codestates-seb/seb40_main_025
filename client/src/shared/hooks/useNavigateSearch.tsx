import { useNavigate, createSearchParams, URLSearchParamsInit } from 'react-router-dom';
import { ModalStore } from 'store/store';
export const useNavigateSearch = () => {
  const navigate = useNavigate();
  const { resetModal } = ModalStore();
  return (pathname: string, params: URLSearchParamsInit) => {
    navigate({ pathname, search: `${createSearchParams(params)}` });
    resetModal();
  }
};
