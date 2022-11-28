import axios from "axios";
import ipBack from "./ipBack";

const apiCampanha = axios.create({
    baseURL : `http://${ipBack}:8082/`
})

export default apiCampanha;