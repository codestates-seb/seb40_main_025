import React from 'react';
import Header from 'shared/components/Header';
import UploadPicture from 'UploadPicture';
import AlarmList from 'AlarmList';
import Toast from 'shared/components/Toast';
function App() {
  return (
    <>
      <Header></Header>
      {/* <AlarmList></AlarmList> */}
      {/* <UploadPicture></UploadPicture> */}
      <Toast time={1000}/>
      <Toast time={2000}/>
      <Toast time={3000}/>
      <Toast time={4000}/>
      <Toast time={5000}/>
      <Toast time={6000}/>
      <Toast time={7000}/>
    </>
  );
}

export default App;
