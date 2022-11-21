import React from 'react';
import Header from 'shared/components/Header';
import UploadPicture from 'UploadPicture';
import AlarmList from 'AlarmList';
function App() {
  return (
    <>
      <Header></Header>
      <AlarmList></AlarmList>
      <UploadPicture></UploadPicture>
    </>
  );
}

export default App;
