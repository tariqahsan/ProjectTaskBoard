package org.tahsan.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tahsan.spring.domain.ProjectTask;
import org.tahsan.spring.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask) {
		
		return projectTaskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> findAll() {
		
		return projectTaskRepository.findAll();
	}

	public ProjectTask findById(Long id) {
		
		return projectTaskRepository.getById(id);
	}

	public void deleteProjectTask(Long id) {
		
		projectTaskRepository.delete(projectTaskRepository.getById(id));
		
	}
	

}
