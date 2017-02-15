package team.lw.arena.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import team.lw.arena.entity.ReturnInfo;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.util.ResultCode;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ReturnInfo returnInfo = new ReturnInfo();

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ReturnInfo ServiceException(Exception ex) {
        returnInfo.setInfo(ResultCode.SEND_FAIL, ex.getMessage());
        return returnInfo;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnInfo superException(Exception ex){
        returnInfo.setInfo(ResultCode.SEND_FAIL,ex.getMessage());
        return returnInfo;
    }
}
