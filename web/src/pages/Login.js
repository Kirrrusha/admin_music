import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {AuthMode} from '../utils/const';
import FormInput from "../components/login/FormInput";
import SubmitButton from "../components/login/SubmitButton";
import {Redirect} from "react-router-dom";

export class Login extends Component {
    static propTypes = {};

    state = {
        mode: 'Sign in',
        login: '',
        password: ''
    };

    onSubmit = () => {
        const {mode, login, password, username} = this.state;
        // if (mode === AuthMode.SIGN_IN) {
            this.props.signIn(login, password);
        // } else {
            // this.props.signUp(login, password, username);
        // }
    };

    onFieldChange = (field, event) => {
        const newState = {...this.state};
        newState[field] = event.target.value;
        this.setState(newState);
    };

    render() {
        const {login, password, mode} = this.state;
        const {isAuthenticated} = this.props;
        if (isAuthenticated) {
            return <Redirect to="/" />
        }

        return (
            <main>
                <div className="form">
                    <div className="form-title">authorization</div>
                    <FormInput
                        value={login}
                        onChange={event => this.onFieldChange('login', event)}
                        id="inputLogin"
                        label="Login"
                    />

                    <FormInput
                        value={password}
                        onChange={event => this.onFieldChange('password', event)}
                        secured={true}
                        id="inputPassword"
                        label="Password"
                    />

                    {/* <ErrorLabel message={error} /> */}

                    <SubmitButton onSubmit={this.onSubmit}>
                        {mode}
                    </SubmitButton>
                </div>
            </main>
        );
    }
}

export default Login;
