package com.example.handySub.domain.match.service;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.dto.MatchDto;
import com.example.handySub.domain.match.repository.MatchRepository;
import com.example.handySub.util.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MatchService {
    @Autowired
    MatchRepository matchRepository;

    public void test(){
        MatchCollections matchCollections = new MatchCollections();
        matchCollections.setMatchId(1L);
        matchCollections.setHandicapped(1L);
        matchCollections.setNonHandicapped(2L);
        matchCollections.setStartedAt(1L);
        matchCollections.setFinishedAt(1L);
        matchCollections.setRequiredTime(1);
        matchCollections.setStartStation(1L);
        matchCollections.setFinishStation(2L);

        matchRepository.save(matchCollections);
    }

    public List<MatchDto.GetNonMatch> getNonMatchList(Long startStation, Long finishStation) throws BaseException {
        List<MatchCollections> matchCollectionsList = new ArrayList<MatchCollections>();
        if (startStation.equals(0L) & finishStation.equals(0L)){
            List<MatchCollections> temp = matchRepository.findAll();
            for(MatchCollections i : temp){
                matchCollectionsList.add(i);
            }
        } else if(startStation.equals(0L)){
            List<MatchCollections> temp = matchRepository.findAllByFinishStation(finishStation);
            for(MatchCollections i : temp){
                matchCollectionsList.add(i);
            }
        } else if(finishStation.equals(0L)){
            List<MatchCollections> temp = matchRepository.findAllByStartStation(startStation);
            for(MatchCollections i : temp){
                matchCollectionsList.add(i);
            }
        } else{
            List<MatchCollections> temp = matchRepository.findAllByStartStation(startStation);
            for(MatchCollections i : temp){
                Long finish = i.getFinishStation();
                if (finish.equals(finishStation)){
                    matchCollectionsList.add(i);
                }
            }
        }
        List<MatchDto.GetNonMatch> getNonMatchList = new ArrayList<>();
        for(MatchCollections i : matchCollectionsList){
            MatchDto.GetNonMatch getNonMatch = new MatchDto.GetNonMatch();
            getNonMatch.setMatchId(i.getMatchId());
            getNonMatch.setStartStation(i.getStartStation());
            getNonMatch.setFinishStation(i.getFinishStation());
            getNonMatchList.add(getNonMatch);
        }
        return getNonMatchList;
    }

    public MatchDto.GetNonMatchInfo getNonMatchInfo(Long matchId) throws BaseException {
        MatchCollections matchCollections = matchRepository.findByMatchId(matchId);
        MatchDto.GetNonMatchInfo getNonMatchInfo = new MatchDto.GetNonMatchInfo();
        getNonMatchInfo.setMatchId(matchCollections.getMatchId());
        getNonMatchInfo.setStartStation(matchCollections.getStartStation());
        getNonMatchInfo.setFinishStation(matchCollections.getFinishStation());
//        getNonMatchInfo.setNickname();
        return getNonMatchInfo;
    }
}
