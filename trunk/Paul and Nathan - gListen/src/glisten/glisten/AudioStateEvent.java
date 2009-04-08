/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package glisten;

import java.util.EventObject;

/**
 *
 * @author paul
 */



public class AudioStateEvent extends EventObject {
    private AudioState state;

    public enum AudioState {
        INVALID_URL, UNSUPPORTED_FORMAT, OPENING, READY, OPEN_FAILED, PLAYING, STOPPING, STOPPED, CLOSING, CLOSED, SEEKING,
    }
    public AudioStateEvent( Object source, AudioState state ) {
        super( source );
        this.state = state;
    }
    public AudioState getAudioState() {
        return state;
    }

}
