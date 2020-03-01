package Tjoker.service;

import java.util.List;

import Tjoker.dao.QuestionDao;
import Tjoker.pojo.Question;

public class QuestionService {
		int index=-1;
		//获取dao成对象
		QuestionDao qd=new QuestionDao();
	public int addQuestion(Question q) {
		index=qd.addQuestion(q);
		return index;
	}
	public List<Question> allQuestion() {
		// TODO Auto-generated method stub
		return qd.allQuestion();
	}
	public Question findQuestion(int id) {
		
		return qd.findQuestion(id);
	}
	public int deletQuestion(int id) {
		// TODO Auto-generated method stub
		return qd.deletQuestion(id);
	}
	public List<Question> myQuestion(int user_id) {
		
		return qd.myQuestion(user_id);
	}
		
}
