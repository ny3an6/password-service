package com.ndmitrenko.passwordservice.model.request;

import com.ndmitrenko.passwordservice.annotation.validation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateResourceFromFileRequest {
    @NotEmpty
    private String fileName;
    private String prostoTakField;
}
