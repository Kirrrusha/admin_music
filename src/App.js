import React, {Component} from 'react';
import Header from "./components/Header";
import Playlists from "./components/Playlists";

class App extends Component {
    render() {
        return (
            <div className="content">
                <Header />
                <Playlists />
            </div>
        );
    }
}

export default App;
