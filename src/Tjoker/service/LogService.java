package Tjoker.service;

import java.util.List;

import Tjoker.dao.LogDao;
import Tjoker.pojo.Log;

public class LogService {
//doa成对象
	LogDao lg=new LogDao();
	public int addLog(Log l) {
		// TODO Auto-generated method stub
		return lg.addLog(l);
	}
	public List<Log> allLog() {
		// TODO Auto-generated method stub
		return lg.allLog();
	}
	public List<Log> findLog(String username) {
		// TODO Auto-generated method stub
		return lg.findLog(username);
	}
	public int deletFile(int id) {
		// TODO Auto-generated method stub
		return lg.deleteLog(id);
	}

}
