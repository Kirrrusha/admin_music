import thunk from 'redux-thunk';
import reducer from '../modules/reducer';
import {apiMiddleware} from 'redux-api-middleware';
import {compose, createStore, applyMiddleware} from 'redux';


const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const enhancer = composeEnhancers(
    applyMiddleware(apiMiddleware, thunk)
);

const store = createStore(
    reducer,
    enhancer);

export default store;