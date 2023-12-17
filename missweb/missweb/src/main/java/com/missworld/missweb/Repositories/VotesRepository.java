package com.missworld.missweb.Repositories;

import com.missworld.missweb.Models.Users;
import com.missworld.missweb.Models.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VotesRepository  extends JpaRepository<Votes, Long> {
    @Query("SELECT c FROM Votes c WHERE c.contestant_id LIKE CONCAT ('%',:contestant_id,'%')")
    List<Votes> findContestant_id(long contestant_id);

}
