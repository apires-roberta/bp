import axios from "axios";

const apiLogin = axios.create({
    baseURL : "http://localhost:8080/"
})

export default apiLogin;