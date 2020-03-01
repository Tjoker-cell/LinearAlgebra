package Tjoker.service;

import java.util.List;

import Tjoker.dao.FileDao;
import Tjoker.pojo.FileUpDown;
//上传文件的业务层
public class FileService {
//获取dao层对象
	FileDao fd=new FileDao();
//上传文件
	//调用dao层进行添加
	public int  insert(FileUpDown f) {
		int index= fd.insert(f);
		return index;
	}
	//查询文件
		public List<FileUpDown> list() {
			
			 List<FileUpDown> list=fd.list();
			 System.out.println(list);
			return list;
		}
//	 
//		//通过id获得文件对象的全部信息
//		public FileUploadDownload select(String id) {
//	 
//			FileUploadDownload fud=f.select(id);
//			return fud;
//		}
		public FileUpDown downloadFileService(String id) {
			// TODO Auto-generated method stub
			return fd.downloadFileDao(id);
		}
}
