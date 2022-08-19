package com.example.handySub.domain.match.service;

import com.example.handySub.domain.match.dto.MatchDto;

import java.util.List;

public interface MatchService {

    List<MatchDto.GetAllResponse> getAllMatchByHandicappedId(Long handicappedId);
    void createMatch(MatchDto.GetAllResponse getAllResponse);
    void test();
//
//    public List<MatchDto.GetNonMatch> getNonMatchList(Long startStation, Long finishStation) throws BaseException {
//        List<MatchCollections> matchCollectionsList = new ArrayList<MatchCollections>();
//        if (startStation.equals(0L) & finishStation.equals(0L)){
//            List<MatchCollections> temp = matchRepository.findAll();
//            for(MatchCollections i : temp){
//                matchCollectionsList.add(i);
//            }
//        } else if(startStation.equals(0L)){
//            List<MatchCollections> temp = matchRepository.findAllByFinishStation(finishStation);
//            for(MatchCollections i : temp){
//                matchCollectionsList.add(i);
//            }
//        } else if(finishStation.equals(0L)){
//            List<MatchCollections> temp = matchRepository.findAllByStartStation(startStation);
//            for(MatchCollections i : temp){
//                matchCollectionsList.add(i);
//            }
//        } else{
//            List<MatchCollections> temp = matchRepository.findAllByStartStation(startStation);
//            for(MatchCollections i : temp){
//                Long finish = i.getFinishStation();
//                if (finish.equals(finishStation)){
//                    matchCollectionsList.add(i);
//                }
//            }
//        }
//        List<MatchDto.GetNonMatch> getNonMatchList = new ArrayList<>();
//        for(MatchCollections i : matchCollectionsList){
//            MatchDto.GetNonMatch getNonMatch = new MatchDto.GetNonMatch();
//            getNonMatch.set_id(i.get_id());
//            getNonMatch.setStartStation(i.getStartStation());
//            getNonMatch.setFinishStation(i.getFinishStation());
//            getNonMatchList.add(getNonMatch);
//        }
//        return getNonMatchList;
//    }
//
//    public MatchDto.GetNonMatchInfo getNonMatchInfo(Long _id) throws BaseException {
//        MatchCollections matchCollections = matchRepository.findBy_id(_id);
//        MatchDto.GetNonMatchInfo getNonMatchInfo = new MatchDto.GetNonMatchInfo();
//        getNonMatchInfo.set_id(matchCollections.get_id());
//        getNonMatchInfo.setStartStation(matchCollections.getStartStation());
//        getNonMatchInfo.setFinishStation(matchCollections.getFinishStation());
////        getNonMatchInfo.setNickname();
//        return getNonMatchInfo;
//    }
}


