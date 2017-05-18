package team.lw.arena.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import team.lw.arena.entity.Comment;
import team.lw.arena.entity.ReturnInfo;
import team.lw.arena.entity.State;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.StateService;
import team.lw.arena.util.CheckExcelFileTypeUtil;
import team.lw.arena.util.PropertiesUtil;

import javax.annotation.Resource;


import java.io.File;
import java.util.UUID;

import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS;
import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS_MSG;

@Controller
@Api(value = "/Arena/state/*", description = "发表动态进行操作")
@RequestMapping("/Arena/state/*")
public class StateController {

    @Resource(name = "stateServiceImpl")
    private StateService stateService;

    private ReturnInfo returnInfo = new ReturnInfo();


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addState", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "添加动态",
            notes = "发表个人动态",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "请求成功，返回该条动态id"),
            @ApiResponse(code = 101, message = "请求失败")})
    @ResponseBody ReturnInfo addState(@RequestBody State state) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, stateService.addState(state));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("添加动态失败，请重试");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "picState/{sid}",method = RequestMethod.POST)
    public
    @ResponseBody
    ReturnInfo picState(@PathVariable String sid,MultipartFile[] file) throws ServiceException {
   try {
       String paths="";
       String front= PropertiesUtil.getRequestPropertiesAddress();
       String path;
       for (MultipartFile mf : file) {
           if(!mf.isEmpty()){
               //生成uuid作为文件名称
               String uuid = UUID.randomUUID().toString().replaceAll("-","");
               //将MultipartFile转化为file
               CommonsMultipartFile cf= (CommonsMultipartFile)mf;
               DiskFileItem fi = (DiskFileItem)cf.getFileItem();
               String picType= CheckExcelFileTypeUtil.getFileType(fi.getStoreLocation());
               path=uuid+"."+picType;

               mf.transferTo(new File(PropertiesUtil.getRequestPropertiesSaveAddress()+path));
               paths+=front+path+",";
               stateService.addPics(sid,paths);
           }
       }
       returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,"发表成功");
       return returnInfo;
   }catch (Exception e){
       throw new ServiceException("发表的动态失败");
   }
    }



    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addLike/{stateID}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(
            value = "点赞",
            notes = "给动态点赞，需要用户该条动态id",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "点赞成功"),
            @ApiResponse(code = 101, message = "请求失败")})
    @ResponseBody ReturnInfo addLike(@PathVariable String stateID) throws ServiceException {
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
    @ApiOperation(
            value = "评论",
            notes = "给动态评论，需要传入动态对象",
            response = Comment.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "评论成功"),
            @ApiResponse(code = 101, message = "请求失败")})
    @ResponseBody
    ReturnInfo addComment(@RequestBody Comment comment) throws ServiceException {
        try {
            stateService.addComment(comment);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, "评论成功");
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("评论失败，请重试");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getAllComments/{stateID}/{currPage}",method = RequestMethod.GET)
    @ApiOperation(
            value = "获取用户评论",
            notes = "根据动态ID获取评论，同时需要传入页码",
            response = Comment.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "获取成功"),
            @ApiResponse(code = 101, message = "请求失败")})
    @ResponseBody
    ReturnInfo getAllComments(@PathVariable String stateID,@PathVariable int currPage) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,stateService.getAllComment(currPage,stateID));
            return returnInfo;
        }
        catch (Exception e){
            throw new ServiceException("获取失败");
        }
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "deleteState/{stateID}", method = RequestMethod.DELETE)
    @ApiOperation(
            value = "删除动态",
            notes = "传入动态id",
            response = ReturnInfo.class
    )
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
    @RequestMapping(value = "getHotStates/{currPage}",method = RequestMethod.GET)
    @ApiOperation(
            value = "获取热门动态",
            notes = "获取热门动态，传入页码",
            response = State.class
    )
    public
    @ResponseBody
    ReturnInfo getHotStates(@PathVariable int currPage) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,stateService.getHotStates(currPage));
            return returnInfo;
        }catch (Exception e){
            throw new ServiceException("获取热门动态失败");
        }
    }



    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getAllStates/{uid}/{currPage}", method = RequestMethod.GET)
    @ApiOperation(
            value = "获取动态",
            notes = "获取动态，传入页码",
            response = ReturnInfo.class
    )
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
