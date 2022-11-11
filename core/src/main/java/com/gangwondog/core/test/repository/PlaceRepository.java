package com.gangwondog.core.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gangwondog.core.test.Entity.PlaceEntity;
public interface PlaceRepository extends JpaRepository<PlaceEntity,Long> {

}
