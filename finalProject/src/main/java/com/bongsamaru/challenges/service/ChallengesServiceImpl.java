package com.bongsamaru.challenges.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.challenges.mapper.ChallengeMapper;
import com.bongsamaru.common.VO.ChallengesVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.file.service.FilesVO;
@Service
public class ChallengesServiceImpl implements ChallengesService{
	
	@Autowired
	ChallengeMapper challengeMapper;
	
	@Override
	public List<ChallengesVO> getChallengeList(ChallengesVO challengesVO) {
		return challengeMapper.getChallengeList( challengesVO);
	}
	
	@Override
	public ChallengesVO getChallengeInfo(Integer chalId) {
		return challengeMapper.getChallengeInfo(chalId);
	}

	@Override
	public List<FilesVO> getFileList(Integer codeNo, String code) {
		return challengeMapper.getFileList(codeNo, code);
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

	@Override
	public List<LikeVO> getChallengeLike(LikeVO likeVO) {
		return challengeMapper.getChallengeLike( likeVO);
	}

	/*
	 * @Override public int challengesLikeInsert(LikeVO likeVO) { int
	 * result=challengeMapper.challengesLikeInsert(likeVO); if(result ==1) { return
	 * likeVO.getLikeId(); }else { return -1; } }
	 */

	/*
	 * @Override public int deleteChallengeLike(Integer boardId) {
	 * 
	 * return challengeMapper.deleteChallengeLike(boardId); }
	 */

	@Override
	public int reportInsert(ReportVO reportVO) {
		int result = challengeMapper.reportInsert(reportVO);
		if(result ==1) {
			return result;
		}else {
			return -1;
		}	
	}


	
}
