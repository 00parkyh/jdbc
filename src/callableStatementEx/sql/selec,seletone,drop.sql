## SP_MEMBER_LIST() 프로시저를 생성   :  전체 회원들의 정보를 출력하는 기능입니다.
## MemberList 클래스에서 callableStatement 방식으로 회원들의 리스트를 출력하는 기능 구현하세요

-- 회원 전체 리스트
delimiter $$
create procedure SP_MEMBER_LIST()
begin
    set @strsql = 'select * from tb_member' ;
    prepare s1 from @strsql;
    execute s1;
    deallocate prepare s1;

end $$
delimiter ;

-- m_userid 로 조회
drop procedure SP_MEMBER_LIST_ONE;
delimiter $$
create procedure SP_MEMBER_LIST_ONE(in uid varchar(20))
begin
    set @uid = uid;
    set @strsql = 'select * from tb_member where m_userid = ? ' ;
    prepare s1 from @strsql;
    execute s1 using @uid;
    deallocate prepare s1;

end $$
delimiter ;
select * from tb_member;
call SP_MEMBER_LIST_ONE('pink pink');

-- 회원 수정 ( 비밀번호)를 수정할지  이메일을 수정할지  연락처를 수정할지를 선택해서 다중분기로 처리하기
delimiter $$
create procedure SP_MEMBER_UPDATE(in uid varchar(20))
begin
    set @uid = uid;
    set @strsql = 'select * from tb_member where m_userid = ? ' ;
    prepare s1 from @strsql;
    execute s1 using @uid;
    deallocate prepare s1;

end $$
delimiter ;

-- 회원 삭제(탈퇴하기)
delimiter $$
create procedure SP_MEMBER_DROP(in uid varchar(20))
begin
    set @uid = uid;
    set @strsql = 'delete from tb_member where m_userid = ? ' ;
    prepare s1 from @strsql;
    execute s1 using @uid;
    deallocate prepare s1;

end $$
delimiter ;