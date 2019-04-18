package exception;

public class ApplicationException extends Exception {

    private String messageOfException;
    private ResponseCode code;

    public ApplicationException() {
    }

    public ApplicationException(String messageOfException, ResponseCode code) {
        this.messageOfException = messageOfException;
        this.code = code;
    }

    public ApplicationException(String messageOfException, Throwable cause, ResponseCode code) {
        super(cause);
        this.messageOfException = messageOfException;
        this.code = code;
    }

    public String getMessageOfException() {
        return messageOfException;
    }

    public ResponseCode getCode() {
        return code;
    }
}
