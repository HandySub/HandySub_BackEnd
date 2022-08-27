package com.example.handySub.domain.match.service;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.collection.ReviewCollections;
import com.example.handySub.domain.match.collection.StationCollections;
import com.example.handySub.domain.match.dto.MatchDto;
import com.example.handySub.domain.match.repository.MatchRepository;
import com.example.handySub.domain.match.repository.StationRepository;
import com.example.handySub.global.sequence.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private SequenceGeneratorService sequenceGeneratorService;


    @Override
    public List<MatchDto.GetAllResponse> getAllMatchByHandicappedId(String handicappedId){
        List<MatchCollections> allMatchByHandicappedId=this.matchRepository.findAllByHandicappedId(handicappedId);
        return this.convertNestedStructure(allMatchByHandicappedId);
    }


    private List<MatchDto.GetAllResponse> convertNestedStructure(List<MatchCollections> matches) {
        List<MatchDto.GetAllResponse> result = new ArrayList<>();
        Map<String, MatchDto.GetAllResponse> map = new HashMap<>();
        matches.stream().forEach(c -> {
            MatchDto.GetAllResponse getResponse = MatchDto.GetAllResponse.convertMatchToDto(c);
            map.put(getResponse.getMatchId(), getResponse);
            result.add(getResponse);
        });
        return result;
    }

    @Override
    public void createMatch(MatchDto.CreateRequest createRequest){
        MatchCollections matchCollections=createRequest.toEntity();
        System.out.println(matchCollections.toString());
        Long temp = sequenceGeneratorService.generateSequence(matchCollections.SEQUENCE_NAME);
        matchCollections.setMatchId(Long.toString(temp));
        System.out.println(matchCollections.toString());
        matchRepository.save(matchCollections);
    }

    @Override
    public void getResultMatch(MatchDto.EnterMatch enterMatch){
        MatchCollections matchCollections=matchRepository.findByMatchId(enterMatch.getMatchId());
        matchCollections.setMatchingStatus(enterMatch.getMatchingStatus());
        matchRepository.save(matchCollections);
    }

    @Override
    public void createReview(MatchDto.RequestReview requestReview){
        MatchCollections matchCollections=matchRepository.findByMatchId(requestReview.getMatchId());
        matchCollections.setMyReview(new ReviewCollections(requestReview.getCookies(), requestReview.getComment(),requestReview.getCreatedAt()));
        matchRepository.save(matchCollections);
    }

    @Override
    public void deleteMatch(String matchId){
        MatchCollections matchCollections=matchRepository.findByMatchId(matchId);
        matchCollections.setDeleted(true);
        matchRepository.save(matchCollections);
    }

//    @Override
//    public MatchCollections validateMatchId(String matchId){
//        return this.matchRepository.findNotDeletedByBoardId(matchId).orElseThrow();
//    }

    @Override
    public void test(){
        MatchCollections matchCollections = new MatchCollections();
        Long temp = sequenceGeneratorService.generateSequence(matchCollections.SEQUENCE_NAME);
        matchCollections.setMatchId(Long.toString(temp));
        matchCollections.setStartedAt(0L);
        matchCollections.setFinishedAt(0L);
        matchCollections.setRequiredTime(10);
        matchCollections.setNonContents("good");
        matchCollections.setStartStation(new StationCollections(3L, "충무로"));
        matchCollections.setFinishStation(new StationCollections(6L, "봉화산"));
        System.out.println(matchCollections.toString());
        matchRepository.save(matchCollections);
    }

    @Override
    public List<MatchDto.GetAllNonMatch> getAllNonMatchByStation(Long startStation, Long finishStation) {
        List<MatchCollections> matchCollectionsList = new ArrayList<MatchCollections>();
        List<MatchCollections> temp = matchRepository.findAll();
        if (startStation.equals(0L) & finishStation.equals(0L)){
            for(MatchCollections i : temp){
                matchCollectionsList.add(i);
            }
        } else if(startStation.equals(0L)){
            for (MatchCollections i : temp) {
                if (i.getFinishStation().getLine().equals(finishStation)){
                    matchCollectionsList.add(i);
                }
            }
        } else if(finishStation.equals(0L)){
            for (MatchCollections i : temp) {
                if (i.getStartStation().getLine().equals(startStation)){
                    matchCollectionsList.add(i);
                }
            }
        } else{
            for (MatchCollections i : temp){
                if (i.getStartStation().getLine().equals(startStation) & i.getFinishStation().getLine().equals(finishStation)){
                    matchCollectionsList.add(i);
                }
            }
        }
        List<MatchDto.GetAllNonMatch> getNonMatchList = new ArrayList<>();
        for(MatchCollections i : matchCollectionsList){
            MatchDto.GetAllNonMatch getNonMatch = new MatchDto.GetAllNonMatch();
            getNonMatch.setMatchId(i.getMatchId());
            getNonMatch.setStartStation(i.getStartStation());
            getNonMatch.setFinishStation(i.getFinishStation());
            getNonMatchList.add(getNonMatch);
        }
        return getNonMatchList;
    }

    @Override
    public MatchDto.GetNonMatch getNonMatchByID(String matchId) {
        MatchCollections matchCollections = matchRepository.findByMatchId(matchId);
        MatchDto.GetNonMatch getNonMatchInfo = new MatchDto.GetNonMatch();
        getNonMatchInfo.setMatchId(matchCollections.getMatchId());
        getNonMatchInfo.setStartStation(matchCollections.getStartStation());
        getNonMatchInfo.setFinishStation(matchCollections.getFinishStation());
        getNonMatchInfo.setNickname("test"); // 추후 변경
        return getNonMatchInfo;
    }

    @Override
    public void patchNonMatchApply(MatchDto.GetNonMatchApply getNonMatchApply) {
        MatchCollections matchCollections = matchRepository.findByMatchId(getNonMatchApply.getMatchId());
        matchCollections.setNonHandicappedId("principal"); // 추후 principal로 수정
        matchCollections.setRequiredTime(getNonMatchApply.getRequiredTime());
        matchCollections.setNonContents(getNonMatchApply.getNonContents());

        matchRepository.save(matchCollections);
    }
}
