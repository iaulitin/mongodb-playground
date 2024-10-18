package org.iaulitin.mongodbpg.common.exceptions;

import org.iaulitin.mongodbpg.common.ResourceType;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ServiceException {
    private static final long serialVersionUID = 1;

    public ResourceNotFoundException(ResourceType resourceType,
                                     String resourceId) {
        super(
                HttpStatus.NOT_FOUND,
                String.format("Unable to find resource of type=%s with id=%s", resourceType.name(), resourceId)
        );
    }
}
