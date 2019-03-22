import {combineReducers} from 'redux';
import auth from "./auth";
import tracks from "./tracks";

export default combineReducers({
    auth,
    tracks
});