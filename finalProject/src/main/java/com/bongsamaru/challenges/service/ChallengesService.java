package com.bongsamaru.challenges.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bongsamaru.common.VO.ChallengesVO;

public interface ChallengesService {
	
	public List<ChallengesVO> getChallengeList();
	public ChallengesVO getChallengeInfo(Integer chalId);
	public List<ChallengesVO> getFileList(@Param("codeNo") Integer codeNo, @Param("code") String code, @Param("chalId") Integer chalId , @Param("chalDetId") Integer chalDetId);
	public List<ChallengesVO> getChallengesList(Integer chalId);
	public int getChallengeInsert(ChallengesVO challengeVO);
	public int getChallengesInsert(ChallengesVO challengeVO);
	public boolean getChallengesDel(Integer chalDetId);
}
