package team.lw.arena.exception;

public class SysException extends Exception {

    //������Ϣ
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }


    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    //���漸��ȫ�ǲ�ͬ�Ĺ��췽��
    public SysException() {
        super();
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
        errorMsg=message;
    }

    public SysException(String message) {
        super(message);
        errorMsg=message;
    }

    public SysException(Throwable cause) {
        super(cause);
    }

}
