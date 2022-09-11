import axios from "axios";

const apiCampanha = axios.create({
    baseURL : "http://localhost:8082/"
})

export default apiCampanha;