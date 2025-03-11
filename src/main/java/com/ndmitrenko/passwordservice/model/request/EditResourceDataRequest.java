package com.ndmitrenko.passwordservice.model.request;

import com.ndmitrenko.passwordservice.annotation.validation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EditResourceDataRequest {
    private Long id;
    private String email;
    private String login;
    private String description;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private Boolean isTestData;
    @NotEmpty
    private Boolean actual;
}
