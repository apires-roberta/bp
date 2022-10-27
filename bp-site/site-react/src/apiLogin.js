import axios from "axios";

const apiLogin = axios.create({
    baseURL : "http://localhost:8080/"//http://52.0.52.140:8080
})

export default apiLogin;