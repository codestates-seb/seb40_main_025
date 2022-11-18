import React from 'react';
import UploadPicture from 'UploadPicture';
import Header from 'shared/components/Header';
import ModalContainer from 'shared/components/Modal';
function App() {
  return (
    <div className="App">
      <ModalContainer></ModalContainer>
      <Header></Header>
      <UploadPicture></UploadPicture>
    </div>
  );
}

export default App;
