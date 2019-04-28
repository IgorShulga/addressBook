package exception;

public enum ResponseCode {
    NOT_FOUND(404, "This storage is empty"),
    OBJECT_EXIST(302, "This contact already exists"),
    WRONG_DATA_TYPE(415, "Your entered wrong type data. "),
    NOT_CONTENT(204, "This data don't find in address book. "),
    OBJECT_WAS_NOT_CHANGED(909, "This object wasn't changed. ");

    private int code;
    private String str;

    ResponseCode(int code, String str) {
        this.code = code;
        this.str = str;
    }

    ResponseCode(int code) {
        this.code = code;
    }

    public String getStr() {
        return str;
    }

    public int getCode() {
        return code;
    }
}
