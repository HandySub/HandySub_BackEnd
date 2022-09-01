package com.example.handySub.domain.mypage.service;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.constant.MatchConstants;
import com.example.handySub.domain.match.repository.MatchRepository;
import com.example.handySub.domain.match.repository.StationRepository;
import com.example.handySub.domain.mypage.dto.MypageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final MatchRepository matchRepository;
    private final StationRepository stationRepository;

    @Override
    public List<MypageDto.GetMatchList> GetAllMatchList(String handicappedId){
        List<MatchCollections> matchCollectionsList = new ArrayList<MatchCollections>();
        List<MypageDto.GetMatchList> getMatchLists = new ArrayList<>();
        List<MatchCollections> tmp = matchRepository.findAll();

        for(MatchCollections i : tmp){
            if(i.getHandicappedId().equals(handicappedId)
                    & i.getMyReview().getComment().equals(null)){
                matchCollectionsList.add(i);
            }
        }

        for(MatchCollections i : matchCollectionsList){
            MypageDto.GetMatchList getMatchList = new MypageDto.GetMatchList();
            getMatchList.setMatchId(i.getMatchId());
            getMatchList.setStartStation(i.getStartStation());
            getMatchList.setFinishStation(i.getFinishStation());
            getMatchLists.add(getMatchList);
        }
        return getMatchLists;
    }

    @Override
    public List<MypageDto.GetMatchList> GetNonAllMatchList(String nonHandicappedId){
        List<MatchCollections> matchCollectionsList = new ArrayList<MatchCollections>();
        List<MypageDto.GetMatchList> getMatchLists = new ArrayList<>();
        List<MatchCollections> tmp = matchRepository.findAll();

        for(MatchCollections i : tmp){
            if(i.getNonHandicappedId().equals(nonHandicappedId)
                    & i.getMatchingStatus().equals(MatchConstants.EMatchingStatus.SUCCESS)){
                matchCollectionsList.add(i);
            }
        }

        for(MatchCollections i : matchCollectionsList){
            MypageDto.GetMatchList getMatchList = new MypageDto.GetMatchList();
            getMatchList.setMatchId(i.getMatchId());
            getMatchList.setStartStation(i.getStartStation());
            getMatchList.setFinishStation(i.getFinishStation());
            getMatchLists.add(getMatchList);
        }
        return getMatchLists;
    }
}
