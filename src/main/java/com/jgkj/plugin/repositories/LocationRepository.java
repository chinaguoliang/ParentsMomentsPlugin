package com.jgkj.plugin.repositories;

import com.jgkj.plugin.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;



public interface LocationRepository extends JpaRepository<Location, Long>,JpaSpecificationExecutor {

}
