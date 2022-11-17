package jpaStudy.study.Repository.Book;

import java.util.List;
import jpaStudy.study.Entity.Book;
import jpaStudy.study.Entity.PageRequest;
import org.springframework.data.domain.Page;

public interface BookCustomRepository {
  List<Book> JPQLTest();



  Page<Book> bookByDSLPage(org.springframework.data.domain.PageRequest id);
}
