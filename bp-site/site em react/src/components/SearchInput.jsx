import React, { useState } from 'react';
import useDebounce from './useDebounce';
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import styled from "styled-components";

const SearchInput = ({ value, onChange }) => {
  const [displayValue, setDisplayValue] = useState(value);
  const debouncedChange = useDebounce(onChange, 500);

  function handleChange(event) {
    setDisplayValue(event.target.value);
    debouncedChange(event.target.value);
  }

  const inputEstilo={
    fontSize: "20px",
    color: "white",
    backgroundColor:"#01396F",
    borderRadius:"50px",
    paddingLeft:"20px",
    border:"none",
    width: "400px",
    height: "40px",
}

  return (
    <input 
      style={inputEstilo}
      type="search"
      value={displayValue}
      onChange={handleChange}
      placeholder="Pesquisar"
    />
  );
};

export default SearchInput;
