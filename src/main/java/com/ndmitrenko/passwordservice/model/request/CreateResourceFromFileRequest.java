package com.ndmitrenko.passwordservice.model.request;

import com.ndmitrenko.passwordservice.annotation.validation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateResourceFromFileRequest {
    @NotEmpty
    private String fileName;
}
