package be.bstorm.akimts.reservaroom.controller;

import be.bstorm.akimts.reservaroom.exceptions.BookingConflictException;
import be.bstorm.akimts.reservaroom.models.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BookingConflictException.class)
    public ErrorDTO handle(BookingConflictException ex, HttpServletRequest request){
        return ErrorDTO.builder(request.getRequestURI(), HttpMethod.valueOf(request.getMethod()) )
                .add( ErrorDTO.Error.from( ex.getMessage(), "booking_conflict" ) )
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalHandle(Exception ex, HttpServletRequest request){

        if( ex instanceof BookingConflictException ){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(
                            ErrorDTO.builder(request.getRequestURI(), HttpMethod.valueOf(request.getMethod()) )
                                    .add( ErrorDTO.Error.from( ex.getMessage(), "booking_conflict" ) )
                                    .build()
                    );
        }
        else if (  ex instanceof IllegalAccessException ){
            // ...
        }
        else {
            // 500
        }

        return null;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body( ex.getAllErrors() );
    }
}
