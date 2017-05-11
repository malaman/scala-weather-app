import React from 'react';

require('./App.css');

const host = 'http://localhost:9000';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { some: 1 };
  }

  componentDidMount() {
      const city = 'Berlin';
      fetch(`${host}/weather?city=${city}`)
        .then(resp => resp.json())
        .then(result => console.log(result));
  }

  render() {
    return (
      <h1>!</h1>
    );
  }
}
