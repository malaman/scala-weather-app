import 'isomorphic-fetch';
import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';

require('es6-promise').polyfill();

ReactDOM.render(
  <App />,
  document.body.appendChild(document.createElement('div'))
);
