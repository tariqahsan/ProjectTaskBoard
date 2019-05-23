package org.tahsan.spring.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tahsan.spring.domain.ProjectTask;
import org.tahsan.spring.service.ProjectTaskService;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ProjectTaskController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@PostMapping("")
	public ResponseEntity<?> addProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result) {
		
		if(result.hasErrors()) {
			
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : result.getFieldErrors()) {
				
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		ProjectTask newProjectTask = projectTaskService.saveOrUpdateProjectTask(projectTask);
		return new ResponseEntity<ProjectTask>(newProjectTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<ProjectTask> getAllProjectTask() {
		
		return projectTaskService.findAll();
		
	}
	
	@GetMapping("/{projectTaskId}")
	public ResponseEntity<?> getById(@PathVariable Long projectTaskId) {
		
		ProjectTask projectTask = projectTaskService.findById(projectTaskId);
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{projectTaskId}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable Long projectTaskId) {
		
		projectTaskService.deleteProjectTask(projectTaskId);
		return new ResponseEntity<String>("Project Task of id <" + projectTaskId + "> has been deleted successfully", HttpStatus.OK);
		
	}

}
