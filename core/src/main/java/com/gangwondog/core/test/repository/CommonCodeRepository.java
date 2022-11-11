package com.gangwondog.core.test.repository;

import com.gangwondog.core.test.Entity.CommonCodeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonCodeRepository extends JpaRepository<CommonCodeEntity,String> {

  Optional<CommonCodeEntity> findByComCodeDescription(String ComCodeDescription);
}
