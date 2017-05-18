package team.lw.arena;


import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import team.lw.arena.dao.GameDao;
import team.lw.arena.dao.StateDao;
import team.lw.arena.dao.TestDao;
import team.lw.arena.entity.*;
import team.lw.arena.exception.ServiceException;
import team.lw.arena.service.*;
import team.lw.arena.service.impl.TestServiceImpl;
import team.lw.arena.util.*;


import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static team.lw.arena.util.CheckExcelFileTypeUtil.getFileType;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test {
    private ClassPathXmlApplicationContext ctx;


    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    @Resource(name = "recordServiceImpl")
    private RecordService recordService;

    @Resource(name = "positionServiceImpl")
    private PositionService positionService;

    @Resource(name="gameServiceImpl")
    private GameService gameService;

    @Resource(name = "stateServiceImpl")
    private StateService stateService;

    @org.junit.Test
    public void updateToken(){
        userInfoService.addToken("huge");
    }

    @org.junit.Test
    public void deleteToken(){
        userInfoService.deleteToken("huge");
    }

    @org.junit.Test
    public void testLogin() throws Exception {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("zl");
        userLogin.setPassword("ssss");
        Gson gson = new Gson();
        System.out.println(gson.toJson(userLogin));
        System.out.println(userInfoService.loginService(userLogin));
    }

    @org.junit.Test
    public void testRun(){
        System.out.println("junit run");
    }

    @org.junit.Test
    public void testRegister() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("qweroooo");
        userLogin.setPassword("ssss");
        try {
//            System.out.println(userInfoService.registerService(userLogin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("1705000003");
        userInfo.setName("恒哥直走");
//        userInfo.setPortrait("aaa");
        userInfo.setSex("男");
        userInfo.setPlaying(true);
        userInfo.setAge("11");
        userInfo.setHeight("1.80");
        userInfo.setWeight("90");
        userInfo.setProfiles("热爱运动热爱生活");
        userInfo.setPhone("18328439623");
        try {
            System.out.println(userInfoService.updateUserInfoService(userInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void TestMap() {
        ReturnInfo returnInfo = new ReturnInfo();
//        returnInfo.setStatus(ResultCode.loginFail);
//        returnInfo.setObj("123123123");
//        Gson gson = new Gson();
//        String a = gson.toJson(returnInfo);
//        System.out.println(a);
//        ReturnInfo returnInfo1 = gson.fromJson(a, ReturnInfo.class);
//        Map<Integer,String> map=returnInfo1.getStatus();
//        System.out.println(map.keySet());
//        System.out.println(map.values());
    }


    @org.junit.Test
    public void testSpring() {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TestServiceImpl ts = (TestServiceImpl) ctx.getBean("testService");
        TestServiceImpl ts1 = (TestServiceImpl) ctx.getBean("testService");
        System.out.println(ts.equals(ts1));
        System.out.println(ts.testDao.equals(ts1.testDao));
        ts1.say();
        ts.say();
    }

    @org.junit.Test
    public void TestDao() {
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TestDao testDao = (TestDao) ctx.getBean("lii");
        System.out.println(testDao.findUser("1701000001").toString());
    }

    @org.junit.Test
    public void testLog4j() {
//        PropertyConfigurator.configure("log4j.properties");
        Log log = LogFactory.getLog(getClass());
        log.debug("debug 级别日志");
        log.info("info 级别日志");
        log.warn("warn 级别日志");
        log.error("error 级别日志");
        log.fatal("fatal 级别日志");
    }

    @org.junit.Test
    public void testUpdatePassword() {
        UserLogin userLogin = new UserLogin();
        userLogin.setId("1702000001");
        userLogin.setPassword("mi");
        try {
            userInfoService.updatePasswordService(userLogin);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void sendMail() {
        SendMailUtil sendMailUtil = new SendMailUtil("heelo", "1018676477@qq.com");
        sendMailUtil.run();
    }

    @org.junit.Test
    public void checkEmailExist() {
        try {
            System.out.println(userInfoService.checkEmailExistService("dsadasdasd"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testCreateSafeCode() {
        System.out.println("随机数字为" + CreateSafeCode.getRandCode());
        System.out.println("随机数字为" + CreateSafeCode.getRandCode());
        System.out.println("随机数字为" + CreateSafeCode.getRandCode());
        System.out.println("随机数字为" + CreateSafeCode.getRandCode());
        System.out.println("随机数字为" + CreateSafeCode.getRandCode());
        System.out.println("随机数字为" + CreateSafeCode.getRandCode());
    }

    @org.junit.Test
    public void testGetUserInfo() {
        try {
            System.out.println(userInfoService.findUserInfo("1701000001").toString());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

//    @org.junit.Test
//    public void testRecordCount() {
//        System.out.println(recordService.getCount());
//    }

    @org.junit.Test
    public void testGetuser(){
        try {
            System.out.println(userInfoService.getUserLogin("sdass"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testRecord() {

        List<Record> lists = recordService.findByPage(1,"1704000003");
        for (Record record : lists) {
            System.out.println(record.toString());
        }
    }
    @org.junit.Test
    public void testDistance(){
        System.out.println("相差距离"+ GetPositionUtil.distance(103.826878,30.673742,103.827334,30.673978));
    }

    @org.junit.Test
    public void testAround(){
        List<UserInfo> lists= null;
        MobileUser mobileUser=new MobileUser();
        mobileUser.setMuUId("1701000001");
        mobileUser.setCurrPage(1);
        mobileUser.setDistance(1000000000);
        mobileUser.setMuLatitude(new BigDecimal(13.212899));
        mobileUser.setMuLongitud(new BigDecimal(29.221234));
        try {
            lists = positionService.getAroundPeople(mobileUser);
            System.out.println(lists.size());
            for (UserInfo userInfo:lists){
                System.out.println(userInfo.toString());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void testUpdatePosition(){
        MobileUser mobileUser=new MobileUser();
//        mobileUser.setMuId(1);
        mobileUser.setMuUId("1701080003");
        mobileUser.setMuLatitude(new BigDecimal(13));
        mobileUser.setMuLongitud(new BigDecimal(29.1231));

            positionService.updatePosition(mobileUser);

    }

    @org.junit.Test
    public void createRoom(){
        try {
            System.out.println(gameService.CreateHouse("1705000001"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void inviteOthers(){
        try {
            System.out.println(gameService.inviteOthers("1705000001","1705000004"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void overGame() throws ServiceException {
//        gameService.endGame("1704000003");
    }

    @org.junit.Test
    public void startGame(){
//        try {
////            gameService.startGame("1704000003");
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
    }


    @org.junit.Test
    public void addState() {
//        System.out.println(CreateNewUserId.crateStateId("17010000010001"));
        State state=new State();
        state.setId("1701000001");
        state.setText("今天dasdmaishdjaisd");
//        state.setDate(new Timestamp(System.currentTimeMillis()));
//        state.setPhoto("http://file.koolearn.com/20170307/14888803845500.jpg");
//        state.setPosition("中国");
//        state.setLike(10);
        System.out.println(stateService.addState(state));

    }

    @org.junit.Test
    public void addLikes(){
        stateService.addLike("17010000020001");
    }


    @org.junit.Test
    public void addComment(){
        Comment comment=new Comment();
        comment.setCommentsId("17010000020002");
//        comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
        comment.setContent("dsadsaaaaaaaaaaaa");
        comment.setReviewerId("1701000005");
        stateService.addComment(comment);
    }

    @org.junit.Test
    public void deleteState(){
        stateService.deleteState("17010000010002");
    }


    @org.junit.Test
    public void findAllState(){
        List<State> states=stateService.findAllState(1,"1704000003");
        for(State state:states){
            System.out.println(state);
        }
    }

    @org.junit.Test
    public void findAllComments(){
       List<Comment> comments=stateService.getAllComment(1,"17010000020001");
       for (Comment comment:comments){
           System.out.println(comment);
       }
    }

    @org.junit.Test
    public void getHotStates(){
        List<State> states=stateService.getHotStates(1);
        for (State state:states){
            System.out.println(state);
        }
    }

    @org.junit.Test
    public void addPortrait(){
        userInfoService.addPortrait("1701000001","1dsad/asdasd/sdasdasdaedsdaedasdasdwda.jpg");
    }

    @org.junit.Test
    public void testFileType(){
        File file=new File("D:\\pic\\0a0f8c8547f3498eaee0f94494a1edca.null");
         String fileType = getFileType(file);
        System.out.println(fileType);
    }

    @org.junit.Test
    public void testAddStates(){
        stateService.addPics("17040000030093","" +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg," +
                "http://192.168.0.16:8080/pic/6e2cca9b7be64191bfafe521f6efe4b5.jpg,"
          );
    }


    @org.junit.Test
    public void testFindRecord(){

      List<Record> list=recordService.findByPage(1,"1705000004");
      for (Record record:list){
          System.out.println(record);
      }
    }

    @org.junit.Test
    public  void  testGetAllUser(){
        SixPeopleRoom sixPeopleRoom=new SixPeopleRoom();
        sixPeopleRoom.setCharacter01("qwertty");
        sixPeopleRoom.setCharacter02("danudsd");
        sixPeopleRoom.setCharacter03("dasdsdas");
        sixPeopleRoom.setCharacter04("lydsd");
        sixPeopleRoom.setCharacter05("123123123");
        sixPeopleRoom.setCharacter06("powqeqw");
        List<UserInfo> lists=InviteUser.getAllUser(sixPeopleRoom);
        for (UserInfo userInfo:lists){
            System.out.println(userInfo.getId());
        }
    }

    @org.junit.Test
    public void testStart() throws ServiceException {
        gameService.start("1705000001","足球","四川省成都市温江区");
    }


    @org.junit.Test
    public void testCurrRecord(){
       List<Record> list= recordService.currRecord(1);
    for (Record record:list){
        System.out.println(record);
    }
    }
    @org.junit.Test
    public void testqqq(){
        String s = "adgsh,jkhjkh,,gh";
        String[] a = s.split(",");
        List<String> strings = Arrays.asList(a);
        for (String s1 : a) {
            System.out.println("--:"+s1);
        }
        System.out.println(a.length+"---"+strings.size());
    }
}
