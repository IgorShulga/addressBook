package exception;

import constants.ResponseCode;

public class ApplicationException extends Exception {

    private String messageOfException;
    private ResponseCode code;

    public ApplicationException() {
    }

    public ApplicationException(ResponseCode code, String messageOfException) {
        this.messageOfException = messageOfException;
        this.code = code;
        System.out.println(messageOfException);
    }

    public ApplicationException(ResponseCode code) {
        this.code = code;
    }

    public String getMessageOfException() {
        return messageOfException;
    }

    public ResponseCode getCode() {
        return code;
    }
}
