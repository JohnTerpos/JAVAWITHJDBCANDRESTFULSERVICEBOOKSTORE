package gr.uniwa.book_shop.database;

// Import the required packages
import gr.uniwa.book_shop.model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    
    //Connect to database book_shop.
    public static Connection getConnection()
    {
      Connection con = null;
      
      try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_shop","terpos","spider18");
      } catch (ClassNotFoundException | SQLException ex) {           
        }
        return con;
    }
    
    //Show list of available books.
    public List<Book> getAvailableBooks()
    {
      List<Book> booklist=new ArrayList();
      Connection con=BookDao.getConnection();
      
      try{
          PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE number_available>0");
          ResultSet rs=ps.executeQuery(); 
          while(rs.next()){
              Book book=new Book();
              book.setBook_code(rs.getInt(1));
              book.setTitle(rs.getString(2));
              book.setFirstname_writer(rs.getString(3));
              book.setLastname_writer(rs.getString(4));
              book.setPrice(rs.getFloat(5));
              book.setPages(rs.getInt(6));
              book.setYear_version(rs.getInt(7));
              book.setPublisher(rs.getString(8));
              book.setNumber_available(rs.getInt(9));
              booklist.add(book);
          }
          con.close();
      }
      catch (SQLException ex) {}
      
      return booklist;
    }
    
    //Show book with current code.
    public Book getBook(int book_code)
    {
      Book book=new Book();
      Connection con=BookDao.getConnection();
      try{
         PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE book_code=?");
         ps.setInt(1, book_code);
         ResultSet rs=ps.executeQuery();
         
         if(rs.next())
         {
           book.setBook_code(rs.getInt(1));
           book.setTitle(rs.getString(2));
           book.setFirstname_writer(rs.getString(3));
           book.setLastname_writer(rs.getString(4));
           book.setPrice(rs.getFloat(5));
           book.setPages(rs.getInt(6));
           book.setYear_version(rs.getInt(7));
           book.setPublisher(rs.getString(8));
           book.setNumber_available(rs.getInt(9));
         }
         con.close();
      }catch (SQLException ex) {}
      
      return book;
    }
    
    //Update number of available books with current code when ordered.
    public int orderBook(int book_code)
    {
      int status=0;
      Connection con=BookDao.getConnection();
      try{
         PreparedStatement ps = con.prepareStatement("UPDATE books SET number_available=number_available-1 WHERE book_code=?");
         ps.setInt(1, book_code);
         status=ps.executeUpdate();                
         con.close();
      }
      catch(SQLException ex){}
      
      return status;
    }
    
    //Add new book.
    public int addBook(Book book)
    {
       int status = 0;
       Connection con=BookDao.getConnection();
       
       try{
          PreparedStatement ps=con.prepareStatement("INSERT INTO books(book_code, title, firstname_writer, lastname_writer, price, pages, year_version, publisher, number_available) "
                  + "VALUES (?,?,?,?,?,?,?,?,?)");
          ps.setInt(1,book.getBook_code());
          ps.setString(2, book.getTitle());
          ps.setString(3, book.getFirstname_writer());
          ps.setString(4, book.getLastname_writer());
          ps.setFloat(5, book.getPrice());
          ps.setInt(6, book.getPages());
          ps.setInt(7, book.getYear_version());
          ps.setString(8, book.getPublisher());
          ps.setInt(9, book.getNumber_available());
          status=ps.executeUpdate();                
          con.close();
       } catch (SQLException ex) {}
       
       return status;
    }
    
    // Update book with current code.
    public int updateBook(Book book)
    {
      int status = 0;
      Connection con=BookDao.getConnection();
      try
      {
        PreparedStatement ps=con.prepareStatement("UPDATE books SET title=?,firstname_writer=?,lastname_writer=?,"
                + "price=?,pages=?,year_version=?,publisher=?,number_available=? WHERE book_code=?");
        
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getFirstname_writer());
        ps.setString(3, book.getLastname_writer());
        ps.setFloat(4, book.getPrice());
        ps.setInt(5, book.getPages());
        ps.setInt(6, book.getYear_version());
        ps.setString(7, book.getPublisher());
        ps.setInt(8, book.getNumber_available());
        ps.setInt(9,book.getBook_code());
        status=ps.executeUpdate();                
        con.close();
      } catch (SQLException ex) {}
      
      return status;
    }
    
    // Delete book with current code.
    public int deleteBook(int book_code)
    {
      int status=0;
      Connection con=BookDao.getConnection();
      
      try
      {
        PreparedStatement ps=con.prepareStatement("DELETE FROM books WHERE book_code=?");
        ps.setInt(1, book_code);
        status=ps.executeUpdate();                
        con.close(); 
      } catch (SQLException ex) {}
      
      return status;
    }
    
}
