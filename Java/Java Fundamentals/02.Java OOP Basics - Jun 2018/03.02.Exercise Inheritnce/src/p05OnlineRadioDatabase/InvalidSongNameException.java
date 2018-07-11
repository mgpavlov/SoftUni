package p05OnlineRadioDatabase4;

public class InvalidSongNameException extends InvalidSongException {

    public InvalidSongNameException() {
        this(Config.INVALID_SONG_NAME_EXCEPTION_MESSAGE);
    }

    public InvalidSongNameException(String message) {
        super(message);
    }
}
