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
package com.whiteholelabs.smartplannerdemo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whiteholelabs.smartplannerdemo.model.Project;
import com.whiteholelabs.smartplannerdemo.repository.ProjectRepository;

/// ProjectService Class.
@Service
public class ProjectService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProjectRepository projectRepository;

    /// Creates or updates a Project.
    public Optional<Project> saveProject(Project project) {
        log.trace("CreateProject service method called");
        projectRepository.save(project);
        return Optional.of(project);
    }

    /// Gets a specific Project by its ID.
    public Optional<Project> getProjectByID(String ID) {
        log.trace("GetProjectByID service method called");
        return projectRepository.getProject(ID);
    }

     /// Deletes an existent Project.
     public boolean deleteProject(Project project) {
        log.trace("DeleteProject service method called");
        if (projectRepository.getProject(project.getID()).isPresent()) {
            projectRepository.delete(project);
            return true;
        }
        return false;
     }

    /// Gets all list of all existent Projects.
    public List<Project> getAllProjects() {
        log.trace("GetAllProject service method called");
        return projectRepository.getAllProjects();
    }
}
