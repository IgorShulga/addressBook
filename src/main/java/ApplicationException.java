public class ApplicationException extends Exception {

    private String messageOfException;
    private int code;

    public ApplicationException(int code, String message) {
        this.code = code;
        this.messageOfException = message;
    }

    public String getMessageOfException() {
        return messageOfException;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
                "messageOfException='" + messageOfException + '\'' +
                ", code=" + code +
                '}';
    }
}
