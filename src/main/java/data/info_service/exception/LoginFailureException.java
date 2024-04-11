package data.info_service.exception;

public class LoginFailureException extends RuntimeException{
    public LoginFailureException() {
        super("Login failure");
    }

    public LoginFailureException(String message) {
        super(message);
    }

    public LoginFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailureException(Throwable cause) {
        super(cause);
    }
}
