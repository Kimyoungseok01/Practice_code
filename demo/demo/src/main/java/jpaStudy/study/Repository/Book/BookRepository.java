package jpaStudy.study.Repository;

import java.util.List;
import java.util.Optional;
import jpaStudy.study.Entity.Book;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    List<Book> findAllByOrderByIsbnDesc();

    List<Book> findByIsbnContaining(String isbn);

    @Query("select b from Book b where (:isbn is null or b.isbn LIKE %:isbn%) or (:name is null or b.name like %:name%)")
    List<Book> JPQLTest(@Param("isbn") String isbn, @Param("name")String name);


}
