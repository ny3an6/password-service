package com.ndmitrenko.passwordservice.mapper;

import com.ndmitrenko.passwordservice.model.entity.ResourceData;
import com.ndmitrenko.passwordservice.model.request.CreateResourceDataRequest;
import com.ndmitrenko.passwordservice.model.request.EditResourceDataRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface ResourceDataMapper {
    ResourceDataMapper INSTANCE = Mappers.getMapper(ResourceDataMapper.class);

    ResourceData map(CreateResourceDataRequest request);

    ResourceData map(CreateResourceDataRequest request, LocalDateTime createdDateTime, Long userId);

    @Mapping(target = "id", source = "resourceData.id")
    @Mapping(target = "createdDateTime", source = "resourceData.createdDateTime")
    @Mapping(target = "email", expression = "java(getEmail(request, resourceData))")
    @Mapping(target = "login", expression = "java(getLogin(request, resourceData))")
    @Mapping(target = "password", expression = "java(getPassword(request, resourceData))")
    @Mapping(target = "name", expression = "java(getName(request, resourceData))")
    @Mapping(target = "description", expression = "java(getDescription(request, resourceData))")
    @Mapping(target = "actual", expression = "java(getActual(request, resourceData))")
    @Mapping(target = "isTestData", expression = "java(getIsTestData(request, resourceData))")
    ResourceData map(EditResourceDataRequest request, ResourceData resourceData);

    default boolean getIsTestData(EditResourceDataRequest request, ResourceData resourceData) {
        return request.getIsTestData() != null ? request.getIsTestData() : resourceData.getIsTestData();
    }

    default boolean getActual(EditResourceDataRequest request, ResourceData resourceData) {
        return request.getActual() != null ? request.getActual() : resourceData.getActual();
    }

    default String getEmail(EditResourceDataRequest request, ResourceData resourceData) {
        return request.getEmail() != null ? request.getEmail() : resourceData.getEmail();
    }

    default String getLogin(EditResourceDataRequest request, ResourceData resourceData) {
        return request.getLogin() != null ? request.getLogin() : resourceData.getLogin();
    }

    default String getPassword(EditResourceDataRequest request, ResourceData resourceData) {
        return request.getPassword() != null ? request.getPassword() : resourceData.getPassword();
    }

    default String getName(EditResourceDataRequest request, ResourceData resourceData) {
        return request.getName() != null ? request.getName() : resourceData.getName();
    }

    default String getDescription(EditResourceDataRequest request, ResourceData resourceData) {
        return request.getDescription() != null ? request.getDescription() : resourceData.getDescription();
    }

}
