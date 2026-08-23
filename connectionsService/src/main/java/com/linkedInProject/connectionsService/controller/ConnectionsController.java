package com.linkedInProject.connectionsService.controller;

import com.linkedInProject.connectionsService.entity.Person;
import com.linkedInProject.connectionsService.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class ConnectionsController {
    private final ConnectionsService connectionsService;

    @GetMapping("/userId/first-degree")
    private ResponseEntity<List<Person>> getAllFirstDegreeConnections(@PathVariable Long userId){
        List<Person> firstDegreeConnections = connectionsService.getAllFirstDegreeConnections(userId);
        return ResponseEntity.ok(firstDegreeConnections);
    }
}
