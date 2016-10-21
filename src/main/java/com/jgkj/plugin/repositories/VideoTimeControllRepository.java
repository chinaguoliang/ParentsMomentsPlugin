package com.jgkj.plugin.repositories;

import com.jgkj.plugin.domain.Location;
import com.jgkj.plugin.domain.VideoTimeControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
public interface VideoTimeControllRepository extends JpaRepository<VideoTimeControl, Long>,JpaSpecificationExecutor {

}
