package com.docChain.Docbackend.Repo;

import com.docChain.Docbackend.Model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocRepo extends JpaRepository<Data, String> {
    @Query("SELECT d FROM Data d WHERE d.aadharNumber = :aadharNumber")
    Optional<Data> findByAadharNumber(@Param("aadharNumber") String aadharNumber);

}
