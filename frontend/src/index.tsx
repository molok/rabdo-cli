import * as React from 'react';
import * as ReactDOM from 'react-dom';
import './index.css';
import {createStore, applyMiddleware } from 'redux';
import {Provider} from 'react-redux';
import Rabdo from "./containers/Rabdo";
import {defaultState, State} from "./model";
import {reducers} from "./reducers/index";
import {apiMiddleware, filterMiddleware} from "./middlewares";
import {TranslateConf} from "./components/Translate";
import {Actions} from "./actions";

const loadState = ():State => {
    try {
        const serializedState = localStorage.getItem('state');
        if (serializedState === null) {
            return defaultState();
        } else {
            return JSON.parse(serializedState);
        }
    } finally { }
};

let store = createStore(
    reducers,
    loadState(),
    applyMiddleware(filterMiddleware, apiMiddleware)
);

const saveState = (state: State) => {
    try {
        const serializedState = JSON.stringify(state);
        localStorage.setItem('state', serializedState);
    } finally {}
};

store.subscribe(() => {
    saveState(store.getState())
});

store.subscribe(() => {
    TranslateConf.usrLang = store.getState().lang;
});

store.dispatch(Actions.loadedFromStorage());

function render() {
    ReactDOM.render(
        <Provider store={store}>
            <Rabdo />
        </Provider>,
        document.getElementById('root') as HTMLElement );
}
render();

