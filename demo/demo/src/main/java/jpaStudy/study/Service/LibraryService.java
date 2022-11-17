package jpaStudy.study.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import jpaStudy.study.Entity.Author;
import jpaStudy.study.Entity.Book;
import jpaStudy.study.Entity.Lend;
import jpaStudy.study.Entity.Member;
import jpaStudy.study.Entity.PageRequest;
import jpaStudy.study.Enum.LendStatus;
import jpaStudy.study.Enum.MemberStatus;
import jpaStudy.study.Exception.ErrorCode;
import jpaStudy.study.Exception.NotFoundEntityException;
import jpaStudy.study.Repository.AuthorRepository;
import jpaStudy.study.Repository.Book.BookRepository;
import jpaStudy.study.Repository.LendRepository;
import jpaStudy.study.Repository.MemberRepository;
import jpaStudy.study.Request.AuthorCreationRequest;
import jpaStudy.study.Request.BookCreationRequest;
import jpaStudy.study.Request.BookLendRequest;
import jpaStudy.study.Request.MemberCreationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor //생성사 생성
public class LibraryService {

  private final AuthorRepository authorRepository;
  private final MemberRepository memberRepository;
  private final LendRepository lendRepository;
  private final BookRepository bookRepository;

  public Page<Book> bookByPage(PageRequest pageable){
    Page<Book> books = bookRepository.findAll(pageable.of("id"));
    Optional<Page<Book>> books1 = Optional.of(bookRepository.findAll(pageable.of("id")));
    //Optional<Page<Book>> books1 = bookRepository.findAll(pageable.of());
    return books;
  }
  public Book readBookById(Long id){
    /*Optional<Book> book = bookRepository.findById(id); //Optional 객체를 사용하면 NPE 를 처라하기 편하다
    if(book.isPresent()){  //.isPresent 는 boolean 값으로 리턴해줌
      return book.get(); // Optional 의 데이터를 가져올 수 있다
    }
    throw new NotFoundEntityException("Cant find any book under given ID", ErrorCode.NOT_FOUND);*/
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new NotFoundEntityException("Cant find any book under given ID", ErrorCode.NOT_FOUND));
    return book;
  }

  public List<Book> readBooks(){
    return bookRepository.findAll();
  }

  public Book readBookByIsbn(String isbn){
    Book book = bookRepository.findByIsbn(isbn)
        .orElseThrow(() -> new EntityNotFoundException("Cant find any book under given Id"));
    return book;
//    Optional<Book> book = bookRepository.findByIsbn(isbn);
//    if(book.isPresent()){
//      return book.get();
//    }
//    throw new EntityNotFoundException ("Cant find any book under given ID");
  }

  public Book createBook(BookCreationRequest book){
    Optional<Author> author = authorRepository.findById(book.getAuthorId());
    if(!author.isPresent()){
      throw new EntityNotFoundException("Author not Found");
    }

    Book bookToCreate = new Book();
    BeanUtils.copyProperties(book, bookToCreate);
    bookToCreate.setAuthor(author.get());
    return bookRepository.save(bookToCreate);
  }

  public Integer deleteBook(Long id){
    Optional<Book> book = bookRepository.findById(id);
    if(!book.isPresent()){
      return 0;
    }
    bookRepository.deleteById(id);
    return 1;
  }

  public Member createMember(MemberCreationRequest request){
    Member member = new Member();
    member.setStatus(MemberStatus.ACTIVE);
    BeanUtils.copyProperties(request, member);
    return memberRepository.save(member);
  }

  public Member updateMember(Long id, MemberCreationRequest request){
    Optional<Member> getMember = memberRepository.findById(id);
    if(!getMember.isPresent()){
      throw new EntityNotFoundException("Not Found Member in Database");
    }
    Member member = getMember.get();
    member.setFirstName(request.getFirstName());
    member.setLastName(request.getLastName());
    return memberRepository.save(member);
  }

  public Author createAuthor(AuthorCreationRequest request){
    Author author = new Author();
    BeanUtils.copyProperties(request, author);
    return authorRepository.save(author);
  }

  @Transactional
  public List<String> lendABook(BookLendRequest request){
    List<String> bookApprovedToBurrow = new ArrayList<>();
//    Member memberForId =
//        memberRepository.findById(request.getMemberId())
//            .orElseThrow(() -> new EntityNotFoundException("Member not present in the database ==" + request.getMemberId()));
    Member memberForId =
        memberRepository.findById(request.getMemberId())
            .orElseThrow(() -> new EntityNotFoundException());
    if (memberForId.getStatus() != MemberStatus.ACTIVE) {
      throw new RuntimeException("User is not active to proceed a lending");
    }

/*    Iterator iterator = request.getBookIds().iterator();
    while (iterator.hasNext()){
      Long getBookId = (Long) iterator.next();
      Optional<Book> bookForId =
          bookRepository.findById(getBookId);
      if(!bookForId.isPresent()){
        throw new EntityNotFoundException("Cant find any book under given ID" + bookForId);
      }*/

      request.getBookIds().stream().forEach(x -> {
        Book bookForId = bookRepository.findById(x)
            .orElseThrow(() -> new EntityNotFoundException("Cant find any book under given ID" + x));
        Optional<Lend> bookLend = lendRepository.findByBookAndStatus(bookForId,LendStatus.BURROWED);
        if(!bookLend.isPresent()){
        Lend lend = Lend.builder()
            .member(memberForId)
            .book(bookForId)
            .status(LendStatus.BURROWED)
            .startOn(Instant.now())
            .dueOn(Instant.now().plus(30, ChronoUnit.DAYS))
            .build();
        lendRepository.save(lend);
        bookApprovedToBurrow.add(bookForId.getName()+" == is Borrowed");
        }else {
          throw new EntityNotFoundException("This BookId is Burrowed " + bookForId.getName());
        }
      });

    return bookApprovedToBurrow;
  }

  public List<Book> bookOrderByIsbn(){
    //return bookRepository.findAllByOrderByIsbnDesc();
    return bookRepository.findAll(Sort.by(Direction.DESC,"isbn"));
  }

  public List<Book> bookLikeSearch(String isbn){
    return bookRepository.findByIsbnContaining(isbn);
  }

  public List<Book> bookBySPQL(String isbn,String name){
    //return bookRepository.JPQLTest(isbn,name);
    return bookRepository.JPQLTest();
  }

  public Page<Book> bookByDSLPage(PageRequest pageable){
    Page<Book> books = bookRepository.bookByDSLPage(pageable.of("id"));
    Page<Book> books1 = Optional.of(bookRepository.bookByDSLPage(pageable.of("id")))
        .orElseThrow(() -> new RuntimeException("책이 없어용"));
    return books1;
  }

  /*public Page<Book> bookByPage(PageRequest pageable){
    Page<Book> books = bookRepository.findAll(pageable.of("id"));
    Optional<Page<Book>> books1 = Optional.of(bookRepository.findAll(pageable.of("id")));
    //Optional<Page<Book>> books1 = bookRepository.findAll(pageable.of());
    return books;
  }*/
}
