package com.linkedInProject.connectionsService.service;

import com.linkedInProject.connectionsService.entity.Person;
import com.linkedInProject.connectionsService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionsService {

    private final PersonRepository personRepository;

    public List<Person> getAllFirstDegreeConnections(Long userId){
        List<Person> firstDegreeConnectiosn = personRepository.getFirstDegreeConeections(userId);
        return firstDegreeConnectiosn;
    }
}
