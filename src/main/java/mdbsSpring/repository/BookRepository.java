package mdbsSpring.repository;

import mdbsSpring.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository  extends MongoRepository<Book, String>
{
    @Query(value="{title:'?0'}")
    Book findItemByName(String title);

    @Query(value="{title:'?0'}", delete = true)
    Book deleteBooksByTitle(String title);

    @Query(value="{title:'?0'}", fields="{'title' : 1, 'author' : 1, 'questions' : 1}")
    List<Book> findAll(String title);
    @Query(value="{title:'?0'}")
    Long deletePersonByLastname(String lastname);
    public long count();
    
}
