import React, {Component} from 'react';
import PropTypes from 'prop-types';
import Textarea from 'react-textarea-autosize';

class Playlists extends Component {
    static defaultProps = {};

    static propTypes = {
        getTracks: PropTypes.func
    };

    state = {
        playlist: [
            {
                id: 0,
                name: 'main playlist',
                list: ['Песня 1', 'Песня 2', 'Песня 3', 'Песня 4']
            }
        ],
        selectedId: 0
    };

    componentWillMount() {
        const {getTracks} = this.props;
        getTracks();
    }

    handleSelect = (id) => {
        this.setState({selectedId: id});
    }

    addPlaylist = () => {
        const {playlist} = this.state;
        playlist.push({
            id: playlist.length,
            name: 'new',
            list: []
        });
        this.setState({playlist});
    }

    handleRemove = (id) => {
        const {playlist} = this.state;
        const newPlaylist = playlist.filter(item => item.id !== id);
        this.setState({playlist: newPlaylist});
    }

    handleInput = (id, e) => {
        const {playlist} = this.state;
        playlist.find(item => item.id === id).name = e.currentTarget.value;
        this.setState({playlist});
    }

    addTrack = (id) => {
        const {playlist} = this.state;
        playlist[id].list.push(`Track-${playlist[id].list.length}`);
        this.setState({playlist});
    }

    render() {
        const {playlist, selectedId} = this.state;
        const {tracks: {data}} = this.props
        console.log('tracks', data);
        if (!data) return null;
        return (
            <section className="playlist">
                <div className="playlist-list">
                    {playlist.map(item => {
                        item.name = item.name[0].toUpperCase() + item.name.substring(1);
                        return (
                            <div
                                key={item.id}
                                className={`playlist-item ${item.id === selectedId ? 'selected' : ''}`}
                                onClick={() => this.handleSelect(item.id)}
                            >
                                <Textarea
                                    value={item.name}
                                    onChange={(event) => this.handleInput(item.id, event)}
                                />
                                {item.id ? (
                                    <div
                                        className="playlist-itemRemove"
                                        onClick={() => this.handleRemove(item.id)}
                                    >+
                                    </div>
                                ) : null}

                            </div>
                        )
                    }, this)
                    }
                    <div className="playlist-add" onClick={this.addPlaylist}>+</div>
                </div>
                <div className="playlist-editor">
                    {playlist[selectedId].list.map(item => <div key={item} className="playlist-song">{item}</div>)}
                    <div className="playlist-add" onClick={() => this.addTrack(selectedId)}>Add Track</div>
                </div>
            </section>
        );
    }
}

export default Playlists;
