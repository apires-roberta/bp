import { createGlobalStyle } from "styled-components";

export default createGlobalStyle`
  body {
    background: ${({ theme }) => theme.body};
    transition: all 0.25s linear;
  }
`;
