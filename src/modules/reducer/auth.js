import {FAIL, GET_USER, START, SUCCESS} from "../../utils/const";

const initialState = {
    isAuthenticated: null,
    error: '',
    loading: false,
    user: null,
    loadingUser: true
};

export default (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case GET_USER + START:
            return {
                ...state,
                error: '',
                loading: true
            };

        case GET_USER + SUCCESS:
            return {
                ...state,
                error: '',
                loading: false,
                user: payload
            };

        case GET_USER + FAIL:
            return {
                ...state,
                loading: false,
                user: payload
            };

        default:
            return state;
    }
}