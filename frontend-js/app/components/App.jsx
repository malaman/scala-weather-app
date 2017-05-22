import React from 'react';
import throttle from 'lodash.throttle';

import Select from './Select/Select';

require('./App.css');

const host = 'http://localhost:9000';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSelectionChange = this.handleSelectionChange.bind(this);
    this.loadWeatherInfo = this.loadWeatherInfo.bind(this);
    this.state = {
      inputValue: '',
      weatherData: [],
      selectOptions: [],
      isLoading: false
    };
    this.throttleInput = throttle(this.throttleInputValueChange, 400);
  }

  loadWeatherInfo(city) {
    this.setState({ isLoading: true });
    fetch(`${host}/weather?city=${city}`)
      .then(resp => resp.json())
      .then(result => {
        const selectOptions = result.map((item, index) => {
          return {
            value: `${item.name}:${index}`,
            label: `${item.name} ${item.weather[0].main} ${item.main.temp.toFixed(1)} °C`
          }
        });
        this.setState({weatherData: result, selectOptions, isLoading: false});
    });
  }

  throttleInputValueChange() {
    const { inputValue: city } = this.state;
    this.loadWeatherInfo(city);
  }


  handleInputChange(value) {
    this.setState({inputValue: value});
    this.throttleInput();
  }

  handleSelectionChange(obj) {
    this.setState({inputValue: obj ? obj.value : ''});
  }

  render() {
    return (
      <div className='app'>
        <div>Search for city to get the weather: </div>
        <br />
        <Select
          value={this.state.inputValue}
          options={this.state.selectOptions}
          onChange={this.handleSelectionChange}
          onInputChange={this.handleInputChange}
          isLoading={this.state.isLoading}
        />
      </div>
    );
  }
}
