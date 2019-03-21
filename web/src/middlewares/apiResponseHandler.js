const getResponse = payload => {
    return payload && payload.response;
};

const getData = response => {
    return (response && response.token) || {};
};

const getMessage = response => {
    return (response && response.message) ||
        (response && response.error && response.error.message) ||
        'Network error';
};

export default store => next => action => {
    console.log(action);
    const response = getResponse(action.payload);
    if (action.error) {
        action.errorMsg = getMessage(response);
    } else {
        action.payload = (action.payload && action.payload.token) || getData(response);
    }
    next(action);
}