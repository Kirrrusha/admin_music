import {RSAA} from "redux-api-middleware";

export default store => next => action => {
    const callApi = action[RSAA];
    if (callApi) {
        callApi.headers = state => {
            console.log('state', state);
            const token = state.auth.token;
            console.log('callApi', {
                ...callApi.headers,
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': token ? `Bearer ${token}` : undefined
            })
            return {
                ...callApi.headers,
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': token ? `Bearer ${token}` : undefined
            }
        };
    }

    return next(action);
}