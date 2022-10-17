import axios from "axios";

const apiJaSegue = axios.create({
    baseURL : "http://localhost:8081/inscricao/"
})

export default apiJaSegue;