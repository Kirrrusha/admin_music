import {AUTH, FAIL, LOCAL_STORAGE_TOKEN_KEY, ME, START, SUCCESS} from "../../utils/const";

const initialState = {
    isAuthenticated: null,
    error: '',
    loading: false,
    token: localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY),
    user: null,
    loadingUser: true
};

export default (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case AUTH + START:
            return {
                ...state,
                isAuthenticated: null,
                error: '',
                loading: true
            };

        case AUTH + SUCCESS:
            localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, payload);
            return {
                ...state,
                error: '',
                loading: false,
                isAuthenticated: true,
                token: payload
            };

        case AUTH + FAIL:
            return {
                ...state,
                loading: false,
                isAuthenticated: false,
                error: action.errorMsg
            };

        case ME + START:
            return {
                ...state,
                loadingUser: true
            };
        case ME + SUCCESS:
            return {
                ...state,
                isAuthenticated: true,
                user: payload,
                loadingUser: false
            };
        case ME + FAIL:
            return {
                ...state,
                loadingUser: false,
                isAuthenticated: false
            };

        default:
            return state;
    }
}