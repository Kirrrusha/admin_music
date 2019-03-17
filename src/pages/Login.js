import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {AuthMode} from '../utils/const';
import FormInput from "../components/login/FormInput";
import SubmitButton from "../components/login/SubmitButton";

export class Login extends Component {
    static propTypes = {};

    state = {
        mode: 'Sign in',
        login: '',
        password: ''
    };

    onSubmit = () => {
        const {mode, login, password} = this.state;
    };

    onFieldChange = (field, event) => {
    };

    render() {
        const {login, password, mode} = this.state;
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
