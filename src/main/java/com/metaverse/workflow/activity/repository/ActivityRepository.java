package com.metaverse.workflow.activity.repository;

import com.metaverse.workflow.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository  extends JpaRepository<Activity,Long> {

    List<Activity> findByAgencyAgencyId(Long id);

}
