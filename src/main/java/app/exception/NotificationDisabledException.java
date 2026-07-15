package app.exception;

import org.springframework.http.HttpStatus;

public class NotificationDisabledException extends ApiException {

    public NotificationDisabledException(String message) {
        super(message);
    }

    public NotificationDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}

