package callableStatementEx;

import jdbc_boards.util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class MemberInsert {
//    Connection 객체에 autocommit이 들어있다 기본값은 true
    static Connection conn = DBUtil.getConnection();

    public static void main(String[] args) {
        String sql = "{call SP_MEMBER_INSERT(?,?,?,?,?)}";
        String m_userid = "black pink";
        String m_pwd = "blackpink1234";
        String m_email = "blackpink@gmail.com";
        String m_hp = "010-1234-1234";

        try(CallableStatement call = conn.prepareCall(sql))
        {
            // in 파라미터 셋팅
            call.setString(1,m_userid);
            call.setString(2,m_pwd);
            call.setString(3,m_email);
            call.setString(4,m_hp);

            //out 파라미터 셋팅
            call.registerOutParameter(5, Types.INTEGER);
            //실행
            call.execute();

            int rtn = call.getInt(5);

            if (rtn == 100) {
                // conn.rollback();
                System.out.println("이미 가입된 사용자 입니다.");
            } else {
//                conn.commit();
                System.out.println("회원 가입이 되었습니다. 감사합니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
