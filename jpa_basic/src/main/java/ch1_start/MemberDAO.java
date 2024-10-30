package ch1_start;

import java.lang.reflect.Member;

public class MemberDAO {

    public Member find(String memberId){
        String sql= "SELECT member_id,name FROM member m WHERE member_id=?";
        //ResultSet rs= pstmt.execut(sql);
        //entity.Member member =new entity.Member();
        // member.setMemId=rs.getString("member_id");
        //...
        return null;
    }

    public void save(Member member){
        //똑같이 insert 쿼리 사용
        // pstmt.execute();
    }

    //나머지 조회,수정,삭제 등도 마찬가지로 쿼리사용 후 execute

    public Member findWithTeam(String memberId){
        String sql= "SELECT member_id,m.name, T.team_id,T.name FROM member m WHERE member_id=?" +
                "JOIN team t on m.team_id=t.team_id" +
                "";
        //ResultSet rs= pstmt.execut(sql);
        //entity.Member member =new entity.Member();
        // member.setMemId=rs.getString("member_id");
        // Team team= new Team();
        // team.setTeamId= rs.getString();
        // member.setTeam(team);
        //...

        return null;
    }


}
