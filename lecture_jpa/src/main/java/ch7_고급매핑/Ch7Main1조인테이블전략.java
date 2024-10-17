package ch7_고급매핑;

import entity.item.Album;
import entity.item.Book;
import entity.item.Item;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch7Main1조인테이블전략 {
    public static void main(String[] args) {
        //상속 매핑 예시 하고 +  jpa_web도 건들고 한번에 커밋하면 됨.
        // 다대다, 일대다 등은 별로 안 중요하고 대부분 다대일이기때문에 따로 실습코드 안했지만
        // 여기서는 조인테이블전략, 단일테이블전략 둘다 해야함  (구현클래스마다는 X)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Album album=new Album(); //album만의 필드 사용=> 다형성X
            album.setArtist("뉴진스");
            album.setName("How Sweet");
            album.setPrice(10000);
            Book book=new Book();
            book.setAuthor("한강");
            book.setIsbn("무언가고유값있겠지");
            book.setPrice(10000);
            book.setName("소년이 온다");
            em.persist(album);
            em.persist(book);




            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
