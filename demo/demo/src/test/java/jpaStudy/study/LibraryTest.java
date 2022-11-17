package jpaStudy.study;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import jpaStudy.study.Entity.Book;
import jpaStudy.study.Repository.Book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
//@SpringBootTest
public class LibraryTest {

  @Autowired
  private BookRepository bookRepository;

  @Test
  @DisplayName("delete")
  void deleteTest() {
    Optional<Book> book = bookRepository.findById(10L);

    book.ifPresent(selectBook -> {
      bookRepository.deleteById(selectBook.getId());
    });
    //Assertions.assertThat()
  }

  @Test
  @DisplayName("OptionTest")
  void OptionTest(){
    int[] arr = new int[0];
    System.out.println("arr.length = " + arr.length);

    Optional<String> opt = Optional.empty();
//    Optional<String> opt = Optional.of("abc");
    System.out.println("opt = " + opt);
    //System.out.println("opt = " + opt.get());

    /*String str = "";
    try {
      str = opt.get();
    } catch (Exception e) {
      str = "";
    }
    System.out.println("str = " + str);*/

    String str = opt.orElseGet(() -> "EMPTY");
    System.out.println("str = " + str);

    Optional<String> optStr = Optional.of("abcdx");
    Optional<Integer> optInt = optStr.map(d -> d.length());
    System.out.println("optStr = " + optStr);
    System.out.println("optInt = " + optInt);

    int result1 = Optional.of("123")
        .filter(x->x.length() > 0)
        .map(Integer::parseInt).get();

    int result2 = Optional.of("")
        .filter(x->x.length()>0)
        .map(Integer::parseInt).orElse(-1);

    System.out.println("result1 = " + result1);
    System.out.println("result2 = " + result2);
  }

  @Test
  @DisplayName("TTTT")
  void test1233(){
    String encode = "GangwonDog";
    byte[] message = encode.getBytes(StandardCharsets.UTF_8);
    String encoded = Base64.getEncoder().encodeToString(message);
    System.out.println("encoded = " + encoded);

   byte[] decoded = Base64.getDecoder().decode(encoded);
    System.out.println("new String(decoded, StandardCharsets.UTF_8) = " + new String(decoded, StandardCharsets.UTF_8));
    System.out.println("decoded = " + decoded);
  }

}
  