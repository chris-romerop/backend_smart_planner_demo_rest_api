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
package com.whiteholelabs.smartplannerdemo.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.whiteholelabs.smartplannerdemo.model.Project;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProjectRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    /// Create or update a Project.
    public Project save(Project project) {
        log.trace("Saving Project");
        dynamoDBMapper.save(project);
        return project;
    }

    /// Gets a specific Project by its ID.
    public Optional<Project> getProject(String ID) {
        log.trace("Get Project by ID");
        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .build();
        return Optional.ofNullable(dynamoDBMapper.load(Project.class, ID, config));
    }

    /// Deletes an existent Project.
    public void delete(Project project) {
        log.trace("Deleting Project");
        dynamoDBMapper.delete(project);
    }

    /// Gets a list of all existent Projects.
    public List<Project> getAllProjects() {
        log.trace("Getting all Projects");
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withProjectionExpression("ID, title, overview, createdBy, createdOn");
        PaginatedList<Project> projectList = dynamoDBMapper.scan(Project.class, scanExpression);
        projectList.loadAllResults();
        return projectList;
    }
}
