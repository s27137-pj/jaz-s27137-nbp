package pl.javastart.jazs27137nbp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.javastart.jazs27137nbp.exception.MyBadRequestException;
import pl.javastart.jazs27137nbp.exception.MyGateWayTimeout;
import pl.javastart.jazs27137nbp.exception.MyInternalServerError;
import pl.javastart.jazs27137nbp.exception.MyNotFoundException;

@RestControllerAdvice
public class JazAdvice {

    @ExceptionHandler(MyBadRequestException.class)
    public ResponseEntity<String> badEx(MyBadRequestException ex){
        return ResponseEntity.status(400)
                .body("Bad Request Exception occured on request, ");
    }


    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<String> NotEx(MyNotFoundException ex){
        return ResponseEntity.status(404)
                .body("Not Found Exception occured on request");
    }

    @ExceptionHandler(MyInternalServerError.class)
    public ResponseEntity<String> MyInt(MyInternalServerError ex){
        return ResponseEntity.status(502)
                .body("My Internal Exception occured on request");
    }

    @ExceptionHandler(MyGateWayTimeout.class)
    public ResponseEntity<String> MyGate(MyGateWayTimeout ex){
        return ResponseEntity.status(504)
                .body("My GateWay Exception occured on request");
    }
}
