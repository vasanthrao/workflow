package com.metaverse.workflow.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.metaverse.workflow.model.Activity;
public interface ActivityRepository  extends JpaRepository<Activity,Long> {
}
