package com.bongsamaru.challenges.mapper;

import java.util.List;

import com.bongsamaru.challenges.vo.ChallengesVO;

public interface ChallengeMapper {
	
			public List<ChallengesVO> getChallengeList();
			public ChallengesVO getChallengeInfo(int chalId);
			public List<ChallengesVO> getChallengeFile(int chalId);
			public List<ChallengesVO> getChallengesList(int chalId);			
			public int getChallengeInsert(ChallengesVO challengeVO);
			public int getChallengesInsert(ChallengesVO challengeVO);
			//public Map<String, Object> updateEmpInfo(EmpVO empVO);
			public int getChallengesDel(int chalDetId);
}
