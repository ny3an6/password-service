package com.ndmitrenko.passwordservice.model.entity;

import com.ndmitrenko.passwordservice.model.request.CreateResourceDataRequest;
import com.ndmitrenko.passwordservice.model.request.EditResourceDataRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "resource_data", schema = "data")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_data_generator")
    @SequenceGenerator(name = "resource_data_generator", sequenceName = "resource_data_id_seq", allocationSize = 1, schema = "data")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @ColumnDefault("false")
    @Column(name = "actual", nullable = false)
    private Boolean actual = false;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "description")
    private String description;

    @ColumnDefault("false")
    @Column(name = "is_test_data", nullable = false)
    private Boolean isTestData;

    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;

    @Column(name = "link")
    private String link;

    private static ResourceData map(CreateResourceDataRequest request) {
        return ResourceData.builder()
                .email(request.getEmail())
                .login(request.getLogin())
                .password(request.getPassword())
                .name(request.getName())
                .userId(1L)
                .actual(true)
                .createdDateTime(LocalDateTime.now())
                .build();
    }

    public static List<ResourceData> map(List<CreateResourceDataRequest> dataList){
        return dataList.stream().map(ResourceData::map).toList();
    }

}