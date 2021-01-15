import axios from 'axios';
import {logout} from '../services/auth';

var ZavrsniAxios = axios.create({
    baseURL: 'http://localhost:8080/api' 
});

ZavrsniAxios.interceptors.request.use(
    function success(config){
      let token = window.localStorage.getItem("token");
      if(token){
        config.headers["Authorization"] = `Bearer ${token}`;
      }
  
      return config;
    }
  );
  
  ZavrsniAxios.interceptors.response.use(
    function uspeh(response){
      return response;
    },
    function neuspeh(error){
      let token = window.localStorage.getItem("token");
  
      if(token){
        if(error.response && error.response.status == 403){
          logout();
        }
      }
  
      throw error;
    }
  )

export default ZavrsniAxios;