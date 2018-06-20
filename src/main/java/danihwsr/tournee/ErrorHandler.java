package danihwsr.tournee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    private class ErrorResponse {
        public int code;
        public String error;
        public String message;

        public ErrorResponse(Exception e){
            if (e instanceof UserAlreadyExistsException) {
                this.code = 500;
                this.message = e.getMessage();
                this.error = "username already exists";
            } else if (e instanceof UserNotFoundException) {
                this.code = 500;
                this.message = e.getMessage();
                this.error = "user not found";
            } else if (e instanceof MailAlreadyExistsException) {
                this.code = 500;
                this.message = e.getMessage();
                this.error = "mail address already exists";
            }
        }

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> error(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e), HttpStatus.BAD_REQUEST);
    }

}
