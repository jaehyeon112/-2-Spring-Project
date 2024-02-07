package com.bongsamaru.challenges.service;

import java.util.List;

import com.bongsamaru.common.VO.ChallengesVO;

public interface ChallengesService {
	
	public List<ChallengesVO> getChallengeList();
	public ChallengesVO getChallengeInfo(Integer chalId);
	public List<ChallengesVO> getChallengeFile(Integer chalId);
	public List<ChallengesVO> getChallengesList(Integer chalId);
	public int getChallengeInsert(ChallengesVO challengeVO);
	public int getChallengesInsert(ChallengesVO challengeVO);
	public boolean getChallengesDel(Integer chalDetId);
}
