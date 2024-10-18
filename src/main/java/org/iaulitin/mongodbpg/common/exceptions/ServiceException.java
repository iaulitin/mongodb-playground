package org.iaulitin.mongodbpg.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ServiceException extends Exception {
    private static final long serialVersionUID = 1;

    private HttpStatus httpStatus;
    private String errorMessage;

    protected ServiceException(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
