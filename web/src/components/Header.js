import React from 'react';

const Header = () => {
    return (
        <div className="header">
            <div className="header-logo">
                MUSIC
            </div>

            <div className="header-user">
                <div className="header-userName">Name</div>
                <div className="header-out">
                    <svg width="30" height="30" viewBox="0 0 30 30" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fillRule="evenodd" clipRule="evenodd"
                              d="M13.143 18.4903L14.5139 19.8611L19.375 15L14.5139 10.1389L13.143 11.5097L15.6514 14.0278H6.24998V15.9722H15.6514L13.143 18.4903ZM21.8055 6.25H8.19441C7.11525 6.25 6.24997 7.125 6.24997 8.19444V12.0833H8.19441V8.19444H21.8055V21.8056H8.19441V17.9167H6.24997V21.8056C6.24997 22.875 7.11525 23.75 8.19441 23.75H21.8055C22.875 23.75 23.75 22.875 23.75 21.8056V8.19444C23.75 7.125 22.875 6.25 21.8055 6.25Z"
                              fill="white"/>
                    </svg>

                </div>
            </div>
        </div>
    );
};

Header.propTypes = {};
Header.defaultProps = {};

export default Header;
