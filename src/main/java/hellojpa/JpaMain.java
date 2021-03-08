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
            Member member = new Member();

//            member.setId(2L);
//            member.setName("HelloJPA");

//            Member findMember = entityManager.find(Member.class, 2L);
//
//            System.out.println("id: " + findMember.getId());
//            System.out.println("name: " + findMember.getName());

//            entityManager.persist(member);

            List<Member> result = entityManager.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member m : result) {
                System.out.println("member.name = " + m.getName());
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();

            emf.close();
        }




    }
}
