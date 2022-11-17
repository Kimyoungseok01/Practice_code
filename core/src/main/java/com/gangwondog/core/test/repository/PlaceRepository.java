package com.gangwondog.core.test.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gangwondog.core.test.Entity.PlaceEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends JpaRepository<PlaceEntity,Long> {

  @Modifying
  @Query("update PlaceEntity p set p.fileId = :fileId where p.contentSeq = :contentSeq")
  void updateFile(@Param("fileId") Long fileId,@Param("contentSeq") Long contentSeq);
}
