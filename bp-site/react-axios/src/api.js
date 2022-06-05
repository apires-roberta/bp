import axios from 'axios';

const api = axios.create({
    baseURL : "https://6286d0a6e9494df61b2db910.mockapi.io/musicas"
})

export default api;
