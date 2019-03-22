import {signIn} from '../modules/actions';
import {connect} from 'react-redux';
import {Login} from '../pages/Login';

const mapStateToProps = ({auth: {error, loading, isAuthenticated}}) => ({
    error,
    loading,
    isAuthenticated
});

const mapDispatchToProps = dispatch => {
    return ({
        signIn: (login, password) => dispatch(signIn(login, password)),
        // signUp: (username, login, password, roleId) => dispatch(signUp(username, login, password, roleId))
    })
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);