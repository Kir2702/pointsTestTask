package assignment.cosy.demoAPI.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}