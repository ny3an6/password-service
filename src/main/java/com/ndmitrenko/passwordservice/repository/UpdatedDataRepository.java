package com.ndmitrenko.passwordservice.repository;

import com.ndmitrenko.passwordservice.model.entity.UpdatedDataHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdatedDataRepository extends JpaRepository<UpdatedDataHistory, Long> {

}
