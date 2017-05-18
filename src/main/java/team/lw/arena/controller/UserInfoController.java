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
@Api(value = "/Arena/userInfo/*", description = "对用户信息进行操作")
@RequestMapping("/Arena/userInfo/*")
public class UserInfoController {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;
    private ReturnInfo returnInfo = new ReturnInfo();


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "return", method = RequestMethod.GET)
    @ApiOperation(
            value = "返回登录状态",
            notes = "得到是否登陆成功",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo returnI() {
        returnInfo.setInfo(ResultCode.SEND_FAIL, "你还未登录");
        return returnInfo;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    @ApiOperation(
            value = "用户注册",
            notes = "进行用户注册,返回生成ID",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "请求成功，返回生成的新用户ID"),
            @ApiResponse(code = 101, message = "请求失败")})
    @ResponseBody
    ReturnInfo registerUser(
            @ApiParam(value = "用户邮箱", required = true) @RequestParam String email,
            @ApiParam(value = "用户密码", required = true) @RequestParam String password) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.registerService(email, password));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("邮箱已被注册");
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
            value = "用户登录",
            notes = "用户登录操作,返回生成token",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "请求成功，返回用户ID"),
            @ApiResponse(code = 101, message = "请求失败")})
    @ResponseBody
    ReturnInfo loginCheck(@ApiParam(value = "用户邮箱", required = true) @RequestParam String email,
                          @ApiParam(value = "用户密码", required = true) @RequestParam String password) throws Exception {

        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(email, password);
            token.setRememberMe(true);
            currentUser.login(token);
//            获取token
//            String UserToken = userInfoService.addToken(email);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.getUserLogin(email).getId());
        }
        return returnInfo;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "logout", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "用户注销",
            notes = "用户注销登录",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); //  session 会销毁，在SessionListener监听session销毁，清理权限缓存
//            userInfoService.deleteToken(userLogin.getEmail());
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG);
        }
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "修改密码",
            notes = "修改用户密码",
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
            value = "获取用户信息",
            notes = "根据id获取用户信息，id附在url上",
            response = UserInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "请求成功，返回用户信息"),
            @ApiResponse(code = 101, message = "请求失败")})
    @ResponseBody
    ReturnInfo getUserInfo(@PathVariable String id) throws ServiceException {
        returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.findUserInfo(id));
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "用户填写信息",
            notes = "用户填写详细信息，用户名，性别等",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "请求成功，返回用户ID"),
            @ApiResponse(code = 101, message = "请求失败,手机号码已被使用")})
    @ResponseBody
    ReturnInfo addUserInfo(@RequestBody UserInfo userInfo) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.addUserInfoService(userInfo));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("手机号已被使用");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "settingPortrait/{uid}", method = RequestMethod.POST)
    @ApiOperation(
            value = "设置头像",
            notes = "利用multipart/form-data进行设置头像",
            response = ReturnInfo.class
    )
    public
    @ResponseBody
    ReturnInfo settingPortrait(@PathVariable String uid,@RequestParam MultipartFile file, HttpServletRequest request) throws ServiceException {

        try {
            String front= PropertiesUtil.getRequestPropertiesAddress();
            String path = "";
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //将MultipartFile转化为file
            CommonsMultipartFile cf= (CommonsMultipartFile)file;
            DiskFileItem fi = (DiskFileItem)cf.getFileItem();
            //判断上传图片类型
            String picType= CheckExcelFileTypeUtil.getFileType(fi.getStoreLocation());
            path = uuid + "." + picType;
            file.transferTo(new File( PropertiesUtil.getRequestPropertiesSaveAddress()+path));
            userInfoService.addPortrait(uid,front+path);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("设置头像失败" );
        }
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(
            value = "修改用户信息",
            notes = "修改用户信息，用户名，性别等",
            response = ReturnInfo.class
    )
    @ResponseBody
    public ReturnInfo updateUserInfo(@RequestBody UserInfo userInfo) throws ServiceException {
        try {
            userInfo.setPlaying(false);
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.updateUserInfoService(userInfo));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("手机号已被使用");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "checkEmail", method = RequestMethod.POST)
    @ApiOperation(
            value = "检查用户邮箱，获取验证码",
            notes = "传入邮箱，post邮箱地址，检查是否可用，发送验证码",
            response = ReturnInfo.class
    )
    public
    @ApiResponses(value = {@ApiResponse(code = 100, message = "请求成功，返回验证码"),
            @ApiResponse(code = 101, message = "请求失败,邮箱已被注册")})
    @ResponseBody
    ReturnInfo checkEmail(@RequestParam String email) throws ServiceException {
        if (userInfoService.checkEmailExistService(email)) {
            throw new ServiceException("邮箱已被注册");
        }
        String securityCode = CreateSafeCode.getRandCode();
        SendMailUtil sendMailUtil = new SendMailUtil(securityCode, email);
        sendMailUtil.start();
        returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, securityCode);
        return returnInfo;
    }
}
