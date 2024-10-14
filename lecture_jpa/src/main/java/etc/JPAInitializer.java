package etc;

import entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

/**
 * 처음 JPA 실행할 때 DB넣는용. persisten.xml은 계속 create로 유지하고 처음에 필요한 기본적인 데이터 넣는 용도
 * 수업하면서 조금씩 변경해나가야함.   외우지말고 같이 만들어야 함
 */
public class JPAInitializer {

    //트랜잭션을 여기서 만드는 이유는 실습 코드들이 처음 데이터 넣는 것과는 상관없게 하기위해서 (데이터넣고 트랜잭션 종료)
    public static void firstInsertSetting(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        System.out.println("JPA Initailize 시작");
        System.out.println("-----------------------------------------------------------------------------");
        try{
            tx.begin();
            Member member=new Member("mem1","창희");
            em.persist(member);   //em에  등록   DB에서 조회가능.

            em.persist(member);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("-----------------------------------------------------------------------------");
            em.close();
        }
    }


}
