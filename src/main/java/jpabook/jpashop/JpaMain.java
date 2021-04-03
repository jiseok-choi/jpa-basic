package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");

        // EntityManagerFactory 에서 entityManger 꺼내기
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            // SELECT * FROM ORDER WHERE id = 1;
            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();

            // SELECT * FROM MEMBER WHERE memberid = 1;
            Member member = em.find(Member.class, memberId);

            Member findMember = order.getMember();


        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();

            emf.close();
        }
    }
}
