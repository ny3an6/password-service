package com.ndmitrenko.passwordservice.model.request;

import com.ndmitrenko.passwordservice.annotation.validation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateResourceDataRequest {
    private String email;
    private String login;
    private Long userId;
    private String description;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private Boolean actual;
    @NotEmpty
    private Boolean isTestData;
}

