package Tjoker.pojo;

import java.util.List;

public class Page {
	int currentPage;//当前页数
	int totalPage;//总页数
	int pageSize;//页面显示数
	int totalnum;//总条数
	List<FileUpDown> f;//当前文件数据的集合
	List<Notice> n;
	public List<Notice> getN() {
		return n;
	}
	public void setN(List<Notice> n) {
		this.n = n;
	}
	public List<FileUpDown> getF() {
		return f;
	}
	public void setF(List<FileUpDown> f) {
		this.f = f;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
	public int getPageSize() {
		return pageSize;
	}
	//总页数通过公式自动计算出来
	//总页数=总数量%页面大小==0？总数/页面大小：总数/页面大小+1；
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.totalPage=this.totalnum%this.pageSize==0?this.totalnum/this.pageSize:this.totalnum/this.pageSize+1;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		
		this.totalnum = totalnum;
	}
	
	
}
