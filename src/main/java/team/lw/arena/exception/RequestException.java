package team.lw.arena.exception;


public class RequestException extends SysException {
    public RequestException(){
    super("Arena:The request layer an exception occurs");
    }

    public RequestException(String msg){
        super(msg);
    }
}
