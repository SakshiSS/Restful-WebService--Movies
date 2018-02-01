package org.Resource;
import org.Business.RatingService;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NoPermissionException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import org.Model.Movie;

@Path("/rating")
public class RatingResource {
    HashMap<Integer,List<Movie>> db= new HashMap<Integer,List<Movie>>();

    RatingService ratingService = new RatingService();



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchMovieByRating(@PathParam("id") int rating) throws ClassNotFoundException, SQLException{

        List<Movie> moviesList = ratingService.findMovieByRating(rating);
        Gson g=new Gson();
        String jsonList=g.toJson(moviesList);
        return Response.status(Response.Status.OK).entity(jsonList).build();

    }//Search

    @GET
    @Path("/movie/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public int searchRatingByMovie(@PathParam("id") String movieName) throws ClassNotFoundException, SQLException{

        int rating= ratingService.findRatingByMovie(movieName);
        return rating;

    }//Search

    @PUT
    @Path("/{rating}/movie/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateMovie(@PathParam("id") String movieName,@PathParam("rating")int rating) throws ClassNotFoundException, SQLException{
        //Movie m=new Movie();
        Movie movie = ratingService.updateMovie(movieName,rating);
        if(movie != null)
        return Response.ok().build();
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }//update


}
