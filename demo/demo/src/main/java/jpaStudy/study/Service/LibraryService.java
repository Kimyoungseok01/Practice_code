package jpaStudy.study.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import jpaStudy.study.Entity.Author;
import jpaStudy.study.Entity.Book;
import jpaStudy.study.Entity.Lend;
import jpaStudy.study.Entity.Member;
import jpaStudy.study.Enum.LendStatus;
import jpaStudy.study.Enum.MemberStatus;
import jpaStudy.study.Exception.ErrorCode;
import jpaStudy.study.Exception.NotFoundEntityException;
import jpaStudy.study.Repository.AuthorRepository;
import jpaStudy.study.Repository.BookRepository;
import jpaStudy.study.Repository.LendRepository;
import jpaStudy.study.Repository.MemberRepository;
import jpaStudy.study.Request.AuthorCreationRequest;
import jpaStudy.study.Request.BookCreationRequest;
import jpaStudy.study.Request.BookLendRequest;
import jpaStudy.study.Request.MemberCreationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    Optional<Book> book = bookRepository.findByIsbn(isbn);
    if(book.isPresent()){
      return book.get();
    }
    throw new EntityNotFoundException ("Cant find any book under given ID");
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
    Optional<Member> memberForId =
        memberRepository.findById(request.getMemberId());
    if(!memberForId.isPresent()){
      throw new EntityNotFoundException("Member not present in the database ==" + request.getMemberId());
    }
    if (memberForId.get().getStatus() != MemberStatus.ACTIVE) {
      throw new RuntimeException("User is not active to proceed a lending");
    }
    Iterator iterator = request.getBookIds().iterator();
    while (iterator.hasNext()){
      Long getBookId = (Long) iterator.next();
      Optional<Book> bookForId =
          bookRepository.findById(getBookId);
      if(!bookForId.isPresent()){
        throw new EntityNotFoundException("Cant find any book under given ID" + bookForId);
      }

      Optional<Lend> burrowedBook =
          lendRepository.findByBookAndStatus(bookForId.get(), LendStatus.BURROWED);
      if(!burrowedBook.isPresent()){
        bookApprovedToBurrow.add(bookForId.get().getName());
        Lend lend = new Lend();
        lend.setMember(memberForId.get());
        lend.setBook(bookForId.get());
        lend.setStatus(LendStatus.BURROWED);
        lend.setStartOn(Instant.now());
        lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
        lendRepository.save(lend);
      } else if (burrowedBook.isPresent()) {
        bookApprovedToBurrow.add(bookForId.get().getName()+" == is Borrowed");
      }
    }
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
    return bookRepository.JPQLTest(isbn,name);
  }


}
