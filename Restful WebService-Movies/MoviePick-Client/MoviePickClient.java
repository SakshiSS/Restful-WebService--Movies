import com.google.gson.*;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import javax.ws.rs.core.MediaType;
import java.io.*;



public class MoviePickClient {

    public static BufferedReader bufferedReader;

    //Get the list of all movies
    public void getMovies() throws IOException, Exception{

        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/movie");
        clientRequest.accept(MediaType.APPLICATION_JSON);

        ClientResponse<String> clientResponse = clientRequest.get(String.class);

        if(clientResponse.getStatus() != 200){
            throw new RuntimeException("Failed : HTTP Error code "+clientResponse.getStatus());
        }
        else {
            System.out.println("OK. Retrieved movies list. The movies are : ");
            String movies =  clientResponse.getEntity(String.class) ;
            System.out.println(prettyPrintJSON(movies));
        }

    }

    //Get the movies given the movie name
    public void getMovie(String movieName) throws Exception{
        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/movie/"+movieName);
        clientRequest.accept(MediaType.APPLICATION_JSON);

        ClientResponse<String> clientResponse = clientRequest.get(String.class);

        if(clientResponse.getStatus() != 200){
            throw new RuntimeException("Failed : HTTP Error code "+clientResponse.getStatus());
        }
        else {
            System.out.println("OK. Retrieved movie details. The movie details are : ");
            String movies =  clientResponse.getEntity(String.class) ;
            //System.out.println(movies);
            if(!movies.equals("{}"))
            System.out.println(prettyPrintJSON(movies));
            else{
                System.out.println("Not available. Please enter from above options");
            }
        }
    }

    //Get the list of theaters where the given movie is being played
    public void getTheatersGivenMovie(String movieName) throws Exception{
        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/movie/"+movieName+"/theater");
        clientRequest.accept(MediaType.APPLICATION_JSON);

        ClientResponse<String> clientResponse = clientRequest.get(String.class);

        if(clientResponse.getStatus() != 200){
            throw new RuntimeException("Failed : HTTP Error code "+clientResponse.getStatus());
        }
        else {
            System.out.println("Ok. Retrieved theaters list. The theaters are : ");
            String movies =  clientResponse.getEntity(String.class) ;
            //System.out.println(moviesList);
            if(!movies.equals("{}"))
                System.out.println(prettyPrintJSON(movies));
            else{
                System.out.println("Not available. Please enter from above options");
            }
        }
    }

    //Give the genre to get the list of movies with that genre
    public void getMoviesGivenGenre(String genre) throws Exception{
        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/movie?genre="+genre);
        clientRequest.accept(MediaType.APPLICATION_JSON);

        ClientResponse<String> clientResponse = clientRequest.get(String.class);

        if(clientResponse.getStatus() != 200){
            throw new RuntimeException("Failed : HTTP Error code "+clientResponse.getStatus());
        }
        else {
            System.out.println("Ok. Retrieved the movies list. The movie details are : ");
            String movies =  clientResponse.getEntity(String.class) ;
            //System.out.println(moviesList);
            if(!movies.equals("{}"))
                System.out.println(prettyPrintJSON(movies));
            else{
                System.out.println("Not available. Please enter from above options");
            }
        }

    }

    //Get the list of all theaters
    public void getTheaters() throws IOException, Exception{

        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/theater");
        clientRequest.accept(MediaType.APPLICATION_JSON);

        ClientResponse<String> clientResponse = clientRequest.get(String.class);



        if( clientResponse.getStatus() != 200 ) {
            throw new RuntimeException( "GET Request failed: HTTP code: " + clientResponse.getStatus() );
        }
        else {


            String theaters = clientResponse.getEntity().toString();
            if(!theaters.equals("{}")){
                System.out.println( prettyPrintJSON( theaters ) );
            }

            else{
                System.out.println("No results are there under theaters");
            }

        }

    }

    //Get the details of a given theater
    public void getTheatersWithTheaterName(String theaterName) throws IOException,Exception{

        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/theater/"+theaterName);
        clientRequest.accept(MediaType.APPLICATION_JSON);

        ClientResponse<String> clientResponse = clientRequest.get(String.class);


        //clientResponse = target.request( MediaType.APPLICATION_JSON ).get();
        if( clientResponse.getStatus() != 200 ) {
            throw new RuntimeException( "GET Request failed: HTTP code: " + clientResponse.getStatus() );
        }
        else {
            System.out.println( "Getting theater details...." );

            String theaterDetails = clientResponse.getEntity().toString();
            if(!theaterDetails.equals("{}"))
            System.out.println( prettyPrintJSON( theaterDetails ) );
            else{
                System.out.println("No results with the given theater name");
            }

        }
    }

    //Get the movies list with the given rating
    public void getMoviesWithRating(int rating) throws IOException, Exception {
        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/rating/" + rating);
        clientRequest.accept(MediaType.APPLICATION_JSON);

        ClientResponse<String> clientResponse = clientRequest.get(String.class);

        if (clientResponse.getStatus() != 200) {
            throw new RuntimeException("GET Request failed: HTTP code: " + clientResponse.getStatus());
        } else {
            System.out.println("OK: Retrieved Movie Details with rating :" + rating);

            String moviesList = clientResponse.getEntity(String.class);
            if (!moviesList.equals("{}"))
                System.out.println(prettyPrintJSON(moviesList));
            else {
                System.out.println("No results with the given rating");
            }

        }
    }

    //Get rating of given movie
    public void getRatingGivenMovie(String movieName) throws Exception{

        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/rating/movie/"+movieName);
        clientRequest.accept(MediaType.TEXT_PLAIN);

        ClientResponse<String> clientResponse = clientRequest.get(String.class);

        if (clientResponse.getStatus() != 200) {
            throw new RuntimeException("GET Request failed: HTTP code: " + clientResponse.getStatus());
        }
        else {
            System.out.println( "OK: Retrieved Movie Details" );

            String rating = clientResponse.getEntity(String.class);
            System.out.println("rating::"+rating);
        }//else


    }

    //update Rating of Particular Movie
    public  void updateMovieRating(String movieName,int rating) throws Exception{
        ClientRequest clientRequest = new ClientRequest("http://uml.cs.uga.edu:8080/cs8350_4_movies/rest/rating/"+rating+"/movie/"+movieName);
        clientRequest.accept(MediaType.TEXT_PLAIN);

        ClientResponse<String> clientResponse = clientRequest.put();

        if( clientResponse.getStatus() != 200 ) {
            throw new RuntimeException( "PUT Request failed: HTTP code: " +clientResponse.getStatus() );
        }
        else
            System.out.println( "OK: Updated the rating for the movie "+movieName );
       // clientResponse.close();

    }//update




    public static void main(String[] args) throws RuntimeException, Exception{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        MoviePickClient moviePickClient = new MoviePickClient();

        boolean looping = true;

        while (looping){

            System.out.println("1. Get the list of all movies\n" +
                    "2. Get the details of the given movie\n" +
                    "3. Get the theaters where the given movie is being played\n" +
                    "4. Get the movies as per the given genre\n" +
                    "5. Get the list of theaters\n" +
                    "6. Get the details of the given theater\n" +
                    "7. Get the movies being played at the given theater\n" +
                    "8. Get the movies, given the rating\n" +
                    "9. Get the rating of a given movie\n" +
                    "10.Update the rating of a given movie\n" +
                    "11.Exit");

            System.out.println("Enter your choice");
            int choice = Integer.parseInt(bufferedReader.readLine());


            switch (choice){
                case 1: System.out.println("Getting the movie details.....");

                    moviePickClient.getMovies();
                    System.out.println();

                    break;
                case 2: System.out.println("Enter the movie name for which you want details in :\n" +
                        "A Dogs purpose\n" +
                        "Rings\n" +
                        "Split\n" +
                        "La La Land\n" +
                        "Hidden Figures\n" +
                        "Resident Evil: The Final\n");

                    System.out.println("Movie name ?");
                    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String movieName = bufferedReader.readLine();
                    System.out.println("Getting the details of "+movieName);
                    moviePickClient.getMovie(movieName);
                    System.out.println();
                    break;

                case 3: System.out.println("Get theaters where your favorite movie is available");
                    System.out.println("Enter the movie name for which you want details in :\n" +
                            "A Dogs purpose\n" +
                            "Rings\n" +
                            "Split\n" +
                            "La La Land\n" +
                            "Hidden Figures\n" +
                            "Resident Evil: The Final\n");
                    System.out.println("Movie name ?");
                    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String mvName = bufferedReader.readLine();

                    moviePickClient.getTheatersGivenMovie(mvName);
                    System.out.println();
                    break;
                case 4: System.out.println("Enter the genre you want to check from :");
                    System.out.println("Comedy\nHorror\nFamily\nRomcom\nSuspense\n");
                    System.out.println("Genre ?");
                    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String genre = bufferedReader.readLine();
                    moviePickClient.getMoviesGivenGenre(genre);
                    break;
                case 5: System.out.println("Gettig the theater details");
                    moviePickClient.getTheaters();
                    break;
                case 6: System.out.println("Enter the theater name :");
                    System.out.println("GTC University 16 Ci\nCine\nBeechwood Cinema\nGeorgia Theatre\nAthens Creative Thea");
                    System.out.println("Theater Name?");
                    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String thName = bufferedReader.readLine();

                    moviePickClient.getTheatersWithTheaterName(thName);
                    break;
                case 7: System.out.println("Enter the theater name :");
                    System.out.println("GTC University 16 Ci\nCine\nBeechwood Cinema\nGeorgia Theatre\nAthens Creative Thea");
                    System.out.println("Theater Name?");
                    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String tName = bufferedReader.readLine();

                    moviePickClient.getTheatersWithTheaterName(tName);
                    break;

                case 8 : System.out.println("Enter the Rating to check the movies::");
                    int rating = Integer.parseInt(bufferedReader.readLine());
                    moviePickClient.getMoviesWithRating(rating);
                    break;

                case 9: System.out.println("Enter the movie name to get the rating of that movie");
                    System.out.println("Enter the movie name for which you want details in :\n" +
                            "A Dogs purpose\n" +
                            "Rings\n" +
                            "Split\n" +
                            "La La Land\n" +
                            "Hidden Figures\n" +
                            "Resident Evil: The Final\n");
                    System.out.println("Movie name ?");

                    String movieNameForRating = bufferedReader.readLine();
                    moviePickClient.getRatingGivenMovie(movieNameForRating);
                    System.out.println();
                    break;

                case 10: System.out.println("Enter the details to update the rating for a given movie");
                    System.out.println("Enter the movie name for which you want details in :\n" +
                            "A Dogs purpose\n" +
                            "Rings\n" +
                            "Split\n" +
                            "La La Land\n" +
                            "Hidden Figures\n" +
                            "Resident Evil: The Final\n");
                    System.out.println("Enter the movie Name :");
                    String mName = bufferedReader.readLine();
                    System.out.println("Enter the rating on a scale of 1 to 5");

                    int ratingForMovie = Integer.parseInt(bufferedReader.readLine());
                    moviePickClient.updateMovieRating(mName,ratingForMovie);
                    System.out.println();
                    break;
                case 11: looping = false;
                    break;
            }
        }

    }

    public static String prettyPrintJSON(String input) throws JsonIOException, IOException{

        JsonElement object = new JsonParser().parse(input.toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }
}
