import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import CartaoFeed from "../components/CartaoFeed";
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import Rodape from "../components/Rodape";
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import CadastroFeed from '../components/CadastroFeed';
import ip from '../ip';

function Feed() {
    const [theme, setTheme] = useState("light");
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const toggleTheme = () => {
        if (theme === "light") {
            window.localStorage.setItem("theme", "dark");
            setTheme("dark");
        } else {
            window.localStorage.setItem("theme", "light");
            setTheme("light");
        }
    };
    useEffect(() => {
        const localTheme = window.localStorage.getItem("theme");
        localTheme && setTheme(localTheme);
    }, []);
    useEffect(() => {


    }, []);
    const estilo = {
        marginTop: "10%",
        float: "right",
        zIndex: "90",
        position: "relative",
        marginRight: "6%"
    }

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '33%',
        border: 'none',
        boxShadow: 24,
        borderRadius: "20px"
      };
    const estiloBotao={
        margin: "0"
    }
      function fechar(){
        if(open){
            handleClose()
        }
    }
    return (
        <>
            <Menu funcaoDark={toggleTheme} />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                    <CartaoFeed />
                    <Rodape />
                </Fragment>
            </ThemeProvider>
        </>
    );
}

export default Feed;

function redirecionar(pagina) {
    window.location.href = `http://${ip}/` + pagina;
  }

/*
<div style={estilo} onClick={fechar}>
                        <Fab onClick={handleOpen} color="inherit" aria-label="add" sx={`background-color: ${theme === "light" ? "#01396F" : "#0070DC"}; color: white; float: right; margin-right: 10%; position: relative;`}>
                            <AddIcon sx={estiloBotao}/>
                            <Modal open={open}>
                            <Box sx={style} className="caixa">
                             <CadastroFeed/>
                             </Box>
                            </Modal>
                        </Fab>
                    </div>*/