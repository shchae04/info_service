package data.info_service.exception;

public class MemberEmailAlreadyExistsException extends RuntimeException {

    public MemberEmailAlreadyExistsException() {
        super("This email already exists");
    }

    public MemberEmailAlreadyExistsException(String message) {
        super(message);
    }

    public MemberEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberEmailAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
