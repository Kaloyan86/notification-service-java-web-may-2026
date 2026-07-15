package app.exception;

import org.springframework.http.HttpStatus;

public class MissingApiKeyException extends ApiException {

    public MissingApiKeyException(String message) {
        super(message);
    }

    public MissingApiKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}

