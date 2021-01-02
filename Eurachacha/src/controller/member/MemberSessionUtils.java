package controller.member;

import javax.servlet.http.HttpSession;

public class MemberSessionUtils {
    public static final String MEMBER_SESSION_KEY = "memberId";

    /* ���� �α����� ������� ID�� ���� */
    public static String getLoginMemberId(HttpSession session) {
        String memberId = (String)session.getAttribute(MEMBER_SESSION_KEY);
        return memberId;
    }

    /* �α����� ���������� �˻� */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginMemberId(session) != null) {
            return true;
        }
        return false;
    }

    /* ���� �α����� ������� ID�� memberId���� �˻� */
    public static boolean isLoginUser(String memberId, HttpSession session) {
        String loginMember = getLoginMemberId(session);
        if (loginMember == null) {
            return false;
        }
        return loginMember.equals(memberId);
    }
}
