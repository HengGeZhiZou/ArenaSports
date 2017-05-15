package team.lw.arena.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "/Arena/position/*", description = "对用户位置进行操作")
@RequestMapping("/Arena/position/*")
public class PositionController {

    @Resource(name = "positionServiceImpl")
    private PositionService positionService;
    private ReturnInfo returnInfo=new ReturnInfo();

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updatePosition",method = RequestMethod.POST,produces = "application/json")
    @ApiOperation(
            value = "更新用户信息",
            notes = "传入用户id，经纬度更新用户信息",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "请求成功"),
            @ApiResponse(code = 101, message = "请求失败,id不存在")})
    @ResponseBody ReturnInfo updatePosition(@RequestBody MobileUser mobileUser) throws ServiceException {
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
    @ApiOperation(
            value = "获取附近的用户，传入页码",
            notes = "传入用户id以及页码，获取附近的用户",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "请求成功，获取到用户列表"),
            @ApiResponse(code = 101, message = "请求失败,id不存在")})
    @ResponseBody ReturnInfo getAroundPeople(@RequestBody MobileUser mobileUser) throws ServiceException {
        List<UserInfo> lists = positionService.getAroundPeople(mobileUser);
        returnInfo.setInfo(REQUEST_SUCCESS,REQUEST_SUCCESS_MSG, lists);
        return returnInfo;
    }

}
