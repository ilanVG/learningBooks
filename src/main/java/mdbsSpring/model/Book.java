package mdbsSpring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document("Book")
public class Book
{
    @Id
    private String isbn;

    private String title;
    private String author;
    private HashMap<String, String> questions;

    public Book()
    {

    }

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.questions = new HashMap<String, String>();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public HashMap<String, String> getQuestions() {
        return questions;
    }

    public void setQuestions(String question, String answer) {
        this.questions.put(question, answer);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", questions=" + questions +
                '}';
    }
}
