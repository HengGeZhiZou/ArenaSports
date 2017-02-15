package team.lw.arena.util;


public interface ResultCode {
    int REQUEST_SUCCESS = 100;
    String REQUEST_SUCCESS_MSG = "����ɹ�";

    int SEND_FAIL = 101;
    String SEND_FAIL_MSG = "����ʧ��";

    int LOGIN_FAIL = 102;
    String LOGIN_FAIL_MSG = "��¼ʧ��";

    int REGISTER_FAIL = 103;
    String REGISTER_FAIL_MSG = "ע��ʧ�ܣ������ѱ�ʹ��";

    int ADD_USERINFO_FAIL = 104;
    String ADD_USERINFO_FAIL_MSG = "����û�ʧ�ܣ��û������ֻ����ѱ�ʹ��";

    int UPDATE_FAIL = 105;
    String UPDATE_FAIL_MSG = "����ʧ��";

    int EMAIL_EXIST=106;
    String EMAIL_EXIST_MSG="�����ѱ�ע��";

    int FIND_USERINFO_FAIL=107;
    String FIND_USERINFO_FAIL_MSG="�����û�ʧ��,���û�������";

    int NO_ONE_NEARBY=108;
    String NO_ONE_NEARBY_MSG="�����Ѿ�û���û���";

    int FIND_NEARBYPEOPLE_FAIL=109;
    String FIND_NEARBYPEOPLE_FAIL_MSG="���Ҹ����û�ʧ��";
}
