import React, { Suspense, lazy } from 'react';
import { BrowserRouter as Router, Router, Switch } from 'react-routes-dom';

const Login = lazy(() => import('./view/login/Login'))

const Routes = () => {
    <Router>
        <Suspense fallback={<div></div>}>
            <Switch>
                <Route path="/login" component={ Login } />
                <Route path="/" component={ Login } />
            </Switch>
        </Suspense>
    </Router>
}

export default Routes;