package org.netbeans.rest.application.config;

//Import the required packages.
import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")  //Set path of final BookService.
public class ApplicationConfig extends Application{
    
    @Override
    public Set<Class<?>> getClasses(){
       Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(gr.uniwa.book_shop.service.BookServiceImpl.class);
    }
}
