import logo from "../img/logo-2.png"
function Menu(props) {
    if(props.funcao=="semCadastro"){
        return (
            <>
            <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"></link>
            <div className="menu">
                <img className="logo" src={logo}/>
                <span className="name">bp</span>
            </div>
            <span className="menuLateral">&#9776;</span>
            </>
        );
    }
    else{
        return (
            <>
            <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"></link>
            <div className="menu">
                <img className="logo" src={logo}/>
                <span className="name">bp</span>
                <div className="divDireita">
                    <button onClick={redirecionar}>Campanhas</button>
                    <span>&#128100;</span>
                    <span>&#128276;</span>
                </div>
                
            </div>
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onClick={closeNav}>&times;</a>
                <a href="perfilDoador.html">Perfil</a>
                <a href="Campanhas.html">Campanhas</a>
            </div>
            <span className="menuLateral" onClick={openNav}>&#9776;</span>
            </>
        );
    }
    
  }
  
  export default Menu;
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
function redirecionar(){
    window.location.href = "http://www.devmedia.com.br";
}