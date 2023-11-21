import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class Homework {
    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/books.xml"));
        Element rootElement = document.getRootElement();
        List<Element> books = rootElement.elements("book");
        for (Element book : books) {
            Element name = book.element("name");
            String bookName = name.getText();
            Element author = book.element("author");
            String bookAuthor = author.getText();
            Element price = book.element("price");
            double bookPrice = Double.parseDouble(price.getText());
//            Book b = new Book(bookName, bookAuthor, bookPrice);
            Book b = new Book();
            b.setName(bookName);
            b.setAuthor(bookAuthor);
            b.setPrice(bookPrice);
            System.out.println(b);
        }
    }
}

class Book {
    private String name;
    private String author;
    private double price;

    public Book() {
    }

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}