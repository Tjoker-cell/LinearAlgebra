package Tjoker.service;

import java.util.List;

import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;

import Tjoker.dao.PageDao;
import Tjoker.pojo.FileUpDown;
import Tjoker.pojo.Page;

public class PageService {
//获取dao层对象
	PageDao pd=new PageDao();
	
	
	public List<FileUpDown> getCurrentFile(int currentPage, int pageSize,String searchString) {
		
		return pd.getCurrentFile(currentPage,pageSize,searchString);
	}


	public List<FileUpDown> list(String searchString) {
		// TODO Auto-generated method stub
		return pd.list(searchString);
	}

}
