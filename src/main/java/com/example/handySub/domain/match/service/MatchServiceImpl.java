package com.example.handySub.domain.match.service;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.collection.StationCollections;
import com.example.handySub.domain.match.dto.MatchDto;
import com.example.handySub.domain.match.repository.MatchRepository;
import com.example.handySub.domain.match.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final StationRepository stationRepository;

    @Autowired
    private final MongoTemplate mongoTemplate;


    @Override
    public List<MatchDto.GetAllResponse> getAllMatchByHandicappedId(Long handicappedId){
        List<MatchCollections> allMatchByHandicappedId=this.matchRepository.findAllByHandicappedId(handicappedId);
        return this.convertNestedStructure(allMatchByHandicappedId);
    }


    private List<MatchDto.GetAllResponse> convertNestedStructure(List<MatchCollections> matches) {
        List<MatchDto.GetAllResponse> result = new ArrayList<>();
        Map<String, MatchDto.GetAllResponse> map = new HashMap<>();
        matches.stream().forEach(c -> {
            MatchDto.GetAllResponse getResponse = MatchDto.GetAllResponse.convertMatchToDto(c);
            map.put(getResponse.get_id(), getResponse);
        });
        return result;
    }

    @Override
    public void createMatch(MatchDto.GetAllResponse getAllResponse){
        MatchCollections matchCollections=getAllResponse.toEntity();
        System.out.println(matchCollections.toString());
        matchRepository.save(matchCollections);
    }

    @Override
    public void test(){
        MatchCollections matchCollections = new MatchCollections();
        matchCollections.set_id("ss");
        matchCollections.setHandicappedId(1L);
        matchCollections.setNonHandicappedId(2L);
        matchCollections.setStartedAt(1L);
        matchCollections.setFinishedAt(1L);
        matchCollections.setRequiredTime(1);
        matchCollections.setStartStation(new StationCollections("1", 3L, "충무로"));
        matchCollections.setFinishStation(new StationCollections("2", 6L, "봉화산"));
        matchRepository.save(matchCollections);
    }

    @Override
    public List<MatchDto.GetAllNonMatch> getAllNonMatchByStation(Long startStation, Long finishStation) {
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
                StationCollections finish = i.getFinishStation();
                if (finish.equals(finishStation)){
                    matchCollectionsList.add(i);
                }
            }
        }
        List<MatchDto.GetAllNonMatch> getNonMatchList = new ArrayList<>();
        for(MatchCollections i : matchCollectionsList){
            MatchDto.GetAllNonMatch getNonMatch = new MatchDto.GetAllNonMatch();
            getNonMatch.set_id(i.get_id());
            getNonMatch.setStartStation(i.getStartStation());
            getNonMatch.setFinishStation(i.getFinishStation());
            getNonMatchList.add(getNonMatch);
        }
        return getNonMatchList;
    }

    @Override
    public MatchDto.GetNonMatch getNonMatchByID(String _id) {
        MatchCollections matchCollections = matchRepository.findBy_id(_id);
        MatchDto.GetNonMatch getNonMatchInfo = new MatchDto.GetNonMatch();
        getNonMatchInfo.set_id(matchCollections.get_id());
        getNonMatchInfo.setStartStation(matchCollections.getStartStation());
        getNonMatchInfo.setFinishStation(matchCollections.getFinishStation());
        getNonMatchInfo.setNickname("test"); // 추후 변경
        return getNonMatchInfo;
    }
}
