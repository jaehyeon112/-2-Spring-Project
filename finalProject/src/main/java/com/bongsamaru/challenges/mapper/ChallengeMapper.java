package com.bongsamaru.challenges.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bongsamaru.common.VO.ChallengesVO;

public interface ChallengeMapper {
	
			public List<ChallengesVO> getChallengeList();
			public ChallengesVO getChallengeInfo(int chalId);
			public List<ChallengesVO> getFileList(@Param("codeNo") Integer codeNo, @Param("code") String code, @Param("chalId") Integer chalId, @Param("chalDetId") Integer chalDetId);
			public List<ChallengesVO> getChallengesList(int chalId);			
			public int getChallengeInsert(ChallengesVO challengeVO);
			public int getChallengesInsert(ChallengesVO challengeVO);
			//public Map<String, Object> updateEmpInfo(EmpVO empVO);
			public int getChallengesDel(int chalDetId);
}
