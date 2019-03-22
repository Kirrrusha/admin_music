import {FAIL, GET_TRACKS, START, SUCCESS} from "../../utils/const";

const initialState = {
    data: null,
    loading: false,
    error: ''
};

export default (state = initialState, action) => {
    const {type, payload} = action;
    console.log('action tracks', action);

    switch (type) {
        case GET_TRACKS + START:
            return {
                ...state,
                error: '',
                loading: true
            };

        case GET_TRACKS + SUCCESS:
            return {
                ...state,
                error: '',
                loading: false,
                data: payload
            };

        case GET_TRACKS + FAIL:
            return {
                ...state,
                loading: false,
                data: payload.errorMsg
            };

        default:
            return initialState;

    }

}