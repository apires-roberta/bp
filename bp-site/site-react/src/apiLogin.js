import axios from "axios";
import ipBack from "./ipBack";

const apiLogin = axios.create({
    baseURL : `http://${ipBack}:8080/`//http://52.0.52.140:8080
})

export default apiLogin;