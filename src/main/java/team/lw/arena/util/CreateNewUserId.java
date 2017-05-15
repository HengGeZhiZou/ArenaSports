package team.lw.arena.util;


public class CreateNewUserId {
    public static String getNewUserId(String id) {   //生成新的id

        String today = TimestampUtil.getTimestamp();
        if (id == null) {
            id = today + "000001";
            return id;
        } else {
            if (today.substring(2, 4).equals(id.substring(2, 4))) {
                String num = id.substring(4);
                int i = Integer.parseInt(num);
                i++;
                String nid = String.valueOf(i);
                i = 6 - nid.length();
                for (int j = 0; j < i; j++) {
                    nid = "0" + nid;
                }
                nid = today + nid;
                return nid;
            } else {
                return today + "000001";
            }
        }
    }

//    创建用户动态ID  比赛记录id
    public static String crateStateId(String stateOrRecordId) {
        String first = stateOrRecordId.substring(0, 10);
        String count = stateOrRecordId.substring(10);
        int i = Integer.parseInt(count);
        i++;
        String nid=String.valueOf(i);
        i=4-nid.length();
        for (int j=0;j<i;j++){
            nid="0"+nid;
        }
        return first+nid;
    }
}
