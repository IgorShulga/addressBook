package constants;

public enum ResponseCode {
    NOT_FOUND(404),
    OBJECT_EXIST(302),
    WRONG_DATA_TYPE(444),
    NOT_CONTENT(204),
    STORAGE_IS_EMPTY(222),
    OBJECT_WAS_NOT_CHANGED(999);

    private int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
