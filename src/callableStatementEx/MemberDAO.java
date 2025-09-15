package callableStatementEx;

import jdbc_boards.vo.Board;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MemberDAO {
    static Connection conn ;
    static Scanner sc = new Scanner(System.in);

    //회원 가입
    public static void join() {
       conn = DBUtil.getConnection();
        String sql = "{call SP_MEMBER_INSERT(?,?,?,?,?)}";
        System.out.print("아이디 : ");
        String m_userid = sc.nextLine();
        System.out.println(" ");
        System.out.print("비밀번호 : ");
        String m_pwd = sc.nextLine();
        System.out.println(" ");
        System.out.print("이메일 : ");
        String m_email = sc.nextLine();
        System.out.println(" ");
        System.out.print("휴대폰 번호 : ");
        String m_hp = sc.nextLine();
        System.out.println(" ");

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

    public static void selectAll() {
        conn = DBUtil.getConnection();
        List<Board> boardList = new ArrayList<>();
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

                System.out.print("회원번호 : " + seq );
                System.out.print(" | ");
                System.out.print("아이디 : " + userid );
                System.out.print(" | ");
                System.out.print("비밀번호 : " + pwd );
                System.out.print(" | ");
                System.out.print("이메일 : " + email );
                System.out.print(" | ");
                System.out.print("폰 번호  : " + hp );
                System.out.print(" | ");
                System.out.print("가입 날짜 : " + registdate );
                System.out.print(" | ");
                System.out.print("포인트 : " + point );
                System.out.println(" | ");
            }
        } catch (Exception e) {
            System.out.println("에러");
        }
    }

    public static void selectONE() {
        conn = DBUtil.getConnection();
        List<Board> boardList = new ArrayList<>();
        String sql = "{call SP_MEMBER_LIST_ONE(?)}";
        System.out.println("조회하고 싶은 아이디를 입력하세요 : ");
        String m_userid = sc.nextLine();

        try(CallableStatement call = conn.prepareCall(sql)){
            ResultSet rs = call.executeQuery();
            call.setString(1,m_userid);
            while(rs.next()) {
                int seq = rs.getInt(1);
                String userid = rs.getString(2);
                String pwd = rs.getString(3);
                String email = rs.getString(4);
                String hp = rs.getString(5);
                Date registdate = rs.getDate(6);
                int point = rs.getInt(7);

                System.out.print("회원번호 : " + seq );
                System.out.print(" | ");
                System.out.print("아이디 : " + userid );
                System.out.print(" | ");
                System.out.print("비밀번호 : " + pwd );
                System.out.print(" | ");
                System.out.print("이메일 : " + email );
                System.out.print(" | ");
                System.out.print("폰 번호  : " + hp );
                System.out.print(" | ");
                System.out.print("가입 날짜 : " + registdate );
                System.out.print(" | ");
                System.out.print("포인트 : " + point );
                System.out.println(" | ");
            }
        } catch (Exception e) {
            System.out.println("에러");
        }
    }

    public static void main(String[] args) {

//        join();

//        selectAll();
        selectONE();

    }
}
