import { BrowserRouter, Route, Routes } from "react-router-dom";
import BpHome from "./pages/BpHome";
import CadastroOng from "./pages/CadastroOng";
import Login from "./pages/Login";
import Doacao from "./pages/Doacao";
import CadastroDoador from "./pages/CadastroDoador";
import CampanhasDoador from "./pages/CampanhasDoador";
import CampanhasOng from "./pages/CampanhasOng";
import Feed from "./pages/Feed";
import PerfilOng from "./pages/PerfilOng";
import PerfilDoador from "./pages/PerfilDoador"
import NotaFiscal from "./components/NotaFiscal";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="*" element={<BpHome />} />
                <Route path="/cadastro-ong" element={<CadastroOng />} />
                <Route path="/cadastro-doador" element={<CadastroDoador />} />
                <Route path="/login" element={<Login />} />
                <Route path="/doacao" element={<Doacao />} />
                <Route path="/campanhas-doador" element={<CampanhasDoador />} />
                <Route path="/campanhas-ong" element={<CampanhasOng />} />
                <Route path="/feed" element={<Feed/>} />
                <Route path="/PerfilOng" element={<PerfilOng/>} />
                <Route path="/PerfilDoador" element={<PerfilDoador/>} />
                <Route path="/NotaFiscal" element={<NotaFiscal/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;