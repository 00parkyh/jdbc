package callableStatementEx;

import jdbc_boards.util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

//MemberList 클래스에서 callableStatement 방식으로 회원들의 리스트를 출력하는 기능 구현하세요
public class MemberList {

    static Connection conn = DBUtil.getConnection();

    public static void main(String[] args) {

        String sql = "{call SP_MEMBER_LIST()}";
        try(CallableStatement call = conn.prepareCall(sql)){
            ResultSet rs = call.executeQuery();
            while(rs.next()) {
                    int seq = rs.getInt(1);
                    String userid = rs.getString(2);
                    String pwd = rs.getString(3);
                    String email = rs.getString(4);
                    String hp = rs.getString(5);
                    Date registdate = rs.getDate(6);
                    int point = rs.getInt(7);

                    System.out.println("회원번호 : " + seq );
                    System.out.println("아이디 : " + userid );
                    System.out.println("비밀번호 : " + pwd );
                    System.out.println("이메일 : " + email );
                    System.out.println("폰 번호  : " + hp );
                    System.out.println("가입 날짜 : " + registdate );
                    System.out.println("포인트 : " + point );
            }
        } catch (Exception e) {
            System.out.println("에러");
        }

    }
}
