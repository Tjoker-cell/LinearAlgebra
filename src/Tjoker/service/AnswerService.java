package Tjoker.service;

import java.util.List;

import Tjoker.dao.AnswerDao;
import Tjoker.pojo.Answer;

public class AnswerService {
	//调用dao成对象
	AnswerDao ad=new AnswerDao();

	public List<Answer> findAnswer(int id) {
		// TODO Auto-generated method stub
		return ad.findAnswer(id);
	}

	public int addAnswer(Answer a) {
		
		return ad.addAnswer(a);
	}

	public int deletAnswer(int id) {
		// TODO Auto-generated method stub
		return ad.deletAnswer(id);
	}

	public List<Answer> myAnswer(int user_id) {
		// TODO Auto-generated method stub
		return ad.myAnswer(user_id);
	}
	
}
