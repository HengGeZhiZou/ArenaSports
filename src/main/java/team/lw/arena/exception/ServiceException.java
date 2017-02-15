package team.lw.arena.exception;

public class ServiceException extends SysException {

    public ServiceException(){
        super("Arena:The service layer an exception occurs");
    }

    public ServiceException(String msg){
        super(msg);
    }
}
