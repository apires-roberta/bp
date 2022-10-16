import axios from "axios";

const apiFeed = axios.create({
    baseURL : "http://localhost:8081/"
})

export default apiFeed;