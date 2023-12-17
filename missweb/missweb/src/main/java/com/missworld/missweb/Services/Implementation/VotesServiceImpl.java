package com.missworld.missweb.Services.Implementation;

import com.missworld.missweb.Models.Contestants;
import com.missworld.missweb.Models.Users;
import com.missworld.missweb.JavaMail.Email;
import com.missworld.missweb.Models.Votes;
import com.missworld.missweb.Repositories.UserRepository;
import com.missworld.missweb.Repositories.VotesRepository;
import com.missworld.missweb.Services.VotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotesServiceImpl implements VotesService {
    private VotesRepository votesRepository;
    private UserRepository userRepository;

    @Autowired
    public VotesServiceImpl(VotesRepository votesRepository, UserRepository userRepository) {
        this.votesRepository = votesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Votes> findAllVotes(){
        List<Votes> votes = votesRepository.findAll();
        return votes;
    }


        @Override
        public String saveVotes(Contestants cont, Users user) {
            long Id = user.getUserId();
            Users userx = userRepository.findByUserId(Id);
            System.out.println(userx);
            if(userx.getVoted().equals("NO"))
            {
                Votes votes = new Votes();
                votes.setUserId(user);
                votes.setContestant_id(cont);
                votesRepository.save(votes);
                userx.setUserId(Id);
                userx.setVoted("YES");
                userRepository.save(userx);
                long contId = cont.getContestant_id();
                Email email = new Email();
                email.onVotingEmail(userx, contId);
                return "NO";
            }
            else{
                return "YES";
            }
        }

    @Override
    public List<Votes> searchVotes(long contestant_id) {
        List<Votes> votes = votesRepository.findContestant_id(contestant_id);
        return votes;
    }
}
