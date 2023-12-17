package com.missworld.missweb.Services.Implementation;

import com.missworld.missweb.Models.Contestants;
import com.missworld.missweb.Models.Users;
import com.missworld.missweb.JavaMail.Email;
import com.missworld.missweb.Repositories.ContestantRepository;
import com.missworld.missweb.Repositories.UserRepository;
import com.missworld.missweb.Repositories.VotesRepository;
import com.missworld.missweb.Services.ContestantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestantServiceImpl implements ContestantService {
    private ContestantRepository contestantRepository;
    private Email email;

    private UserRepository userRepository;
    private VotesRepository votesRepository;

    @Autowired
    public ContestantServiceImpl(ContestantRepository contestantRepository, UserRepository userRepository) {
        this.contestantRepository = contestantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Contestants> findAllContestants() {
        List<Contestants> persons = contestantRepository.findAll();
        return persons;
    }

    @Override
    public void addContestant(Contestants person) {
        contestantRepository.save(person);
    }

    @Override
    public void updateContestant(Contestants person) {
        contestantRepository.save(person);
    }

    @Override
    public Contestants findById(long Id) {
        Contestants person = contestantRepository.findById(Id).get();
        return person;
    }

    @Override
    public String findIfVoted(Users user) {
        long Id = user.getUserId();
        Users userx = userRepository.findByUserId(Id);
        if(userx.getVoted().equals("NO"))
        {
            return "NO";
        }
        else{
            return "YES";
        }
    }

    @Override
    public Boolean hasApplied(Users user) {
        int count=0;
        List<Contestants> cont = contestantRepository.findByUserId(user);
        for (Contestants contestant : cont) {
            contestant.getUserId();
            count++;
        }
        if(count > 0)
        {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void deleteById(long contestant_id) {
        contestantRepository.deleteById(contestant_id);
    }

    @Override
    public List<Contestants> searchCont(String country) {
        List<Contestants> person = contestantRepository.searchCont(country);
        return person;
    }

    @Override
    public Page<Contestants> findAllContestants(Pageable pageable) {
        return contestantRepository.findAll(pageable);
    }

    @Override
    public Page<Contestants> searchContWithPagination(String country, Pageable pageable) {
        return contestantRepository.findByCountry(country, pageable);
    }



}
