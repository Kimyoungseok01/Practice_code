package jpaStudy.study.Repository.Book;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import jpaStudy.study.Entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
//import static jpaStudy.study.Entity.QBook.book;
import static jpaStudy.study.Entity.QBook.book;
//import static demo/demo/build/src/main/java/jpaStudy/study/Entity
@Repository
@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<Book> JPQLTest() {
    return jpaQueryFactory.selectFrom(book)
        .fetch();
  }

  @Override
  public Page<Book> bookByDSLPage(PageRequest pageable) {
    System.out.println("____________________________________________1");
    List<Book> results = jpaQueryFactory
        .selectFrom(book)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    System.out.println("____________________________________________2");
    Long count = jpaQueryFactory
        .select(book.count())
        .from(book)
        .fetchOne();

    System.out.println("____________________________________________3");
    QueryResults<Book> results1 = jpaQueryFactory
        .selectFrom(book)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetchResults();

    System.out.println("____________________________________________4");
    JPAQuery<Long> countQuery = jpaQueryFactory
        .select(book.count())
        .from(book);

    return PageableExecutionUtils.getPage(results,pageable,countQuery::fetchOne);
    //return new PageImpl<>(results,pageable, results.size());
    //return new PageImpl<>(results,pageable, count);
  }
//  private BooleanExpression usernameEq(String usernameCond) {
//    return usernameCond != null ? book.author.eq(""): null;
//  }
}
