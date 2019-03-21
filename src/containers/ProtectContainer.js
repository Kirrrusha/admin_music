import React from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';
import Loader from 'react-loader-spinner'
import {me} from "../modules/actions";

export const protect = WrappedComponent => {
  class AuthenticatedComponent extends React.Component {

      componentWillMount() {
      const { isAuthenticated, me } = this.props;
      if (!isAuthenticated) {
        me();
      }
    }

    render() {
        console.log(this.props);
      const { isAuthenticated, loading } = this.props;
      return isAuthenticated ? (
        <WrappedComponent {...this.props} />
      ) : loading ? (
        <Loader type="Puff" color="#FF0076" height="100" width="100" />
      ) : (
        <Redirect to="/login" />
      );
    }
  }

  const mapStateToProps = ({ auth: { isAuthenticated, loadingUser } }) => ({
    isAuthenticated,
    loading: loadingUser
  });

  const mapDispatchToProps = dispatch => ({
    me: () => dispatch(me())
  });

  return connect(
    mapStateToProps,
    mapDispatchToProps
  )(AuthenticatedComponent);
};
