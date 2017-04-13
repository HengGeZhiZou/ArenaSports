package team.lw.arena.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.lw.arena.entity.ReturnInfo;
import team.lw.arena.entity.UserInfo;
import team.lw.arena.entity.UserLogin;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.UserInfoService;
import team.lw.arena.util.CreateSafeCode;
import team.lw.arena.util.ResultCode;
import team.lw.arena.util.SendMailUtil;
import team.lw.arena.util.Token;

import javax.annotation.Resource;

import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS;
import static team.lw.arena.util.ResultCode.REQUEST_SUCCESS_MSG;


@Controller
@RequestMapping("/Arena/userInfo/*")
public class UserInfoController {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;
    private ReturnInfo returnInfo = new ReturnInfo();

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "return")
    @ResponseBody
    public ReturnInfo returnI(){
        returnInfo.setInfo(ResultCode.SEND_FAIL,"你还未登录");
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "registerUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnInfo registerUser(@RequestBody UserLogin userLogin) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.registerService(userLogin));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("邮箱已被注册");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "loginCheck", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnInfo loginCheck(@RequestBody UserLogin userLogin) throws Exception {

        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getEmail(), userLogin.getPassword());
            token.setRememberMe(true);
            currentUser.login(token);
            String UserToken= userInfoService.addToken(userLogin.getEmail());
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG,UserToken);
        }
        return returnInfo;
    }
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "logout",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public ReturnInfo logout(@RequestBody UserLogin userLogin){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            userInfoService.deleteToken(userLogin.getEmail());
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG);
        }
        return  returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnInfo updatePassword(@RequestBody UserLogin userLogin) throws ServiceException {
        returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.updatePasswordService(userLogin));
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getUserInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo getUserInfo(@PathVariable String id) throws ServiceException {
        returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.findUserInfo(id));
        return returnInfo;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "addUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnInfo addUserInfo(@RequestBody UserInfo userInfo) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.addUserInfoService(userInfo));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("手机号已被使用");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnInfo updateUserInfo(@RequestBody UserInfo userInfo) throws ServiceException {
        try {
            returnInfo.setInfo(REQUEST_SUCCESS, REQUEST_SUCCESS_MSG, userInfoService.updateUserInfoService(userInfo));
            return returnInfo;
        } catch (Exception e) {
            throw new ServiceException("手机号已被使用");
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "checkEmail/{email}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo checkEmail(@PathVariable String email) throws ServiceException {

            if (userInfoService.checkEmailExistService(email)){
                throw new ServiceException("邮箱已被注册");
            }
            String securityCode = CreateSafeCode.getRandCode();
            SendMailUtil sendMailUtil = new SendMailUtil(securityCode, email);
            sendMailUtil.start();
            returnInfo.setInfo(REQUEST_SUCCESS,REQUEST_SUCCESS_MSG,securityCode);
            return returnInfo;
    }
}
