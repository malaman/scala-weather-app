import React from 'react';
import ReactSelect from 'react-select';

require('./react-select.css');

export default class Select extends React.Component {
  render() {
    return (
      <div>
        <ReactSelect
          name="form-field-name"
          value={this.props.value}
          options={this.props.options}
          onChange={this.props.onChange}
          onInputChange={this.props.onInputChange}
          isLoading={this.props.isLoading}
        />
      </div>
    );
  }
}
