package team.lw.arena.util;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateSafeCode {
    public static String getRandCode() {
        String[] beforeShuffle = new String[]{"2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (Object aList : list) {
            sb.append(aList);
        }
        return sb.toString().substring(5, 9);
    }
}
