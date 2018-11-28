package net.mcep.ted.common.constant;

/**
 * 系统接口结果常量枚举类
 */
public enum TedResultConstant {

    /**
     * 失败
     */
    FAILED(0, "failed"),

    /**
     * 成功
     */
    SUCCESS(1, "success"),

    /**
     * 文件类型不支持
     */
    FILE_TYPE_ERROR(20001, "File type not supported!"),

    /**
     * 无效长度
     */
    INVALID_LENGTH(20002, "Invalid length"),

    /**
     * 无效参数
     */
    INVALID_PARAMETER(20003, "Invalid parameter"),

    /**
     * 无效参数
     */
    DOUBT(10001, "doubt");

    public int code;

    public String message;

    TedResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
