import axios from 'axios';

console.log("axios hi");

//json용도
const jsonInstance = axios.create({
  baseURL: 'http://localhost:4000',
  timeout: 1000,
  headers: {
    // 'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
    'ngrok-skip-browser-warning': 'any',
  },
  // withCredentials: true,
});

//form-data용도
const formdataInstance = axios.create({

  baseURL: 'https://3770-211-210-144-9.jp.ngrok.io',
  timeout: 1000,
  headers: {
    'Content-Type': 'multipart/form-data',
    'ngrok-skip-browser-warning': 'any',
  },
});

export { jsonInstance, formdataInstance };
