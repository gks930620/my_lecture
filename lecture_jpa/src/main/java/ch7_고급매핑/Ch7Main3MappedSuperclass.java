package ch7_고급매핑;

import entity.item.Album;
import entity.item.Book;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class Ch7Main3MappedSuperclass {
    public static void main(String[] args) {
        //단일테이블전략. 큰 의미없다.
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

            album.setCreateDateTime(LocalDateTime.now());
            book.setCreateDateTime(LocalDateTime.now());
            //지금은 직접 데이터를 넣지만 spring Data jpa에서는 AUdition기능으로 자동으로 데이터 넣기가능
            

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
