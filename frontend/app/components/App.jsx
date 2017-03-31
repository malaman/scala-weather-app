import React from 'react';

require('./App.css');

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { some: 1 };
  }

  render() {
    return (
      <h1>!</h1>
    );
  }
}
