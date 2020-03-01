package Tjoker.service;

import java.util.List;

import Tjoker.dao.NoticeDao;
import Tjoker.pojo.Notice;

public class NoticeService {
	//	dao
	NoticeDao nd=new NoticeDao();
	public List<Notice> allNotice(String searchString) {
		// TODO Auto-generated method stub
		return nd.allNotice(searchString);
	}
	public List<Notice> getCurrentNotice(int cPage, int pageSize, String searchString) {
		
		return nd.getCurrentNotice(cPage, pageSize,  searchString);
	}
	public int addNotice(String title, String content) {
		// TODO Auto-generated method stub
		return nd.addNotice(title,content);
	}
	public int deletNotice(int id) {
		// TODO Auto-generated method stub
		return nd.deletNotice(id);
	}
	public int edtiNotice(int id, String content) {
		// TODO Auto-generated method stub
		return nd.editNotice(id,content);
	}

}
