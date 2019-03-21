import thunk from 'redux-thunk';
import reducer from '../modules/reducer';
import {apiMiddleware} from 'redux-api-middleware';
import {compose, createStore, applyMiddleware} from 'redux';
import apiHeadersInjector from "../middlewares/apiHeadersInjector";
import apiResponseHandler from "../middlewares/apiResponseHandler";


const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const enhancer = composeEnhancers(
    applyMiddleware(apiHeadersInjector, apiMiddleware, apiResponseHandler, thunk)
);

const store = createStore(
    reducer,
    enhancer);

export default store;