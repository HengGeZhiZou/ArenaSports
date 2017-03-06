package team.lw.arena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.lw.arena.entity.ReturnInfo;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.GameService;


import javax.annotation.Resource;

import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS;
import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS_MSG;

@Controller
@RequestMapping(value = "/Arena/game/*")
public class GameController {
    @Resource(name = "gameServiceImpl")
    private GameService gameService;

    private ReturnInfo returnInfo = new ReturnInfo();

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "createHouse/{uid}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnInfo createHouse(@PathVariable String uid) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, gameService.CreateHouse(uid));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("创建房间失败");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "inviteOthers",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public ReturnInfo inviteOthers(@RequestBody SixPeopleRoom sixPeopleRoom) throws ServiceException {
        try{
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,gameService.inviteOthers(sixPeopleRoom));
            return returnInfo;
        }catch (Exception e){
            throw  new ServiceException("邀请玩家失败");
        }

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "startGame",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public ReturnInfo startGame(@RequestBody SixPeopleRoom sixPeopleRoom) throws ServiceException {
        try{

        }catch (Exception e){
            throw  new ServiceException("开始比赛失败");
        }
    return  returnInfo;
    }
}
