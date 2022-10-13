package jpaStudy.study.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jpaStudy.study.Entity.Author;
import jpaStudy.study.Entity.Book;
import jpaStudy.study.Entity.Member;
import jpaStudy.study.Entity.ResponseJsonObject;
import jpaStudy.study.Request.AuthorCreationRequest;
import jpaStudy.study.Request.BookCreationRequest;
import jpaStudy.study.Request.BookLendRequest;
import jpaStudy.study.Request.MemberCreationRequest;
import jpaStudy.study.Service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class BookController {

  private final LibraryService libraryService;
 //
  private ResponseJsonObject response;

  @GetMapping("/book")
  public ResponseEntity<ResponseJsonObject> readBooks(@RequestParam(required = false) String isbn){
    List<Book> books = new ArrayList<>();
    if(isbn == null){
      books = libraryService.readBooks();
    }else {
      books.add(libraryService.readBookByIsbn(isbn));
    }
    response = ResponseJsonObject.builder()
        .code(HttpStatus.OK.value())
        .httpStatus(HttpStatus.OK)
        .message("조회성공")
        .data(Arrays.asList(books)).build();
    return new ResponseEntity<>(response, response.getHttpStatus());
  }

  @GetMapping("/book/{bookId}")
  public ResponseEntity<ResponseJsonObject> readBook (@PathVariable Long bookId) {
    response = ResponseJsonObject.builder()
        .code(HttpStatus.OK.value())
        .httpStatus(HttpStatus.OK)
        .message("조회성공")
        .data(Arrays.asList(libraryService.readBookById(bookId))).build();
    return new ResponseEntity<>(response,response.getHttpStatus());

  }

  @PostMapping("/book")
  public ResponseEntity<ResponseJsonObject> createBook (@RequestBody BookCreationRequest request) {
    response = ResponseJsonObject.builder()
        .code(HttpStatus.OK.value())
        .httpStatus(HttpStatus.OK)
        .message("도서 등록 완료")
        .data(Arrays.asList(libraryService.createBook(request))).build();
    return new ResponseEntity<>(response,response.getHttpStatus());
  }

  @DeleteMapping("/book/{bookId}")
  public ResponseEntity<ResponseJsonObject> deleteBook (@PathVariable Long bookId) {
    int res = libraryService.deleteBook(bookId);
    response = ResponseJsonObject.builder()
        .code(HttpStatus.OK.value())
        .httpStatus(HttpStatus.OK)
        .message("도서 삭제 완료")
        .data(Arrays.asList(res)).build();
    return new ResponseEntity<>(response,response.getHttpStatus());
  }

  @PostMapping("/member")
  public ResponseEntity<Member> createMember (@RequestBody MemberCreationRequest request) {
    return ResponseEntity.ok(libraryService.createMember(request));
  }

  @PatchMapping("/member/{memberId}")
  public ResponseEntity<Member> updateMember (@RequestBody MemberCreationRequest request, @PathVariable Long memberId) {
    return ResponseEntity.ok(libraryService.updateMember(memberId, request));
  }

  @PostMapping("/book/lend")
  public ResponseEntity<ResponseJsonObject> lendABook(@RequestBody BookLendRequest bookLendRequests) {
    response = ResponseJsonObject.builder()
        .code(HttpStatus.OK.value())
        .httpStatus(HttpStatus.OK)
        .message("대출완료")
        .data(Arrays.asList(libraryService.lendABook(bookLendRequests))).build();
    return new ResponseEntity<>(response,response.getHttpStatus());
  }

  @PostMapping("/author")
  public ResponseEntity<Author> createAuthor (@RequestBody AuthorCreationRequest request) {
    return ResponseEntity.ok(libraryService.createAuthor(request));
  }

  @GetMapping("/book/isbn")
  public ResponseEntity bookOrderByIsbn(){
    return ResponseEntity.ok(libraryService.bookOrderByIsbn());
  }

  @GetMapping("/book/like")
  public ResponseEntity bookLikeSearch(@RequestParam String isbn){
    return ResponseEntity.ok(libraryService.bookLikeSearch(isbn));
  }

  @GetMapping("/book/jpql")
  public ResponseEntity<ResponseJsonObject> bookBySPQL(@RequestParam(required = false) String isbn,@RequestParam(required = false) String name){
    response = ResponseJsonObject.builder()
        .code(HttpStatus.OK.value())
        .httpStatus(HttpStatus.OK)
        .message("JSQL Test")
        .data(Arrays.asList(libraryService.bookBySPQL(isbn,name))).build();
    return new ResponseEntity<>(response,response.getHttpStatus());
  }

}
