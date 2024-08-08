package gr.uniwa.book_shop.service;

// Import the required packages.
import gr.uniwa.book_shop.database.BookDao;
import gr.uniwa.book_shop.model.Book;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/BookService")   //Use annotation to set path of BookService.
public class BookServiceImpl implements BookService{
   
   BookDao bookDao=new BookDao();
   
   @GET      // Create request GET in order to get the list of available books.
   @Path("/books")   //Use annotation to set endpoint to path of BookService.
   @Produces(MediaType.APPLICATION_XML) // Show the list of available books with type XML.
   @Override
   public List<Book> getAvailableBooks(){
       return bookDao.getAvailableBooks();
   }
   
   @GET      // Create request GET in order to get the list of available books.
   @Path("/booksjson")   //Use annotation to set endpoint to path of BookService.
   @Produces(MediaType.APPLICATION_JSON) // Show the list of available books with type JSON.
   @Override
   public List<Book> getAvailableBooksJson(){
       return bookDao.getAvailableBooks();
   }
   
   @GET      // Create request GET in order to get the book with current code.
   @Path("/books/{book_code}")   //Use annotation to set endpoint to path of BookService.We give book_code in path.
   @Produces(MediaType.APPLICATION_XML) // Show the book with current code with type XML.
   @Override
   public Book getBook(@PathParam("book_code") int book_code)
   {
      return bookDao.getBook(book_code);
   }
   
   @POST    //Create method POST to order a book by inserting its code.
   @Path("/orderbook")   //Use annotation to set endpoint to path of BookService.
   @Produces(MediaType.TEXT_HTML) // Show the result with type HTML.
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)  // Insert book_code in form. 
   @Override
   public String orderBook(@FormParam("book_code") int book_code, @Context HttpServletResponse servletResponse)
   {
     
     Book book=bookDao.getBook(book_code);     // Choose a book with current code.
     
     if(book.getBook_code()==book_code)    // Book with current code found.
     {
        
        if(book.getNumber_available()>0)    // Available books with current code.
        {
          int result=bookDao.orderBook(book_code);     // Update number of available books with current code.
          
          if(result==1)
              return ORDER_SUCCESS;     // Response in case of available books with current code.
        }
        
        else
             return ORDER_NOT_AVAILABLE;    // Response in case of unavailable books with current code.
     }
     
     
     return ORDER_ERROR; //Response in case of not finding books with current code.
   }
   
   @POST //Create method POST to add a new book.
   @Path("/books") //Use annotation to set endpoint to path of BookService.
   @Produces(MediaType.TEXT_HTML) // Show the result with type HTML.
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // Insert elements of book in form.
   @Override
   public String addBook(@FormParam("book_code") int book_code,@FormParam("title") String title,@FormParam("firstname_writer") String firstname_writer,@FormParam("lastname_writer") String lastname_writer,@FormParam("price") float price,@FormParam("pages") int pages,@FormParam("year_version") int year_version,@FormParam("publisher") String publisher,@FormParam("number_available") int number_available,@Context HttpServletResponse servletResponse){
     
       Book book=new Book();
       
       book.setBook_code(book_code);
       book.setTitle(title);
       book.setFirstname_writer(firstname_writer);
       book.setLastname_writer(lastname_writer);
       book.setPrice(price);
       book.setPages(pages);
       book.setYear_version(year_version);
       book.setPublisher(publisher);
       book.setNumber_available(number_available);
       
       int result=bookDao.addBook(book);
       if(result==1)
       {
         return INSERT_SUCCESS;  //Response in case of successful insertion.
       }
       
       return INSERT_ERROR;  //Response in case of finding error in insertion.
               
   }
   
   @PUT //Create method PUT to update a book with current code.
   @Path("/books") //Use annotation to set endpoint to path of BookService.
   @Produces(MediaType.TEXT_HTML) // Show the result with type HTML.
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // Update elements of book with current code in form.
   @Override
   public String updateBook(@FormParam("book_code") int book_code,@FormParam("title") String title,@FormParam("firstname_writer") String firstname_writer,@FormParam("lastname_writer") String lastname_writer,@FormParam("price") float price,@FormParam("pages") int pages,@FormParam("year_version") int year_version,@FormParam("publisher") String publisher,@FormParam("number_available") int number_available,@Context HttpServletResponse servletResponse){
      
       Book book=new Book();
       
       book.setBook_code(book_code);
       book.setTitle(title);
       book.setFirstname_writer(firstname_writer);
       book.setLastname_writer(lastname_writer);
       book.setPrice(price);
       book.setPages(pages);
       book.setYear_version(year_version);
       book.setPublisher(publisher);
       book.setNumber_available(number_available);
       
       int result=bookDao.updateBook(book);
       if(result==1)
       {
         return UPDATE_SUCCESS; //Response in case of successful update.
       }
       
       return UPDATE_ERROR; //Response in case of finding error in update.
   }
   
   @DELETE //Create method DELETE to delete a book with current code.
   @Path("/books/{book_code}") //Use annotation to set endpoint to path of BookService.We give book_code in path.
   @Produces(MediaType.TEXT_HTML) // Show the result with type HTML.
   @Override
   public String deleteBook(@PathParam("book_code") int book_code)
   {
     
     int result=bookDao.deleteBook(book_code);
     
     if(result==1)
         return DELETE_SUCCESS; //Response in case of successful deletion.
     
     return DELETE_ERROR; //Response in case of finding error in deletion.
     
   }
   
}
