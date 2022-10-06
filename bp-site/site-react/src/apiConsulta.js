import axios from "axios";

const apiConsulta = axios.create({
    baseURL : "http://localhost:8080/bp"
})

export default apiConsulta;