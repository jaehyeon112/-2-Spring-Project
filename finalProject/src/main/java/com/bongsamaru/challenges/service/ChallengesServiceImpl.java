package com.bongsamaru.challenges.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.challenges.mapper.ChallengeMapper;
import com.bongsamaru.common.VO.ChallengesVO;
@Service
public class ChallengesServiceImpl implements ChallengesService{
	
	@Autowired
	ChallengeMapper challengeMapper;
	
	@Override
	public List<ChallengesVO> getChallengeList() {
		return challengeMapper.getChallengeList();
	}
	
	@Override
	public ChallengesVO getChallengeInfo(Integer chalId) {
		return challengeMapper.getChallengeInfo(chalId);
	}

	@Override
	public List<ChallengesVO> getFileList(Integer codeNo, String code, Integer chalId, Integer chalDetId) {
		return challengeMapper.getFileList(codeNo, code,chalId, chalDetId);
	}

	@Override
	public List<ChallengesVO> getChallengesList(Integer chalId) {
		return challengeMapper.getChallengesList(chalId);
	}
	

	@Override
	public int getChallengeInsert(ChallengesVO challengeVO) {
		int result = challengeMapper.getChallengeInsert(challengeVO);
		if(result ==1) {
			return challengeVO.getChalId();
			
		}else {
			return -1;
		}	
	}

	@Override
	public int getChallengesInsert(ChallengesVO challengeVO) {
		int result = challengeMapper.getChallengesInsert(challengeVO);
		if(result ==1) {
			return challengeVO.getChalId();
		}else {
			return -1;
		}	
	}

	@Override
	public boolean getChallengesDel(Integer chalDetId) {
		
		int result = challengeMapper.getChallengesDel(chalDetId);
		return result == 1 ? true : false;
		
	}


	
}
