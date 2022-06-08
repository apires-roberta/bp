import React, { Fragment, useState, useEffect } from "react";
import "./css/index.css"
import Rotas from "./routes";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "./theme";
import GlobalTheme from "./globals";
import styled from "styled-components";
import './css/bp-home.css';
import BpHome from "./pages/BpHome";

function App() {
  return (
    <Rotas/>
  );
}

export default App;