package com.bongsamaru.challenges.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bongsamaru.common.VO.ChallengesVO;
import com.bongsamaru.file.service.FilesVO;

public interface ChallengesService {
	
	public List<ChallengesVO> getChallengeList(ChallengesVO challengesVO);
	public ChallengesVO getChallengeInfo(Integer chalId);
	public List<FilesVO> getFileList(@Param("codeNo") Integer codeNo, @Param("code") String code);
	public List<ChallengesVO> getChallengesList(Integer chalId);
	public int getChallengeInsert(ChallengesVO challengeVO);
	public int getChallengesInsert(ChallengesVO challengeVO);
	public boolean getChallengesDel(Integer chalDetId);
}
