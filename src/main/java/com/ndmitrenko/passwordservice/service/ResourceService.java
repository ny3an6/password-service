package com.ndmitrenko.passwordservice.service;

import com.ndmitrenko.passwordservice.annotation.validation.ValidateParamsNotEmpty;
import com.ndmitrenko.passwordservice.exception.AppException;
import com.ndmitrenko.passwordservice.mapper.ResourceDataMapper;
import com.ndmitrenko.passwordservice.mapper.UpdatedDataMapper;
import com.ndmitrenko.passwordservice.model.entity.ResourceData;
import com.ndmitrenko.passwordservice.model.entity.UpdatedDataHistory;
import com.ndmitrenko.passwordservice.model.request.CreateResourceDataRequest;
import com.ndmitrenko.passwordservice.model.request.CreateResourceFromFileRequest;
import com.ndmitrenko.passwordservice.model.request.EditResourceDataRequest;
import com.ndmitrenko.passwordservice.repository.ResourceDataRepository;
import com.ndmitrenko.passwordservice.repository.UpdatedDataRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceDataRepository resourceDataRepository;
    private final UpdatedDataRepository updatedDataRepository;

    public List<ResourceData> getResourceDataList() {
        return resourceDataRepository.findAll();
    }

    @ValidateParamsNotEmpty
    public Long createResourceData(CreateResourceDataRequest request) {
        Long userId = 1L; // todo: доставать id текущего пользователя из redis
        ResourceData resourceData = ResourceDataMapper.INSTANCE.map(request, LocalDateTime.now(), userId);
        resourceDataRepository.save(resourceData);
        log.info("Created resource data: {}", resourceData);
        return resourceData.getId();
    }


    public void createListResourceData(List<CreateResourceDataRequest> request) {
        List<ResourceData> resourceDataList = ResourceData.map(request);
        resourceDataRepository.saveAll(resourceDataList);
    }

    public List<ResourceData> createResourceFromFile(CreateResourceFromFileRequest request) {
        Path path = Paths.get("src/main/resources/file/"+ request.getFileName() + ".txt");
        List<ResourceData> resourceDataList;
        try {
            List<String> lines = Files.readAllLines(path);
            resourceDataList = lines.stream()
                    .map(this::buildResourceDataRequestFromString)
                    .toList();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new AppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!CollectionUtils.isEmpty(resourceDataList)) {
            resourceDataRepository.saveAll(resourceDataList);
            log.info("Created resource data list: {}", resourceDataList);
        }
        return resourceDataList;
    }

    private ResourceData buildResourceDataRequestFromString(String line) {
        List<String> lineList = List.of(line.split(" -- "));
        if (lineList.size() > 2) {
            return ResourceData.builder()
                    .name(lineList.get(0))
                    .email(lineList.get(1).replaceAll("[()]", ""))
                    .password(lineList.get(2))
                    .createdDateTime(LocalDateTime.now())
                    .userId(1L)
                    .actual(true)
                    .build();
        } else {
            throw new AppException(lineList.getFirst(), HttpStatus.BAD_REQUEST);
        }
    }

    public void editResourceData(EditResourceDataRequest request) {
        ResourceData resourceData = resourceDataRepository.findById(request.getId())
                .map(item -> ResourceDataMapper.INSTANCE.map(request, item))
                .orElseThrow(() -> new AppException("Resource not found"));
        UpdatedDataHistory updatedDataHistory = UpdatedDataMapper.INSTANCE.map(resourceData);

        resourceDataRepository.save(resourceData);
        updatedDataRepository.save(updatedDataHistory);
    }

    public List<ResourceData> getMatchData(String searchText) {
        return resourceDataRepository.getMatchData(searchText);
    }
}
