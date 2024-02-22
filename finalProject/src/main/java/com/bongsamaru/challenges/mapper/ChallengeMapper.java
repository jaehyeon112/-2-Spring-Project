package com.bongsamaru.challenges.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bongsamaru.common.VO.ChallengesVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.file.service.FilesVO;

public interface ChallengeMapper {
	
			public List<ChallengesVO> getChallengeList();
			public ChallengesVO getChallengeInfo(int chalId);
			public List<FilesVO> getFileList(@Param("codeNo") Integer codeNo, @Param("code") String code);
			public List<ChallengesVO> getChallengesList(int chalId);			
			public int getChallengeInsert(ChallengesVO challengeVO);
			public int getChallengesInsert(ChallengesVO challengeVO);
			
			public int getChallengesDel(int chalDetId);
			public List<LikeVO> getChallengeLike(LikeVO likeVO);
			public int challengesLikeInsert(LikeVO likeVO);
			public int deleteChallengeLike(Integer boardId);
			public int reportInsert(ReportVO reportVO);
			public int getChallCheck(@Param("memId") String memId,@Param("partDate") Date partDate,@Param("chalId") Integer chalId);
			
}
