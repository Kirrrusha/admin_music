import Playlists from '../components/Playlists';
import {connect} from 'react-redux';
import {getTracks} from '../modules/actions';

const mapStateToProps = (state) => {
    console.log('container', state);
    return ({
        tracks: state.tracks
    })
};

const mapDispatchToProps = dispatch => {
    return ({
        getTracks: (login, password) => dispatch(getTracks()),
    })
};

export default connect(mapStateToProps, mapDispatchToProps)(Playlists);