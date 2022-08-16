//package com.example.handySub.domain.match.service;
//
//import com.example.handySub.domain.match.collection.MatchCollections;
//import com.example.handySub.domain.match.dto.MatchDto;
//import com.example.handySub.domain.match.repository.MatchRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class MatchServiceImpl implements MatchService {
//    private MatchRepository matchRepository;
//
//
//    @Override
//    public List<MatchDto.GetAllResponse> getAllMatchByHandicappedId(Long handicappedId){
//        List<MatchCollections> allMatchByHandicappedId=this.matchRepository.findAllMatchByHandicappedId(handicappedId);
//        return this.convertNestedStructure(allMatchByHandicappedId);
//    }
//
//
//    private List<MatchDto.GetAllResponse> convertNestedStructure(List<MatchCollections> matches) {
//        List<MatchDto.GetAllResponse> result = new ArrayList<>();
//        Map<Long, MatchDto.GetAllResponse> map = new HashMap<>();
//        matches.stream().forEach(c -> {
//            MatchDto.GetAllResponse getResponse = MatchDto.GetAllResponse.convertMatchToDto(c);
//            map.put(getResponse.getMatchId(), getResponse);
//        });
//        return result;
//    }
//
//}
