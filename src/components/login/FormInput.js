import React from 'react';
import PropTypes from 'prop-types';

const FormInput = ({id, label, secured, value, onChange}) => {
  return (
    <div className="row">
      <input
        id={id}
        className="input"
        type={secured ? 'password' : 'text'}
        value={value}
        onChange={onChange}
        placeholder={label}
        required
      />
    </div>
  )
}

FormInput.propTypes = {
  label: PropTypes.string,
  secured: PropTypes.bool,
  value: PropTypes.string,
  onChange: PropTypes.func,
};

FormInput.defaultProps = {
  label: '',
  secured: false,
  value: '',
  onChange: () => {}
};

export default FormInput
