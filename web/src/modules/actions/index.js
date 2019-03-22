import {RSAA} from "redux-api-middleware";
import {FAIL, AUTH, ME, START, SUCCESS, API, GET_TRACKS} from "../../utils/const";

export const signIn = (login, password) => ({
    [RSAA]: {
        endpoint: `${API}/api/auth/sign-in`,
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({login, password}),
        types: [
            AUTH + START,
            AUTH + SUCCESS,
            AUTH + FAIL
        ]
    }
});

export const me = () => ({
    [RSAA]: {
        endpoint: `${API}/api/auth/me`,
        method: 'GET',
        types: [
            ME + START,
            ME + SUCCESS,
            ME + FAIL
        ]
    }
});

export const getTracks = () => ({
    [RSAA]: {
        endpoint: `${API}/api/files`,
        method: 'GET',
        types: [
            GET_TRACKS + START,
            GET_TRACKS + SUCCESS,
            GET_TRACKS + FAIL
        ]
    }
})