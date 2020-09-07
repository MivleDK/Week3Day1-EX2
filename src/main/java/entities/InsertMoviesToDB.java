package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsertMoviesToDB {

    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Movie m1 = new Movie("The Lord of the Rings - Fellowship of the Ring", 2001);
        Movie m2 = new Movie("The Lord of the Rings - The Two Towers", 2002);

        try {
            em.getTransaction().begin();
            em.persist(m1);
            em.persist(m2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
