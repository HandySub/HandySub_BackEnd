package com.example.handySub.domain.match.service;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.dto.MatchDto;
import com.example.handySub.domain.match.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
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
        matchCollections.setStartStation(1L);
        matchCollections.setFinishStation(2L);
        matchRepository.save(matchCollections);
}

}
