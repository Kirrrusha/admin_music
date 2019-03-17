import thunk from 'redux-thunk';
import reducer from '../modules/reducer';
import {compose, createStore, applyMiddleware} from 'redux';


const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const enhancer = composeEnhancers(
    applyMiddleware(thunk)
);

const store = createStore(
    reducer,
    enhancer);

export default store;