package protocol;

/**
 * 响应状态
 *
 * @author huangding
 * @date 2020/5/15 22:27
 */
public enum Status {
    /**
     * 成功
     */
    SUCCESS(200, "SUCCESS"),
    NOT_FOUND(404, "not found"),
    ERROR(500, "服务器异常");;

    Status(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    private final int status;
    private final String msg;
}
