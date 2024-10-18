package org.iaulitin.mongodbpg.controller;

import lombok.extern.slf4j.Slf4j;
import org.iaulitin.mongodbpg.common.exceptions.ServiceException;
import org.iaulitin.mongodbpg.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@SuppressWarnings("PMD.UnusedPrivateMethod")
public abstract class BaseController {

    @ExceptionHandler(ServiceException.class)
    private ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        log.error("An exception happened while processing a request", ex);
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(
                        ErrorResponse.builder()
                                .message(ex.getErrorMessage())
                                .build()
                );
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?> handleUnexpectedException(RuntimeException ex) {
        log.error("An exception happened while processing a request", ex);
        // TODO improve
        return ResponseEntity.internalServerError().build();
    }

}
