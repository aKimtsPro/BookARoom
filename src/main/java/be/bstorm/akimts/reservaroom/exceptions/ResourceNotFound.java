package be.bstorm.akimts.reservaroom.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFound extends ResponseStatusException {

    private final Class<?> resourceType;

    public ResourceNotFound(Class<?> resourceType) {
        super(HttpStatus.NOT_FOUND, resourceType.getSimpleName() + " could not be found");
        this.resourceType = resourceType;
    }

    public Class<?> getResourceType() {
        return resourceType;
    }

}
