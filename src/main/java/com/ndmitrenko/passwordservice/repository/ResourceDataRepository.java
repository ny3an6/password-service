package com.ndmitrenko.passwordservice.repository;

import com.ndmitrenko.passwordservice.model.entity.ResourceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDataRepository extends JpaRepository<ResourceData, Long> {

    @Query(value = "SELECT * FROM data.resource_data d where d.email like %:searchText% OR d.description like %:searchText% " +
            "OR d.login like %:searchText% OR d.name like %:searchText%", nativeQuery = true)
    List<ResourceData> getMatchData(@Param("searchText") String searchText);
}
