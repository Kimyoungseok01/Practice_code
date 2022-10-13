package com.gangwondog.core.test.repository;

import com.gangwondog.core.test.vo.LocalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<LocalEntity, Long> {

}
