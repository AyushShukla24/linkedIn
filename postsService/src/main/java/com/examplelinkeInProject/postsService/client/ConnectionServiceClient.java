package com.examplelinkeInProject.postsService.client;

import com.examplelinkeInProject.postsService.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connections-service", path = "/connections")
public interface ConnectionServiceClient {
     @GetMapping("/core/{userId}/first-degree")
     List<PersonDto> getAllFirstDegreeConnections(@PathVariable Long userId);
}
