package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getMovieCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieDTO> mDTOList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        TypedQuery<Movie> tq = em.createNamedQuery("Movie.getAllMovies", Movie.class);
        List<Movie> movies = tq.getResultList();
        movies.stream().forEach(p -> {
            mDTOList.add(new MovieDTO(p));
        });
        return mDTOList;
    }

    public MovieDTO getMovieByReleaseYear(Long id) {
        EntityManager em = getEntityManager();
        try {
            Movie movie = em.find(Movie.class, id);
            MovieDTO m1 = new MovieDTO(movie);
            return m1;
        } finally {
            em.close();
        }
    }

    public MovieDTO getMoviesByReleaseYear(int year) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE m.releaseYear = :YEAR", Movie.class);
            query.setParameter("YEAR", year);
            query.setMaxResults(1);
            Movie movie = (Movie) query.getSingleResult();
            MovieDTO movieDTO = new MovieDTO(movie);
            return movieDTO;
        } finally {
            em.close();
        }
    }

}
