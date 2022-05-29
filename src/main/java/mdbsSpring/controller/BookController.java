package mdbsSpring.controller;

import mdbsSpring.model.Book;
import mdbsSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController
{
    @Autowired
    BookRepository bookRepo;

    List<Book> bookList = new ArrayList<Book>();

    @GetMapping("/")
    public String showHomePage(Model model)
    {
        Book book = new Book();
        model.addAttribute("modelBookKey", book);
        System.out.println("Started from controller");
        bookList = bookRepo.findAll();
        model.addAttribute("books",bookList);
        return "index";
    }

    @RequestMapping(value = "/removeBooks", method = RequestMethod.POST)
    public String deleteBooks(@ModelAttribute("modelBookKey") Book theBook, Model model)
    {
        System.out.println("Deleting");
        String theTitle = theBook.getTitle();
        System.out.println(theTitle);
        bookRepo.deleteBooksByTitle(theTitle);
        bookList = bookRepo.findAll();
        System.out.println(bookList);
        model.addAttribute("books",bookList);
        return "index";
    }

    //after clicking 'Add Book' sends to book registration page with empty book object
    @GetMapping("/register")
    public String showPage(Model model)
    {
        Book book = new Book();
        model.addAttribute("modelBookKey", book);

        return "book_registration";
    }

    //Retrieves the Book from the model and the inputs from book_registration
    //Saves this object to repository and sends to success page
    @PostMapping("/register")
    public String submitForm(@ModelAttribute("modelBookKey") Book theBook) {
        Book addedBook = new Book(theBook.getIsbn(), theBook.getTitle(), theBook.getAuthor());
        bookRepo.save(addedBook);
        System.out.println(addedBook);
        return "register_success";
    }

    @RequestMapping(value = "/toStudy", method = RequestMethod.GET)
    public String studyQuestionsReload(@ModelAttribute("modelBookKey") Book theBook, Model model) {
        System.out.println("Gt: ");
        System.out.println(model.getAttribute("modelBookKey"));
        System.out.println(theBook);
        if(theBook.getTitle() == null)
        {
            bookList = bookRepo.findAll();
            model.addAttribute("books",bookList);
            return "index";
        }
        else if(!theBook.getTitle().equals(""))
        {
            System.out.println("Given book: " + theBook);
            Book selectedBook = bookRepo.findItemByName(theBook.getTitle());
            System.out.println(selectedBook);
            model.addAttribute("selected", selectedBook);
            System.out.println("Not null");
            System.out.println(model.getAttribute("selected"));
            return "study_questions";
        }
        return "index.html";
    }

    //directs from drop down to form for adding or getting questions
    @RequestMapping(value = "/toStudy", method = RequestMethod.POST)
    public String studyQuestions(@ModelAttribute("modelBookKey") Book theBook, Model model) {
        if(!theBook.getTitle().equals(""))
        {
            System.out.println("Given book: " + theBook);
            Book selectedBook = bookRepo.findItemByName(theBook.getTitle());
            System.out.println(selectedBook);
            model.addAttribute("selected", selectedBook);
            System.out.println("Not null");
            return "study_questions";
        }
        else {
            bookList = bookRepo.findAll();
            model.addAttribute("books",bookList);
            return "index";
        }
    }

    @PostMapping("/addQuestions")
    public String registerQuestions(
            @RequestParam(value="question", required = false) String q,
            @RequestParam(value = "answer", required = false) String a,
            @ModelAttribute("modelBookKey") Book theBook,
            Model model) {

        System.out.println(q);
        System.out.println(a);
        Book selectedBook = bookRepo.findItemByName(theBook.getTitle());
        System.out.println(selectedBook);
        selectedBook.setQuestions(q,a);
        bookRepo.save(selectedBook);
        System.out.println(selectedBook);
        bookList = bookRepo.findAll();
        model.addAttribute("books",bookList);
        return "index";
    }


    public void showAllGroceryItems() {
        bookList = bookRepo.findAll();

        bookList.forEach(item -> getBookDetails(item));
    }

    public void getBookDetails(Book b)
    {
        System.out.println(b);
    }





}
