package com.ndmitrenko.passwordservice.controller;

import com.ndmitrenko.passwordservice.model.entity.ResourceData;
import com.ndmitrenko.passwordservice.model.request.CreateResourceDataRequest;
import com.ndmitrenko.passwordservice.model.request.CreateResourceFromFileRequest;
import com.ndmitrenko.passwordservice.model.request.EditResourceDataRequest;
import com.ndmitrenko.passwordservice.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<ResourceData>> getResource() {
        return ResponseEntity.ok(resourceService.getResourceDataList());
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Long> createResource(@RequestBody CreateResourceDataRequest request) {
        return ResponseEntity.ok(resourceService.createResourceData(request));
    }

    @PostMapping(path = "/list/create")
    public ResponseEntity<Void> createResource(@RequestBody List<CreateResourceDataRequest> request) {
        resourceService.createListResourceData(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/file/create")
    public ResponseEntity<List<ResourceData>> createResourceFromFile(@RequestBody CreateResourceFromFileRequest request) {
        return ResponseEntity.ok(resourceService.createResourceFromFile(request));
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<Long> editResource(@RequestBody EditResourceDataRequest request) {
        resourceService.editResourceData(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/search/{searchText}")
    public ResponseEntity<List<ResourceData>> getMatchData(@PathVariable String searchText) {
        return ResponseEntity.ok(resourceService.getMatchData(searchText));
    }
}
