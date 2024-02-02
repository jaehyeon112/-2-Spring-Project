package com.bongsamaru.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.admin.mapper.AdminMapper;
import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.DonationLedgerVO;
import com.bongsamaru.common.VO.DonationVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.common.VO.VolunteerVO;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminMapper userMapper;

	@Override
	public List<UserVO> getUserList(String memStat) {
		return userMapper.getUserList(memStat);
	}
	
	@Override
	public List<BoardVO> getBoardList(String category) {
		return userMapper.getBoardList(category);
	}
	
	@Override
	public List<FacilityVO> getFacilityList() {
		return userMapper.getFacilityList();
	}
	
	@Override
	public List<DonationVO> getDonationList() {
		return userMapper.getDonationList();
	}
	
	@Override
	public List<FaqVO> getFaqList() {
		return userMapper.getFaqList();
	}

	@Override
	public int insertNotice(BoardVO boardVO) {
		return userMapper.insertNotice(boardVO);
	}

	@Override
	public UserVO getUserOne(String memId) {
		return userMapper.getUserOne(memId);
	}

	@Override
	public VolunteerVO volCount(String memId,String mId) {
		return userMapper.volCount(memId,mId);
	}

	@Override
	public DonationLedgerVO donCount(String memId) {
		return userMapper.donCount(memId);
	}
	
	@Override
	public FacilityVO getFacilityInfo(String facId) {
		return userMapper.getFacilityInfo(facId);
	}

	@Override
	public DonationVO getDonOne(String donId) {
		return userMapper.getDonOne(donId);
	}

	@Override
	public int updateFacApp(String facName) {
		return userMapper.updateFacApp(facName);
	}

	@Override
	public int updateDonApp(String facId) {
		return userMapper.updateDonApp(facId);
	}
	
	@Override
	public int updateDonRegApp(String donId) {
		return userMapper.updateDonRegApp(donId);
	}

	@Override
	public BoardVO getNoticeOne(String category, Integer detailCate) {
		return userMapper.getNoticeOne(category, detailCate);
	}

	@Override
	public int delNotice(String category, String detailCate) {
		return userMapper.delNotice(category, detailCate);
	}

	@Override
	public int faqInsert(FaqVO faqVO) {
		return userMapper.faqInsert(faqVO);
	}

	@Override
	public FaqVO getFaqOne(Integer faqId) {
		return userMapper.getFaqOne(faqId);
	}

	@Override
	public int delFaq(Integer faqId) {
		return userMapper.delFaq(faqId);
	}

	@Override
	public int updateNotice(BoardVO boardVO) {
		return userMapper.updateNotice(boardVO);
	}

	@Override
	public List<ReportVO> getReportList(String category) {
		return userMapper.getReportList(category);
	}

	@Override
	public int inquireComments(CommentsVO commentsVO) {
		return userMapper.inquireComments(commentsVO);
	}

	@Override
	public int updateInquire(Integer boardId) {
		return userMapper.updateInquire(boardId);
	}

	@Override
	public CommentsVO inquireCommentOne(Integer detailCode) {
		return userMapper.inquireCommentOne(detailCode);
	}

	@Override
	public int updateReport(Integer reqCode,Integer repId) {
		return userMapper.updateReport(reqCode,repId);
	}

}
