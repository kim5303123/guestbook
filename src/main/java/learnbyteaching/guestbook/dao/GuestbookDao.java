package learnbyteaching.guestbook.dao;

import java.util.List;

import learnbyteaching.guestbook.vo.GuestbookVo;

public interface GuestbookDao {
//	public List<EmailVo> getList();
//	public boolean insert(EmailVo vo);
//	public boolean delete(Long no);
	
	public List<GuestbookVo> getList();
	public boolean insert(GuestbookVo vo);
	public boolean delete(Integer no , String password);
}
