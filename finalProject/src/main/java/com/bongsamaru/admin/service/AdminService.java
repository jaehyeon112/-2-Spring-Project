package com.bongsamaru.admin.service;

import java.util.List;

import com.bongsamaru.common.VO.AlertVO;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.CodeVO;
import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.DonationLedgerVO;
import com.bongsamaru.common.VO.DonationVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.RemittanceVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.mypage.service.DonledgerVO;


public interface AdminService {
	public List<CodeVO> subCodeList(String mainCodeId);
	public List<UserVO> getUserList(String memStat);
	public List<BoardVO> getBoardList(PageVO pageVO);
	public int getBoardCnt(PageVO pageVO);
	public List<FacilityVO> getFacilityList();
	public List<DonationVO> getDonationList(String donRegApp);
	public List<ReportVO> getReportList(String category);
	public List<DonledgerVO> DonationKing();
	public void insertNotice(BoardVO boardVO);
	public List<FaqVO> getFaqList(PageVO pageVO);
	public int getFaqCnt(PageVO pageVO);
	public UserVO getUserOne(String memId);
	public VolunteerVO volCount(String memId);
	public DonationLedgerVO donCount(String memId);
	public FacilityVO getFacilityInfo(String facId);
	public DonationVO getDonOne(String donId);
	public int updateFacApp(String facName);
	public int updateDonApp(String facId);
	public int updateDonRegApp(String donId);
	public BoardVO getNoticeOne(String category,Integer detailCate);
	public int delNotice(String category,String detailCate);
	public int faqInsert(FaqVO faqVO);
	public FaqVO getFaqOne(Integer faqId);
	public int delFaq(Integer faqId);
	public int updateNotice(BoardVO boardVO);
	public int inquireComments(CommentsVO commentsVO);
	public int updateInquire(Integer boardId);
	public CommentsVO inquireCommentOne(Integer detailCode);
	public int updateReport(Integer reqCode,Integer repId);
	public List<FilesVO> selectFile(String codeNo);
	public int delFile(String filePath);
	public List<VolunteerVO> meetingList(VolunteerVO volunteerVO);
	public List<TagVO> tagList();
	public List<VolunteerVO> facVolunteerList();
	public List<VolunteerVO> memMeetList(String memId);
	public List<DonaVO> donationLedgerList(String recStat);
	public List<DonaVO> facDonLedgerList(Integer donId);
	public List<DonaVO> donationSettlement();
	public DonaVO checkFacDonation(Integer donId);
	public List<RemittanceVO> remittanceList();
	public int insertRemittance(RemittanceVO remittanceVO);
	public List<AlertVO> alertList();
}
