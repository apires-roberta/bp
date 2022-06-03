import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import CadastroCampanha from '../components/CadastroCampanha';
import styled from "styled-components";

export default function BasicModal() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    border: 'none',
    boxShadow: 24,
    borderRadius: "20px"
  };
  const DivInfo = styled.div`
        width: 24.5%;
        margin-left: 8%;
        margin-bottom: 5%;
        float: left;
        box-shadow: ${({ theme }) => theme.borda};
        border-radius: 10px;
        color: ${({ theme }) => theme.logo};
        border: ${({ theme }) => theme.bordaInfo} 2px solid;
        height: 38.4vh;
        text-align: center;
    `;

  const estiloDiv = {
    marginBottom: "5%"
  };

  const estiloP = {
    fontSize: "160px",
    paddingTop: "18%"
  };
  return (
    <div>
      <DivInfo onClick={handleOpen} style={estiloDiv}>
        <p style={estiloP}>+</p>
      </DivInfo>
      <Modal
        open={open}
        onClose={handleClose}
      >
        <Box sx={style} className="caixa">
          <CadastroCampanha></CadastroCampanha>
        </Box>
      </Modal>
    </div>
  );
}
