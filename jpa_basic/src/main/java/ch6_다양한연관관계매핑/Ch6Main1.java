package ch6_다양한연관관계매핑;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch6Main1 {
    public static void main(String[] args) {
        //따로 할게 있나... 다대일 기준으로 하는게 좋아서 실습코드는 필요없는거 같은데..
        // 실습문제에 category등 추가하고 끝내자.
        // 실습문제도 다양한것보다는 다대일을 잘 사용하는 정도로만..

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            //딱히 예시 코드 할 거는 없는 거 같음. 그냥 설명만 대충하자.

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
