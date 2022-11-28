import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import CartaoFeedDoador from "../components/CartaoFeedDoador";
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import Rodape from "../components/Rodape";
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import ip from '../ip';

function FeedDoador() {
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
        marginBottom: "10%"
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
    return (
        <>
            <Menu funcaoDark={toggleTheme} />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                    <CartaoFeedDoador />
                    <Rodape />
                </Fragment>
            </ThemeProvider>
        </>
    );
}

export default FeedDoador;

function redirecionar(pagina) {
    window.location.href = `http://${ip}/` + pagina;
  }