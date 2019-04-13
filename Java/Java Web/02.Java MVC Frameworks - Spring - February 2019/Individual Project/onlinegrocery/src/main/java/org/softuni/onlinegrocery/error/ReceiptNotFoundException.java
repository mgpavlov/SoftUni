package org.softuni.onlinegrocery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Receipt not found!")
public class ReceiptNotFoundException extends RuntimeException {

    private int statusCode;

    public ReceiptNotFoundException() {
        this.statusCode = 404;
    }

    public ReceiptNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
