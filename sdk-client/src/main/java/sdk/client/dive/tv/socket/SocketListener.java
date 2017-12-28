package sdk.client.dive.tv.socket;

import com.touchvie.sdk.model.Card;

import java.util.List;

import sdk.client.dive.tv.socket.model.StreamError;

public interface SocketListener {

    /**
     * On movie-start event received.
     *
     * @param movieId the card id of movie started
     */
    void onMovieStartEventReceived(String movieId);

    /**
     * On movie-end event received.
     *
     */
    void onMovieEndEventReceived();

    /**
     * On scene-start event received.
     *
     * @param cards the list of cards received on scene-start event
     */
    void onSceneStartEventReceived(List<Card> cards);

    /**
     * On scene-update event received.
     *
     * @param cards the list of cards received on scene-update event
     */
    void onSceneUpdateEventReceived(List<Card> cards);

    /**
     * On scene-end event received.
     *
     */
    void onSceneEndEventReceived();

    /**
     * On paused-start event received.
     *
     */
    void onPausedStartEventReceived();

    /**
     * On paused-start event received.
     *
     */
    void onPausedEndEventReceived();

    /**
     * On error received.
     */
    void onErrorReceived(StreamError error);

}
