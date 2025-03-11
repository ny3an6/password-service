package com.ndmitrenko.passwordservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "updated_data_history", schema = "data")
public class UpdatedDataHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "updated_data_generator")
    @SequenceGenerator(name = "updated_data_generator", sequenceName = "updated_data_id_seq", allocationSize = 1, schema = "data")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resource_data_id", nullable = false) // foreign key
    private ResourceData resourceData;

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

    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;
}

