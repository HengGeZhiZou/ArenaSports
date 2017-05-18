package team.lw.arena.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.lw.arena.entity.Record;
import team.lw.arena.entity.ReturnInfo;
import team.lw.arena.entity.SixPeopleRoom;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.GameService;
import team.lw.arena.service.RecordService;


import javax.annotation.Resource;

import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS;
import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS_MSG;

@Controller
@Api(value = "/Arena/game/*", description = "比赛房间进行操作")
@RequestMapping(value = "/Arena/game/*")
public class GameController {
    @Resource(name = "gameServiceImpl")
    private GameService gameService;

    @Resource(name = "recordServiceImpl")
    private RecordService recordService;

    private ReturnInfo returnInfo = new ReturnInfo();

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "createHouse/{uid}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnInfo createHouse(@PathVariable String uid) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, gameService.CreateHouse(uid));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("你已经创建房间，请先退出房间");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "inviteOthers/{ownerID}/{inviteeID}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnInfo inviteOthers(@PathVariable String ownerID,@PathVariable String inviteeID) throws ServiceException {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, gameService.inviteOthers(ownerID, inviteeID));
            return returnInfo;
    }

//    @SuppressWarnings("unchecked")
//    @RequestMapping(value = "startGame/{ownerID}",method = RequestMethod.GET,produces = "application/json")
//    @ResponseBody
//    public ReturnInfo startGame(@PathVariable String ownerID)throws ServiceException{
//            gameService.startGame(ownerID);
//            returnInfo.setInfo(REQUEST_SUCCESS,REQUEST_SUCCESS_MSG);
//            return returnInfo;
//    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "startGame/{ownerID}/{type}/{district}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnInfo endGame(@PathVariable String ownerID,@PathVariable String type,@PathVariable String district) throws ServiceException {
        try {
            gameService.start(ownerID,type,district);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, "比赛结束");
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("结束比赛失败");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "findAllRecord/{uid}/{currPage}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnInfo findAllRecord(@PathVariable String uid, @PathVariable int currPage) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, recordService.findByPage(currPage, uid));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("查询战绩失败");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "findCurrRecord/{currPage}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo findCurrRecord(@PathVariable int currPage) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS,REQUEST_SUCCESS_MSG,recordService.currRecord(currPage));
            return returnInfo;
        } catch (Exception e){
            throw new ServiceException("查找热门比赛失败");
        }
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "findRecord/{rid}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo findRecord(@PathVariable String rid) throws ServiceException{
        returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,recordService.findByRecordID(rid));
    return returnInfo;
    }
}
