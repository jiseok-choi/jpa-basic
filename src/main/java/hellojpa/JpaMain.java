package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // persistence.xml 파일의 속성
        // persistence-unit name 값을 바인딩 한다
        // 팩토리를 생성하는 순간 데이터베이스와 연결이 되었다는 의미이다.
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");

        // EntityManagerFactory 에서 entityManger 꺼내기
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {

            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 영속
            System.out.println("=== BEFORE ===");
            entityManager.persist(member);
            System.out.println("=== AFTER ===");

            // 트랜잭션이 커밋 될때 영속성 컨텍스트에서 DB 에 쿼리가 전달된다.
           tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();

            emf.close();
        }




    }
}
