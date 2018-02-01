package org.Resource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by marne on 2/6/2017.
 */
public class MoviePickApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public MoviePickApplication(){
        singletons.add(new MovieResource());
        singletons.add(new TheaterResource());
        singletons.add(new RatingResource());

    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }



}
