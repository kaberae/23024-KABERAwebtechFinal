package com.missworld.missweb.Services;

import com.missworld.missweb.Models.Contestants;
import com.missworld.missweb.Models.Users;
import com.missworld.missweb.Models.Votes;

import java.util.List;

public interface VotesService {
    List<Votes> findAllVotes();
    String saveVotes(Contestants contestant_id, Users userId);
    List<Votes> searchVotes(long contestant_id);

}
