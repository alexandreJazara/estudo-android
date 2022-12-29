package br.com.jasa.netiflixremaker.model;

import java.util.List;

/*esta parte é responsavável pela lista de categorias dos tipos de filmes e suas coleções de capas*/
public class Category {
    private String name;
    private List<Movie> movies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
