package team.lw.arena.util;




import team.lw.arena.entity.UserInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDistance {
  public static List<UserInfo> getComparatorDistance(List<UserInfo> lists){
      Collections.sort(lists, new Comparator<UserInfo>() {
          @Override
          public int compare(UserInfo o1, UserInfo o2) {
              if(o1.getDistance()>o2.getDistance()){
                  return 1;
              }
              else if(o1.getDistance()==o2.getDistance()){
                  return 0;
              }
              return -1;
          }
      });
      return lists;
  }
}
