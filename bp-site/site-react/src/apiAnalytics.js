import axios from "axios";

const apiAnalytics = axios.create({
    baseURL : "http://localhost:5000/"
})

export default apiAnalytics;