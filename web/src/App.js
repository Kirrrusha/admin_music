import React, {Component} from 'react';
import Header from "./components/Header";
import Playlists from "./components/Playlists";
import PlaylistContainer from './containers/PlaylistContainer';

class App extends Component {
    render() {
        return (
            <div className="content">
                <Header />
                <PlaylistContainer />
            </div>
        );
    }
}

export default App;
