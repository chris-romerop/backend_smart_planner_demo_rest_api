//
// Copyright 2024 (c) Chris Romero.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the “Software”), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
package com.whiteholelabs.smartplannerdemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.whiteholelabs.smartplannerdemo.model.Project;
import com.whiteholelabs.smartplannerdemo.publisher.RabbitMQSender;
import com.whiteholelabs.smartplannerdemo.service.ProjectService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/// Project Controller Class.
@CrossOrigin
@RestController
public class ProjectController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RabbitMQSender sender;

    /// Creates or Updates a Project.
    @PostMapping("/project")
    public ResponseEntity<Void> saveProject(@RequestBody Project project) {
        log.trace("Post save Project");
        sender.sendMessage("project.save", project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /// Gets a specified Project by its ID.
    @GetMapping("/project/{ID}")
    public ResponseEntity<Project> getProjectByID(@PathVariable("ID") String ID) {
        return projectService.getProjectByID(ID)
                .map(newProject -> new ResponseEntity<>(newProject, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    /// Deletes an existent Project.
    @DeleteMapping("/project")
    public ResponseEntity<Void> deleteProject(@RequestBody Project project) {
        sender.sendMessage("project.delete", project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /// Gets a list of all existent Projects.
    @GetMapping("/project")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projectList = projectService.getAllProjects();
        if (projectList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}
