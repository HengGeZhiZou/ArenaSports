package team.lw.arena.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties propertiesA = null;
    private static Properties propertiesC = null;
    private static Properties propertiesRequest=null;
    static {
        try {
            propertiesA=new Properties();
            propertiesC=new Properties();
            propertiesRequest=new Properties();
            propertiesA.load(new FileInputStream(
                    new File(PropertiesUtil.class.getClassLoader().getResource("mailAccount.properties").getPath())));
            propertiesC.load(new FileInputStream(
                    new File(PropertiesUtil.class.getClassLoader().getResource("mailConnect.properties").getPath())));
            propertiesRequest.load(new FileInputStream(
                    new File(PropertiesUtil.class.getClassLoader().getResource("requestAddress.properties").getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAccountForm() {
        return propertiesA.getProperty("mail.from");
    }

    public static String getAccountUsername() {
        return propertiesA.getProperty("mail.username");
    }

    public static String getAccountPassword() {
        return propertiesA.getProperty("mail.password");
    }

    public static String getAccountHost() {
        return propertiesA.getProperty("mail.smtp.host");
    }

    public static Properties getPropertiesConnect() {
        return propertiesC;
    }

    public static String getRequestPropertiesAddress(){
        return propertiesRequest.getProperty("request.address");
    }

    public static String getRequestPropertiesSaveAddress(){
        return propertiesRequest.getProperty("request.saveAddress");
    }
}
