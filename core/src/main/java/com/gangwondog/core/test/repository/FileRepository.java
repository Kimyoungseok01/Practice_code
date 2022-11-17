package com.gangwondog.core.test.repository;

import com.gangwondog.core.test.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
