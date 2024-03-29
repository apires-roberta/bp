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
    width: '33%',
    border: 'none',
    boxShadow: 24,
    borderRadius: "20px"
  };
  const DivInfo = styled.div`
        width: 23.8%;
        margin-left: 7%;
        margin-top: 4%;
        float: left;
        box-shadow: ${({ theme }) => theme.borda};
        border-radius: 10px;
        color: ${({ theme }) => theme.logo};
        border: ${({ theme }) => theme.bordaInfo} 2px solid;
        height: 43vh;
        text-align: center;
    `;

  const estiloDiv = {
    marginBottom: "5%"
  };

  const estiloP = {
    fontSize: "160px",
    paddingTop: "22%"
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
          <CadastroCampanha/>
        </Box>
      </Modal>
    </div>
  );
}
