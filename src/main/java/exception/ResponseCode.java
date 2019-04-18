package exception;

public enum ResponseCode {
    NOT_FOUND(404, "This object not found"),
    OBJECT_EXIST(302, "This object exist"),
    VALIDATION_ERROR(301, "9999999"),
    WRONG_DATA_TYPE(415, "This type data is wrong"),
    NOT_CONTENT(204, "This object don't have in address book. Repeat please.");

    private int code;
    private String str;

    ResponseCode(int code, String s) {
        this.code = code;
        this.str = s;
    }

    public String getStr() {
        return str;
    }

    public int getCode() {
        return code;
    }
}
