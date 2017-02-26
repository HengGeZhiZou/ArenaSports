package team.lw.arena.util;


public class Token {
    public static String getToken(String s){
        return s+System.currentTimeMillis();
    }
}
