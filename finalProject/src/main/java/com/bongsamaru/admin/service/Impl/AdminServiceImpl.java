package com.bongsamaru.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.admin.mapper.AdminMapper;
import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.common.VO.AlertVO;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.DonationLedgerVO;
import com.bongsamaru.common.VO.DonationVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.RemittanceVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.mypage.service.DonledgerVO;

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
	public List<DonationVO> getDonationList(String donRegApp) {
		return userMapper.getDonationList(donRegApp);
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

	@Override
	public List<FilesVO> selectFile(String codeNo) {
		return userMapper.selectFile(codeNo);
	}

	@Override
	public int maxNotice() {
		return userMapper.maxNotice();
	}

	@Override
	public int delFile(String filePath) {
		return userMapper.delFile(filePath);
	}

	@Override
	public List<DonledgerVO> DonationKing() {
		return userMapper.DonationKing();
	}

	@Override
	public List<FacilityVO> meetingList() {
		return userMapper.meetingList();
	}

	@Override
	public List<TagVO> tagList() {
		return userMapper.tagList();
	}

	@Override
	public List<VolunteerVO> facVolunteerList() {
		return userMapper.facVolunteerList();
	}

	@Override
	public List<VolunteerVO> memMeetList(String memId) {
		return userMapper.memMeetList(memId);
	}

	@Override
	public List<DonaVO> donationLedgerList(String recStat) {
		return userMapper.donationLedgerList(recStat);
	}

	@Override
	public List<DonaVO> facDonLedgerList(Integer donId) {
		return userMapper.facDonLedgerList(donId);
	}

	@Override
	public List<DonaVO> donationSettlement() {
		return userMapper.donationSettlement();
	}

	@Override
	public DonaVO checkFacDonation(Integer donId) {
		return userMapper.checkFacDonation(donId);
	}

	@Override
	public List<RemittanceVO> remittanceList() {
		return userMapper.remittanceList();
	}
	
	@Transactional
	@Override
	public int insertRemittance(RemittanceVO remittanceVO) {
		//송금 확인코드 변경
		userMapper.updatePaidCode(remittanceVO.getDonId());
		//송금 테이블에 삽입
		return userMapper.insertRemittance(remittanceVO);
	}

	@Override
	public List<AlertVO> alertList() {
		return userMapper.alertList();
	}

}