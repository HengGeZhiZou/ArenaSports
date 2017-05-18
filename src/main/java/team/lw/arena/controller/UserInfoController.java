package team.lw.arena.controller;

import io.swagger.annotations.*;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import team.lw.arena.entity.ReturnInfo;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.entity.UserLogin;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.UserInfoService;
import team.lw.arena.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.UUID;

import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS;
import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS_MSG;


@Controller
@Api(value = "/Arena/userInfo/*", description = "���û���Ϣ���в���")
@RequestMapping("/Arena/userInfo/*")
public class UserInfoController {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;
    private ReturnInfo returnInfo = new ReturnInfo();


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "return", method = RequestMethod.GET)
    @ApiOperation(
            value = "���ص�¼״̬",
            notes = "�õ��Ƿ��½�ɹ�",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo returnI() {
        returnInfo.setInfo(ResultCode.SEND_FAIL, "�㻹δ��¼");
        return returnInfo;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    @ApiOperation(
            value = "�û�ע��",
            notes = "�����û�ע��,��������ID",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "����ɹ����������ɵ����û�ID"),
            @ApiResponse(code = 101, message = "����ʧ��")})
    @ResponseBody
    ReturnInfo registerUser(
            @ApiParam(value = "�û�����", required = true) @RequestParam String email,
            @ApiParam(value = "�û�����", required = true) @RequestParam String password) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.registerService(email, password));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("�����ѱ�ע��");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "adminLogin", method = RequestMethod.POST)
    @ResponseBody
    public String adminLoginCheck(@RequestParam String email, @RequestParam String password) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(email, password);
            token.setRememberMe(true);
            currentUser.login(token);
            String UserToken = userInfoService.addToken(email);
//            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,UserToken);
        }
        return "forward:swagger-ui.html#/";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "loginCheck", method = RequestMethod.POST)
    @ApiOperation(
            value = "�û���¼",
            notes = "�û���¼����,��������token",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "����ɹ��������û�ID"),
            @ApiResponse(code = 101, message = "����ʧ��")})
    @ResponseBody
    ReturnInfo loginCheck(@ApiParam(value = "�û�����", required = true) @RequestParam String email,
                          @ApiParam(value = "�û�����", required = true) @RequestParam String password) throws Exception {

        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(email, password);
            token.setRememberMe(true);
            currentUser.login(token);
//            ��ȡtoken
//            String UserToken = userInfoService.addToken(email);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.getUserLogin(email).getId());
        }
        return returnInfo;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "logout", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "�û�ע��",
            notes = "�û�ע����¼",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); //  session �����٣���SessionListener����session���٣�����Ȩ�޻���
//            userInfoService.deleteToken(userLogin.getEmail());
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG);
        }
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "�޸�����",
            notes = "�޸��û�����",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo updatePassword(@RequestBody UserLogin userLogin) throws ServiceException {
        returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.updatePasswordService(userLogin));
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getUserInfo/{id}", method = RequestMethod.GET)
    @ApiOperation(
            value = "��ȡ�û���Ϣ",
            notes = "����id��ȡ�û���Ϣ��id����url��",
            response = UserInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "����ɹ��������û���Ϣ"),
            @ApiResponse(code = 101, message = "����ʧ��")})
    @ResponseBody
    ReturnInfo getUserInfo(@PathVariable String id) throws ServiceException {
        returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.findUserInfo(id));
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "�û���д��Ϣ",
            notes = "�û���д��ϸ��Ϣ���û������Ա��",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "����ɹ��������û�ID"),
            @ApiResponse(code = 101, message = "����ʧ��,�ֻ������ѱ�ʹ��")})
    @ResponseBody
    ReturnInfo addUserInfo(@RequestBody UserInfo userInfo) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.addUserInfoService(userInfo));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("�ֻ����ѱ�ʹ��");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "settingPortrait/{uid}", method = RequestMethod.POST)
    @ApiOperation(
            value = "����ͷ��",
            notes = "����multipart/form-data��������ͷ��",
            response = ReturnInfo.class
    )
    public
    @ResponseBody
    ReturnInfo settingPortrait(@PathVariable String uid,@RequestParam MultipartFile file, HttpServletRequest request) throws ServiceException {

        try {
            String front= PropertiesUtil.getRequestPropertiesAddress();
            String path = "";
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //��MultipartFileת��Ϊfile
            CommonsMultipartFile cf= (CommonsMultipartFile)file;
            DiskFileItem fi = (DiskFileItem)cf.getFileItem();
            //�ж��ϴ�ͼƬ����
            String picType= CheckExcelFileTypeUtil.getFileType(fi.getStoreLocation());
            path = uuid + "." + picType;
            file.transferTo(new File( PropertiesUtil.getRequestPropertiesSaveAddress()+path));
            userInfoService.addPortrait(uid,front+path);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("����ͷ��ʧ��" );
        }
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "�޸��û���Ϣ",
            notes = "�޸��û���Ϣ���û������Ա��",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo updateUserInfo(@RequestBody UserInfo userInfo) throws ServiceException {
        try {
            userInfo.setPlaying(false);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.updateUserInfoService(userInfo));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("�ֻ����ѱ�ʹ��");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "checkEmail", method = RequestMethod.POST)
    @ApiOperation(
            value = "����û����䣬��ȡ��֤��",
            notes = "�������䣬post�����ַ������Ƿ���ã�������֤��",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "����ɹ���������֤��"),
            @ApiResponse(code = 101, message = "����ʧ��,�����ѱ�ע��")})
    @ResponseBody
    ReturnInfo checkEmail(@RequestParam String email) throws ServiceException {
        if (userInfoService.checkEmailExistService(email)) {
            throw new ServiceException("�����ѱ�ע��");
        }
        String securityCode = CreateSafeCode.getRandCode();
        SendMailUtil sendMailUtil = new SendMailUtil(securityCode, email);
        sendMailUtil.start();
        returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, securityCode);
        return returnInfo;
    }
}
