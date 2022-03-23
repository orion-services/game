import React from 'react'

import { Router, Switch, Route } from 'react-router'

import Auth from '../pages/Auth';
import Home from '../pages/home'

import {history} from '../history'

const Routes = () => (
    <Router history={history}>
        <Switch>
            <Route component={Auth} exact path="/create"/>
            <PrivateRoute component={Home} exact path="/"/>
        </Switch>
    </Router>
)

export default Routes;