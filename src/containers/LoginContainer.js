import {signIn} from "../modules/actions";
import {connect} from "react-redux";
import {Login} from "../pages/Login";

const mapStateToProps = ({auth: {error, loading, roles, isAuthenticated}}) => ({
    error,
    loading,
    roles,
    isAuthenticated
});

const mapDispatchToProps = dispatch => {
    console.log(`dispatch = ${dispatch}`);
    return ({
        signIn: (login, password) => dispatch(signIn(login, password)),
        // signUp: (username, login, password, roleId) => dispatch(signUp(username, login, password, roleId))
    })
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);