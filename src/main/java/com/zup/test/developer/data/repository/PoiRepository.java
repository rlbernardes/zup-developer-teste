package com.zup.test.developer.data.repository;

import com.zup.test.developer.data.domain.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long>, JpaSpecificationExecutor{

    List<Poi> findAll();

}
