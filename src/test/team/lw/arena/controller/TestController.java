package team.lw.arena.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/Test/*")
public class TestController {

//    @Resource(name = "userServiceImpl")
//    private UserService userService;

    //访问地址：http://localhost:8080/Test/returnTest/user/1000/roles/1200
    @RequestMapping(value = "returnString", produces = {"text/plain;charset=UTF-8"})
    //produces用于解决返回中文乱码问题，application/json;为json解决中文乱码
    @ResponseBody       //用于返回字符串,不写即返回视图
    public String returnString() {
        return "hello return string 这是中文，并没有乱码";
    }

    @RequestMapping(value = "returnTest/user/{userId}/roles/{roleId}")
    @ResponseBody
    public String returnTest(@PathVariable("userId") String id, @PathVariable("roleId") String roleId) {
        System.out.println("用户id为：" + id);
        System.out.println("角色id为：" + roleId);
        return "success";
    }

//    @RequestMapping(value = "saveUser/{username}")
//    @ResponseBody
//    public User saveUser(@PathVariable("username") String username) {
////        userService.addUser(username);
////        return "ok";
////        return userService.addUser(username);
//
//    }

//    @RequestMapping(value = "getUser",method = RequestMethod.POST,produces="application/json")
//    public @ResponseBody
//    User getUser(@RequestBody User userInfo){
//        userService.addUser(userInfo);
//        return userInfo;
//    }
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value = "result",method = RequestMethod.POST,produces = "application/json")
//    public @ResponseBody
//    ReturnInfo returnInfo(){
//        User user=new User();
//        user.setUid("1");
//        user.setUsername("中共人");
//        user.setPassword("美国热播");
//        ReturnInfo returnInfo=new ReturnInfo();
//        returnInfo.setCode(100);
//        returnInfo.setMsg("msg");
//        returnInfo.setObj(user);
//        return returnInfo;
//    }
//    @RequestMapping(value="/Exception/{id}", method=RequestMethod.GET,produces = "application/json;utf-8")
//    public void controller(@PathVariable("id") Integer id) throws Exception{
//        switch(id) {
//            case 1:
//                throw new BusinessException("10", "controller10");
//            case 2:
//                System.out.println(10/0);
//            case 3:
//                throw new BusinessException("30", "controller30");
//            case 4:
//                throw new BusinessException("40", "controller40");
//            case 5:
//                throw new BusinessException("50", "controller50");
//            default:
//                throw new ParameterException("Controller Parameter Error");
//        }
//    }

    @RequestMapping(value = "/testExceptionHandlerExceptionResolver/{i}")
    @ResponseBody
    public String testExceptionHandlerExceptionResolver(@PathVariable("i") int i){
        System.out.println("result: " + (10 / i));
        return "success";
    }
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ReturnInfo handle(Exception ex) {
//        System.out.println("GlobalExceptionHandler: " + ex);
//        ReturnInfo returnInfo=new ReturnInfo();
//        returnInfo.setCode(400);
//        returnInfo.setMsg(ex.getMessage());
//        return returnInfo;
//    }

}
