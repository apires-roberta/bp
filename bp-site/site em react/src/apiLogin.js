import axios from "axios";

const apiLogin = axios.create({
    baseURL : "http://localhost:8081/"
})

export default apiLogin;