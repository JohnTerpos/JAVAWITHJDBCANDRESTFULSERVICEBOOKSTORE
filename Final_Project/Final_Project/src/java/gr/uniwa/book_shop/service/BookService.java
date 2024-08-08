package gr.uniwa.book_shop.service;

//Import the required packages.
import gr.uniwa.book_shop.model.Book;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface BookService {
    
    //Results when you insert a book.
    static final String INSERT_SUCCESS="<h3>Insert completed</h3>";
    static final String INSERT_ERROR="<h3>Insert Error</h3>";
    
    //Results when you order a book.
    static final String ORDER_SUCCESS="<h3>Successful Order</h3>";
    static final String ORDER_NOT_AVAILABLE="<h3>Out of Stock</h3>";
    static final String ORDER_ERROR="<h3>Order Error</h3>";
    
    //Results when you update a book.
    static final String UPDATE_SUCCESS="<h3>Update Completed</h3>";
    static final String UPDATE_ERROR="<h3>Update error</h3>";
    
    //Results when you delete a book.
    static final String DELETE_SUCCESS="<h3>Delete completed</h3>";
    static final String DELETE_ERROR="<h3>Delete error</h3>";
    
    //Methods of interface
     List<Book> getAvailableBooks();
     List<Book> getAvailableBooksJson();
     Book getBook(int book_code);
     String orderBook(int book_code,HttpServletResponse servletResponse);
     String addBook(int book_code,String title,String firstname_writer,String lastname_writer,float price,int pages,int year_version,String publisher,int number_available,HttpServletResponse servletResponse);
     String updateBook(int book_code,String title,String firstname_writer,String lastname_writer,float price,int pages,int year_version,String publisher,int number_available,HttpServletResponse servletResponse);
     String deleteBook(int book_code);
     
}
