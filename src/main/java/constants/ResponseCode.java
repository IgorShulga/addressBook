package constants;

public enum ResponseCode {
    NOT_FOUND(404),
    OBJECT_EXIST(302),
    WRONG_DATA_TYPE(415),
    NOT_CONTENT(204),
    OBJECT_WAS_NOT_CHANGED(909);

    private int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
