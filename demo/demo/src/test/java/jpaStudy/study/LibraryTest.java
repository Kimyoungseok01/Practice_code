package jpaStudy.study;

import java.util.Optional;
import jpaStudy.study.Entity.Book;
import jpaStudy.study.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

@RequiredArgsConstructor
@SpringBootTest
public class LibraryTest {
  @Autowired
  private BookRepository bookRepository;

  @Test
  @DisplayName("delete")
  void deleteTest(){
    Optional<Book> book = bookRepository.findById(10L);

    book.ifPresent(selectBook ->{
      bookRepository.deleteById(selectBook.getId());
    });
    //Assertions.assertThat()
  }
}
