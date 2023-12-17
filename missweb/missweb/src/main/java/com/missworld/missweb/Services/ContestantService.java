package com.missworld.missweb.Services;

import com.missworld.missweb.Models.Contestants;
import com.missworld.missweb.Models.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContestantService {
    List<Contestants> findAllContestants();
    void addContestant(Contestants contestant);
    void updateContestant(Contestants contestant);
    Contestants findById(long Id);
    Boolean hasApplied(Users user);
    String findIfVoted(Users user);

    void deleteById(long contestant_id);
    List<Contestants> searchCont(String country);

    Page<Contestants> findAllContestants(Pageable pageable);

    Page<Contestants> searchContWithPagination(String country, Pageable pageable);
}
