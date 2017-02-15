package team.lw.arena.entity;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class ReturnInfo<T> {

    private int code;
    private String msg;
    private T obj;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getObj() {
        return obj;
    }

    public ReturnInfo() {
    }

    public ReturnInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnInfo(int code, String msg, T obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public void setInfo(int code,String msg){
        this.code=code;
        this.msg=msg;
        this.obj= (T) "none";
    }

    public void setInfo(int code,String msg, T obj){
        this.code=code;
        this.msg=msg;
        this.obj=obj;
    }
}
