import React from "react";
import PropTypes from "prop-types";

const SubmitButton = ({ onSubmit, children, loading }) => (
  <div className="row">
    <button
      onClick={(e) => {
        e.preventDefault();
        onSubmit();
      }}
      disabled={loading}
      type="submit"
      className="btn"
    >
      {loading ? "..." : children}
    </button>
  </div>
);

SubmitButton.propTypes = {
  onSubmit: PropTypes.func,
  children: PropTypes.string,
  loading: PropTypes.bool
};

SubmitButton.defaultProps = {
  onSubmit: () => {},
  children: "",
  loading: false
};

export default SubmitButton;
