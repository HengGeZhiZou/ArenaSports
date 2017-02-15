package team.lw.arena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.lw.arena.entity.MobileUser;
import team.lw.arena.entity.ReturnInfo;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.PositionService;

import javax.annotation.Resource;

import java.util.List;

import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS;
import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS_MSG;

@Controller
@RequestMapping("/Arena/position/*")
public class PositionController {

    @Resource(name = "positionServiceImpl")
    private PositionService positionService;
    private ReturnInfo returnInfo=new ReturnInfo();

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updatePosition",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public ReturnInfo updatePosition(@RequestBody MobileUser mobileUser) throws ServiceException {
        try{
            positionService.updatePosition(mobileUser);
            returnInfo.setInfo(REQUEST_SUCCESS,REQUEST_SUCCESS_MSG);
            return returnInfo;
        }catch (Exception e){
            throw new ServiceException("发生错误，请检查输入id是否存在");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getAroundPeople",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public ReturnInfo getAroundPeople(@RequestBody MobileUser mobileUser) throws ServiceException {
        List<UserInfo> lists = positionService.getAroundPeople(mobileUser);
        returnInfo.setInfo(REQUEST_SUCCESS,REQUEST_SUCCESS_MSG, lists);
        return returnInfo;
    }

}
