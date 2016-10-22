package com.jgkj.plugin.repositories;

import com.jgkj.plugin.domain.Location;
import com.jgkj.plugin.domain.VideoTimeControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface VideoTimeControllRepository extends JpaRepository<VideoTimeControl, Long>,JpaSpecificationExecutor {

}
