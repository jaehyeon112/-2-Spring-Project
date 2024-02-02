package com.bongsamaru.admin.mapper;


import java.util.List;


import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.CommentsVO;
import com.bongsamaru.common.DonationLedgerVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.ReportVO;
import com.bongsamaru.common.UserVO;
import com.bongsamaru.common.VolunteerVO;

public interface AdminMapper {
	public List<UserVO> getUserList(String memStat);
	public List<FaqVO> getFaqList();
	public List<BoardVO> getBoardList(String category);
	public List<FacilityVO> getFacilityList();
	public List<DonationVO> getDonationList();
	public List<ReportVO> getReportList(String category);
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
}
