//BrowserRouter -> Acesso ao uso das rotas
//Switch -> Navegação entre as rotas
//Route -> Criar uma rota

import React from 'react'
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom'

//Import dos componentes/pages
import Index from './pages/Index'

function Routes(){
    return(
        <Router>
            <Switch>
                <Route exact path="/" component={Index}/>
            </Switch>
        </Router>
    );
}

export default Routes;