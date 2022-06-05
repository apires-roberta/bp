import { BrowserRouter, Routes, Route } from "react-router-dom";
import BpHome from "./pages/BpHome";
import CampanhasDoador from "./pages/CampanhasDoador";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="*" element={<BpHome />} />
                <Route path="/campanhas" element={<CampanhasDoador />} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;