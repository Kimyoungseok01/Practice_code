package jpaStudy.study.Repository;

import java.util.Optional;
import jpaStudy.study.Entity.Book;
import jpaStudy.study.Entity.Lend;
import jpaStudy.study.Enum.LendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendRepository extends JpaRepository<Lend,Long> {
    //Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
