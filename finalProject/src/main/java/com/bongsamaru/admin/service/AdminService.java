package com.bongsamaru.admin.service;

import java.util.List;

import com.bongsamaru.common.BoardVO;
import com.bongsamaru.common.CommentsVO;
import com.bongsamaru.common.DonationLedgerVO;
import com.bongsamaru.common.DonationVO;
import com.bongsamaru.common.FacilityVO;
import com.bongsamaru.common.FaqVO;
import com.bongsamaru.common.ReportVO;
import com.bongsamaru.common.TagVO;
import com.bongsamaru.common.UserVO;
import com.bongsamaru.common.VolunteerVO;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.mypage.service.DonledgerVO;

public interface AdminService {
	public List<UserVO> getUserList(String memStat);
	public List<BoardVO> getBoardList(String category);
	public List<FacilityVO> getFacilityList();
	public List<DonationVO> getDonationList(String donRegApp);
	public List<ReportVO> getReportList(String category);
	public List<DonledgerVO> DonationKing();
	public int insertNotice(BoardVO boardVO);
	public List<FaqVO> getFaqList();
	public UserVO getUserOne(String memId);
	public VolunteerVO volCount(String memId,String mId);
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
	public int maxNotice();
	public int delFile(String filePath);
	public List<FacilityVO> meetingList();
	public List<TagVO> tagList();
}
