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
@Api(value = "/Arena/position/*", description = "���û�λ�ý��в���")
@RequestMapping("/Arena/position/*")
public class PositionController {

    @Resource(name = "positionServiceImpl")
    private PositionService positionService;
    private ReturnInfo returnInfo=new ReturnInfo();

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updatePosition",method = RequestMethod.POST,produces = "application/json")
    @ApiOperation(
            value = "�����û���Ϣ",
            notes = "�����û�id����γ�ȸ����û���Ϣ",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "����ɹ�"),
            @ApiResponse(code = 101, message = "����ʧ��,id������")})
    @ResponseBody ReturnInfo updatePosition(@RequestBody MobileUser mobileUser) throws ServiceException {
        try{
            positionService.updatePosition(mobileUser);
            returnInfo.setInfo(REQUEST_SUCCESS,REQUEST_SUCCESS_MSG);
            return returnInfo;
        }catch (Exception e){
            throw new ServiceException("����������������id�Ƿ����");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getAroundPeople",method = RequestMethod.POST,produces = "application/json")
    @ApiOperation(
            value = "��ȡ�������û�������ҳ��",
            notes = "�����û�id�Լ�ҳ�룬��ȡ�������û�",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "����ɹ�����ȡ���û��б�"),
            @ApiResponse(code = 101, message = "����ʧ��,id������")})
    @ResponseBody ReturnInfo getAroundPeople(@RequestBody MobileUser mobileUser) throws ServiceException {
        List<UserInfo> lists = positionService.getAroundPeople(mobileUser);
        returnInfo.setInfo(REQUEST_SUCCESS,REQUEST_SUCCESS_MSG, lists);
        return returnInfo;
    }

}
