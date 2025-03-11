package com.ndmitrenko.passwordservice.mapper;

import com.ndmitrenko.passwordservice.model.entity.ResourceData;
import com.ndmitrenko.passwordservice.model.entity.UpdatedDataHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

//@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // в void методах будут игнорироваться нуловые поля
@Mapper
public interface UpdatedDataMapper {
    UpdatedDataMapper INSTANCE = Mappers.getMapper(UpdatedDataMapper.class);

    @Mapping(target = "updatedDate", expression = "java(getUpdatedDate())")
    @Mapping(target = "resourceData", source = "resourceData")
    UpdatedDataHistory map(ResourceData resourceData);

    default LocalDateTime getUpdatedDate() {
        return LocalDateTime.now();
    }
}
