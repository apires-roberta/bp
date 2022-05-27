import axios from "axios";

const api = axios.create({
    baseURL : "https://6286d0ab7864d2883e7a1350.mockapi.io/musicas"
})

export default api;
