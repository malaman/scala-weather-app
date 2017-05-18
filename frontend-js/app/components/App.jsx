import React from 'react';
import Rx from 'rxjs/Rx';

import Select from './Select/Select';

require('./App.css');

const host = 'http://localhost:9000';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSelectionChange = this.handleSelectionChange.bind(this);
    this.getWeatherData = this.getWeatherData.bind(this);
    this.state = {
      inputValue: '',
      weatherData: [],
      selectOptions: [],
      isLoading: false
    };
    this.inputSubject = new Rx.Subject();
    this.inputSubject.debounceTime(500)
      .subscribe(this.getWeatherData);
  }

  getWeatherData(city) {
    this.setState({ isLoading: true });
    fetch(`${host}/weather?city=${city}`)
      .then(resp => resp.json())
      .then(result => {
        const selectOptions = result[0].map(item => {
          return {
            value: item.name,
            label: `${item.name} ${item.weather[0].main} ${item.main.temp.toFixed(1)} °C`
          }
        });
        this.setState({weatherData: result, selectOptions, isLoading: false});
    });
  }

  handleInputChange(value) {
    this.setState({inputValue: value});
    this.inputSubject.next(value);
  }
  handleSelectionChange(obj) {
    this.setState({inputValue: obj ? obj.value : ''});
  }

  render() {
    return (
      <div className='app'>
        <div>Please search for city to get the weather: </div>
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
