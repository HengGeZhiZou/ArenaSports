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
@Api(value = "/Arena/state/*", description = "����̬���в���")
@RequestMapping("/Arena/state/*")
public class StateController {

    @Resource(name = "stateServiceImpl")
    private StateService stateService;

    private ReturnInfo returnInfo = new ReturnInfo();


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addState", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "��Ӷ�̬",
            notes = "������˶�̬",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "����ɹ������ظ�����̬id"),
            @ApiResponse(code = 101, message = "����ʧ��")})
    @ResponseBody ReturnInfo addState(@RequestBody State state) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, stateService.addState(state));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("��Ӷ�̬ʧ�ܣ�������");
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
               //����uuid��Ϊ�ļ�����
               String uuid = UUID.randomUUID().toString().replaceAll("-","");
               //��MultipartFileת��Ϊfile
               CommonsMultipartFile cf= (CommonsMultipartFile)mf;
               DiskFileItem fi = (DiskFileItem)cf.getFileItem();
               String picType= CheckExcelFileTypeUtil.getFileType(fi.getStoreLocation());
               path=uuid+"."+picType;

               mf.transferTo(new File(PropertiesUtil.getRequestPropertiesSaveAddress()+path));
               paths+=front+path+",";
               stateService.addPics(sid,paths);
           }
       }
       returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,"����ɹ�");
       return returnInfo;
   }catch (Exception e){
       throw new ServiceException("����Ķ�̬ʧ��");
   }
    }



    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addLike/{stateID}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(
            value = "����",
            notes = "����̬���ޣ���Ҫ�û�������̬id",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "���޳ɹ�"),
            @ApiResponse(code = 101, message = "����ʧ��")})
    @ResponseBody ReturnInfo addLike(@PathVariable String stateID) throws ServiceException {
        try {
            stateService.addLike(stateID);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, "���޳ɹ�");
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("����ʧ�ܣ�������");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addComment", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "����",
            notes = "����̬���ۣ���Ҫ���붯̬����",
            response = Comment.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "���۳ɹ�"),
            @ApiResponse(code = 101, message = "����ʧ��")})
    @ResponseBody
    ReturnInfo addComment(@RequestBody Comment comment) throws ServiceException {
        try {
            stateService.addComment(comment);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, "���۳ɹ�");
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("����ʧ�ܣ�������");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getAllComments/{stateID}/{currPage}",method = RequestMethod.GET)
    @ApiOperation(
            value = "��ȡ�û�����",
            notes = "���ݶ�̬ID��ȡ���ۣ�ͬʱ��Ҫ����ҳ��",
            response = Comment.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "��ȡ�ɹ�"),
            @ApiResponse(code = 101, message = "����ʧ��")})
    @ResponseBody
    ReturnInfo getAllComments(@PathVariable String stateID,@PathVariable int currPage) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,stateService.getAllComment(currPage,stateID));
            return returnInfo;
        }
        catch (Exception e){
            throw new ServiceException("��ȡʧ��");
        }
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "deleteState/{stateID}", method = RequestMethod.DELETE)
    @ApiOperation(
            value = "ɾ����̬",
            notes = "���붯̬id",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo deleteState(@PathVariable String stateID) throws ServiceException {
        try {
            stateService.deleteState(stateID);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, "ɾ���ɹ�");
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("ɾ��ʧ�ܣ�������");
        }
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getHotStates/{currPage}",method = RequestMethod.GET)
    @ApiOperation(
            value = "��ȡ���Ŷ�̬",
            notes = "��ȡ���Ŷ�̬������ҳ��",
            response = State.class
    )
    public
    @ResponseBody
    ReturnInfo getHotStates(@PathVariable int currPage) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,stateService.getHotStates(currPage));
            return returnInfo;
        }catch (Exception e){
            throw new ServiceException("��ȡ���Ŷ�̬ʧ��");
        }
    }



    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getAllStates/{uid}/{currPage}", method = RequestMethod.GET)
    @ApiOperation(
            value = "��ȡ��̬",
            notes = "��ȡ��̬������ҳ��",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo getAllStates(@PathVariable String uid, @PathVariable Integer currPage) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, stateService.findAllState(currPage, uid));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("��ȡʧ�ܣ�������");
        }

    }

}
