import axios from "axios";
import ipBack from "./ipBack";

const apiFeed = axios.create({    
    baseURL : `http://${ipBack}:8081/`
})

export default apiFeed;