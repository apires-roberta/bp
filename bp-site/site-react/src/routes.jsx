import { BrowserRouter, Route, Routes } from "react-router-dom";
import BpHome from "./pages/BpHome";
import CadastroOng from "./pages/CadastroOng";
import Login from "./pages/Login";
import Doacao from "./pages/Doacao";
import CadastroDoador from "./pages/CadastroDoador";
import Feed from "./pages/Feed";
import DashMensal from "./pages/DashMensal";
import DashDetalhado from "./pages/DashDetalhado";
import DashMapa from "./pages/DashMapa";
import Perfil from "./pages/Perfil";
import Campanha from "./pages/Campanha";
import BpNotaFiscal from "./pages/bpNotaFiscal";
import VerPerfil from "./pages/VerPerfil";
import HistoricoDoacao from "./pages/HistoricoDoacao"
import AlterarDados from "./pages/AlterarDados";
import Erro from "./pages/Erro";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="*" element={<Erro />} />
                <Route path="/" element={<BpHome />} />
                <Route path="/cadastro/ong" element={<CadastroOng />} />
                <Route path="/cadastro/doador" element={<CadastroDoador />} />
                <Route path="/login" element={<Login />} />
                <Route path="/campanha/doacao" element={<Doacao />} />
                <Route path="/feed" element={<Feed/>} />
                <Route path="/NotaFiscal" element={<BpNotaFiscal/>} />
                <Route path="/Dashboard" element={<DashMensal/>} />
                <Route path="/DashboardDetalhado" element={<DashDetalhado/>} />
                <Route path="/DashboardMapa" element={<DashMapa/>} />
                <Route path="/Perfil" element={<Perfil/>} />
                <Route path="/Campanha" element={<Campanha/>} />
                <Route path="/PerfilResultado" element={<VerPerfil/>} />
                <Route path="/Historico" element={<HistoricoDoacao/>} />
                <Route path="/AlterarDados" element={<AlterarDados/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;