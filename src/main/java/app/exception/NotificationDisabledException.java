package app.exception;

import org.springframework.http.HttpStatus;

public class NotificationDisabledException extends ApiException {

    public NotificationDisabledException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
