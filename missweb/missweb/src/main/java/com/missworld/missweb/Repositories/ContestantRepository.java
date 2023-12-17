package com.missworld.missweb.Repositories;

import com.missworld.missweb.Models.Contestants;
import com.missworld.missweb.Models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestantRepository  extends JpaRepository<Contestants, Long> {
    @Query("SELECT c FROM Contestants c WHERE c.country LIKE CONCAT ('%',:country,'%')")
    List<Contestants> searchCont(String  country);
    Page<Contestants> findByCountry(String country, Pageable pageable);
    List<Contestants> findByUserId(Users user);


}
