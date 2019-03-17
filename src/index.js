import React from 'react';
import ReactDOM from 'react-dom';
import './index.scss';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {Provider} from 'react-redux';
import store from './store';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import {protect} from "./containers/ProtectContainer";
import {Login} from "./pages/Login";

ReactDOM.render(
    <BrowserRouter>
        <Provider store={store}>
            <Switch>
                <Route exact path="/login" component={Login}/>
                <Route path="/" component={App}/>
            </Switch>
        </Provider>
    </BrowserRouter>,
    document.getElementById('root')
);

serviceWorker.unregister();
