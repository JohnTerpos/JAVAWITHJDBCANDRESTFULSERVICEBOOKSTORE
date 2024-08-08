package gr.uniwa.book_shop.model;

// Import the required packages
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book") //Use annotation to make object to XML element
public class Book implements Serializable{
    
    // Private Members of class Book.
    private int book_code,pages,number_available,year_version;
    private float price;
    private String title,firstname_writer,lastname_writer,publisher;
    
    // Getters and Setters.
    
    public int getBook_code() {
        return book_code;
    }

    public void setBook_code(int book_code) {
        this.book_code = book_code;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getNumber_available() {
        return number_available;
    }

    public void setNumber_available(int number_available) {
        this.number_available = number_available;
    }

    public int getYear_version() {
        return year_version;
    }

    public void setYear_version(int year_version) {
        this.year_version = year_version;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname_writer() {
        return firstname_writer;
    }

    public void setFirstname_writer(String firstname_writer) {
        this.firstname_writer = firstname_writer;
    }

    public String getLastname_writer() {
        return lastname_writer;
    }

    public void setLastname_writer(String lastname_writer) {
        this.lastname_writer = lastname_writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
}
