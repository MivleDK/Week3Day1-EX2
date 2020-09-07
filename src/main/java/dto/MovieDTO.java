package dto;

import entities.Movie;

public class MovieDTO {

    Long id;
    String movieTitle;
    int releaseYear;

    public MovieDTO() {
    }

    public MovieDTO(Movie m) {
        this.id = m.getId();
        this.movieTitle = m.getMovieTitle();
        this.releaseYear = m.getReleaseYear();
    }
}
