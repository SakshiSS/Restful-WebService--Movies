package org.Resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import org.Business.TheaterService;
import org.Model.MovieTheaterMapping;
import org.Model.Theater;

@Path("/theater")
public class TheaterResource {

	TheaterService theaterService = new TheaterService();

	@GET
	//@Path("/theaters")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTheaters(){
		String theaters = null;
		ArrayList<Theater> theaterList = new ArrayList<Theater>();
		try{
			theaterList = theaterService.getTheaters();
			Gson gson = new Gson();
			theaters = gson.toJson(theaterList);
		}catch (Exception e){
			e.printStackTrace();
		}
		return theaters;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{theaterName}")
	public String getTheater(@PathParam("theaterName") String theaterName){
		String theaters = null;
		ArrayList<MovieTheaterMapping> theaterList = new ArrayList<MovieTheaterMapping>();
		try{
			theaterList = theaterService.getTheater(theaterName);
			Gson gson = new Gson();
			theaters = gson.toJson(theaterList);
		}catch (Exception e){
			e.printStackTrace();
		}
		return theaters;
	}
	
}
