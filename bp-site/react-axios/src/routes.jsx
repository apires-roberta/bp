import { BrowserRouter, Routes, Route } from "react-router-dom";
import CampanhasDoador from "./pages/CampanhasDoador";

function Rotas(){
    return(
        <BrowserRouter>
        <Routes>
            <Route path="*" element={<CampanhasDoador/>}/>
        </Routes>
        </BrowserRouter>
    );
}

export default Rotas;