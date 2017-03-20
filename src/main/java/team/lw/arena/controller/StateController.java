package team.lw.arena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.lw.arena.entity.Comment;
import team.lw.arena.entity.ReturnInfo;
import team.lw.arena.entity.State;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.StateService;

import javax.annotation.Resource;

import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS;
import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS_MSG;

@Controller
@RequestMapping("/Arena/state/*")
public class StateController {

    @Resource(name = "stateServiceImpl")
    private StateService stateService;

    private ReturnInfo returnInfo = new ReturnInfo();


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addState", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnInfo addState(@RequestBody State state) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, stateService.addState(state));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("添加动态失败，请重试");
        }
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addLike/{stateID}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnInfo addLike(@PathVariable String stateID) throws ServiceException {
        try {
            stateService.addLike(stateID);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, "点赞成功");
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("点赞失败，请重试");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addComment", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnInfo addComment(@RequestBody Comment comment) throws ServiceException {
        try {
            stateService.addComment(comment);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, "评论成功");
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("评论失败，请重试");
        }
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "deleteState/{stateID}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ReturnInfo deleteState(@PathVariable String stateID) throws ServiceException {
        try {
            stateService.deleteState(stateID);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, "删除成功");
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("删除失败，请重试");
        }
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getAllStates/{uid}/{currPage}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnInfo getAllStates(@PathVariable String uid, @PathVariable Integer currPage) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, stateService.findAllState(currPage, uid));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("获取失败，请重试");
        }

    }

}
