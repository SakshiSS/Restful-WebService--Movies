package org.Resource;

import com.google.gson.Gson;
import org.Model.Movie;
import org.Business.MovieService;
import org.Model.MovieTheaterMapping;
import org.Model.Theater;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marne on 2/4/2017.
 */
@Path("/movie")
public class MovieResource {

    MovieService movieService = new MovieService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies(@QueryParam("genre") String genre)     {

        Gson gson = new Gson();
        List<Movie> moviesList ;
        if(genre!=null){
           moviesList = new ArrayList<Movie>(movieService.getMoviesWithGenre(genre));
            String jsonMovies = gson.toJson(moviesList);
            return Response.status(Response.Status.OK).entity(jsonMovies).build();
        }

        moviesList = new ArrayList<Movie>(movieService.getMovies());
        String jsonAllMovies = gson.toJson(moviesList);
        return Response.status(Response.Status.OK).entity(jsonAllMovies).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{movieName}")
    public Response getMovie(@PathParam("movieName") String movieName){
        Gson gson = new Gson();
        Map<String,ArrayList<MovieTheaterMapping>> movieDetails = movieService.getMovie(movieName);
        String jsonMovieDetails = gson.toJson(movieDetails);

        return Response.status(Response.Status.OK).entity(jsonMovieDetails).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{movieName}/theater")
    public Response getTheatersWithMovie(@PathParam("movieName") String movieName){
        List<MovieTheaterMapping> movieTheaters = movieService.getTheatersGivenMovie(movieName);
        Gson gson = new Gson();
        String jsonTheaters = gson.toJson(movieTheaters);
        return Response.status(Response.Status.OK).entity(jsonTheaters).build();
    }


}