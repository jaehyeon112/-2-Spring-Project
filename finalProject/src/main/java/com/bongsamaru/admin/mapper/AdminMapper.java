package com.bongsamaru.admin.mapper;


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



public interface AdminMapper {
	public List<CodeVO> subCodeList(String mainCodeId);
	public List<UserVO> getUserList(String memStat);
	public List<FaqVO> getFaqList(PageVO pageVO);
	public List<BoardVO> getBoardList(PageVO pageVO);
	public int getBoardCnt(PageVO pageVO);
	public int getFaqCnt(PageVO pageVO);
	public List<FacilityVO> getFacilityList();
	public List<VolunteerVO> meetingList(Integer volId);
	public List<TagVO> tagList();
	public List<DonationVO> getDonationList(String donRegApp);
	public List<ReportVO> getReportList(String category);
	public List<DonledgerVO> DonationKing();
	public List<VolunteerVO> facVolunteerList();
	public UserVO getUserOne(String memId);
	public int insertNotice(BoardVO boardVO);
	public int faqInsert(FaqVO faqVO);
	public VolunteerVO volCount(String memId,String mId);
	public DonationLedgerVO donCount(String memId);
	public FacilityVO getFacilityInfo(String facId);
	public DonationVO getDonOne(String donId);
	public int updateFacApp(String facName);
	public int updateDonApp(String facId);
	public int updateDonRegApp(String donId);
	public BoardVO getNoticeOne(String category,Integer detailCate);
	public int delNotice(String category,String detailCate);
	public FaqVO getFaqOne(Integer faqId);
	public int delFaq(Integer faqId);
	public int updateNotice(BoardVO boardVO);
	public int inquireComments(CommentsVO commentsVO);
	public int updateInquire(Integer boardId);
	public CommentsVO inquireCommentOne(Integer detailCode);
	public int updateReport(Integer reqCode,Integer repId);
	public List<FilesVO> selectFile(String codeNo);
	public int maxNotice();
	public int delFile(String filePath);
	public List<VolunteerVO> memMeetList(String memId);
	public List<DonaVO> donationLedgerList(String recStat);
	public List<DonaVO> facDonLedgerList(Integer donId);
	public List<DonaVO> donationSettlement();
	public DonaVO checkFacDonation(Integer donId);
	public List<RemittanceVO> remittanceList();
	public int updatePaidCode(Integer donId);
	public int insertRemittance(RemittanceVO remittanceVO);
	public List<AlertVO> alertList();
}
