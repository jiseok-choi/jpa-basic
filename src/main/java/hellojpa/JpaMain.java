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
            // find를 통해서 데이터가 1차 캐시에 등록되면 영속 상태가 된다.
            Member member = entityManager.find(Member.class, 150L);

            // 이후 데이터를 변경하면 컨텍스트가 변경을 감지하여 (더티체킹)
            member.setName("ZZZZ");

            // 영속성 컨텍스트에서 떼어낸다!!
            // jpa에서 관리를 하지 않는다!
            entityManager.detach(member);

            System.out.println("====================");

            // 원래라면 이 시점에 업데이트 쿼리를 날려준다.
            // 하지만 detach 를 수행한 이후에는 쿼리가 수행되지 않는다!
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();

            emf.close();
        }




    }
}
