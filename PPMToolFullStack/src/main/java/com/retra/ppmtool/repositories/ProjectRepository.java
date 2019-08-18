package com.retra.ppmtool.repositories;

import com.retra.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String projectId);
    void deleteByProjectIdentifier(String projectId);
}
