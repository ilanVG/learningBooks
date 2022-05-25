package mdbsSpring;

import mdbsSpring.model.Book;
import mdbsSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner {

    @Autowired
    BookRepository bookRepo;

    List<Book> bookList;

    public static void main(String[] args) {
        SpringApplication.run(MdbSpringBootApplication.class, args);
    }

    public void run(String... args)
    {

        bookRepo.deleteAll();
        System.out.println("-----ADD BOOK------");
        Book test = new Book("978-0-525", "AP CSA Review", "Princeton Review");
        bookRepo.save(test);
        Book test2 = new Book("9780786884063", "The Man Who Loved Only Numbers", "Paul Hoffman");
        bookRepo.save(test2);
        System.out.println("Added");
        updateQuestion("AP CSA Review", "What is a Java class?",
            "The template for an object");
        updateQuestion("AP CSA Review", "A Linked List is better than an ArrrayList for:",
                "Linked List is better for manipulation.");
        updateQuestion("AP CSA Review", "An ArrayList is better than a LinkedList for:",
                "Arraylist is better for storing and accessing data");
        System.out.println("Changed");

        bookList = bookRepo.findAll();
        for(Book b : bookList)
        {
            System.out.println(b);
        }
        //model.addAttribute("books",bookList);
    }

    public void updateQuestion(String book, String question, String answer)
    {
        List<Book> list = bookRepo.findAll(book);
        list.forEach(b -> {
            b.setQuestions(question, answer);
        });

        List<Book> booksUpdated = bookRepo.saveAll(list);
        if(booksUpdated != null)
        {
            System.out.println("Successful update");
        }
    }


}
