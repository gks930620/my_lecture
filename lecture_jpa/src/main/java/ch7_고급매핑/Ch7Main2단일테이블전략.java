package ch7_고급매핑;

import entity.item.Album;
import entity.item.Book;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch7Main2단일테이블전략 {
    public static void main(String[] args) {
        //단일테이블전략. entity만 변경하면 됨.
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
