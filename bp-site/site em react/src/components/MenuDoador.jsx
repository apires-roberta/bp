import logo from '../img/logo.png'

function MenuDoador() {
  function abrirMenu() {
    document.getElementById("side_menu").style.width = "30%";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
  }
  
  function fecharMenu() {
    document.getElementById("side_menu").style.width = "0%";
    document.body.style.backgroundColor = "white";
  }
  return (
    <>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"/>
 
    <div class="menu">
      <div class="menu-movel">
        <div class="div-logo-menu">
          <img src={logo} alt="logo" class="logo" />
          <h1>bp</h1>
          <button class="botao-menu-haburguer" onclick={abrirMenu}><i class="fa fa-bars fa-4x" aria-hidden="true"></i></button>
      </div>
      <div class="div-botoes-menu">
          <button class="botao-nav"><i class="fa fa-user fa-1x" aria-hidden="true"></i></button>
          <button class="botao-nav"><i class="fa fa-bell fa-1x" ></i></button>
      </div>
      </div>
    </div>

    <div id="side_menu" class="side-menu">
        <a class="botao-fechar" onclick={fecharMenu}><i class="fa fa-times" aria-hidden="true"></i></a>
        <div class="menu-opcao">
          <a>Configurações</a><br/><br/>
          <a>Sair</a>
        </div>
    </div>
    </>
  );
  
}

export default MenuDoador;
