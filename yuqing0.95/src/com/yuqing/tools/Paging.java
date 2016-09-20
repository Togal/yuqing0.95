package com.yuqing.tools;

/**
 * 分页类
 * @author Administrator
 *
 */
public class Paging {
	
	private int totalCount;//总共多少条记录
	private int pageSize;//每个页面显示多少条记录
	private int totalPage;//共有多少页，不需要传进来，自行计算
	private String url;//为那个url分页
	private int groupSize;//这组显示多少个页面选择
	
	public static int PAGESIZE=10;
	public static int GROUPSIZE=5;
	
	public Paging(String url,int pageSize,int totalCount,int groupSize) {
		this.url = url;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.groupSize = groupSize;
		if(!url.contains("?")) {//考虑url是否后面带了参数
			this.url+="?test=1";
		}
		totalPage = (totalCount-1)/pageSize+1;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("totalCount:").append(totalCount).append("\n")
			.append("pageSize:").append(pageSize).append("\n")
			.append("totalPage:").append(totalPage).append("\n")
			.append("url:").append(url).append("\n")
			.append("groupSize:").append(groupSize);
		return sb.toString();
	}
	
	public String getFirst() {
		return getPage(1);
	}
	
	public String a(String url,String context) {
		StringBuffer sb = new StringBuffer();
		sb.append("<a href=")
			.append(url)
			.append(">")
			.append(context)
			.append("</a>");
		return sb.toString();
	}
	
	public String getLast() {
		return getPage(totalPage);
	}
	
	public String getPage(int num) {
		String p = "";
		if(totalCount < 1 || totalPage <1) {p = "不存在相关记录";return p;}
		if(num>totalPage){p="对不起,您访问的第"+num+"页大于最大页面"+totalPage;return p;}
		if(num<=0){p="对不起，您访问的第"+num+"页非法，不能小于1";return p;}
		
		int start = ((num-1)/groupSize*groupSize)+1;
		int end = start+groupSize-1;
		if(start+groupSize>totalPage){
			end = totalPage;
		}
		
		if(start>groupSize) {
			p+= "<li>" + a(url+"&page="+(start-1),"<")+"</li>";
		}
		for(int i=start;i<=end;i++) {
			if(i== num){
				p+="<li class='active'>"+a(url+"&page="+i,i+"<span class='sr-only'>(current)</span>")+"</li>";
			}else {
				p+="<li>"+a(url+"&page="+i,""+i)+"</li>";
			}
		}
		if(end<totalPage) {
			p+= "<li>"+a(url+"&page="+(end+1),">")+"</li>";
		}
		p+="<span class='pageHint'>&nbsp;共"+totalPage+"页,&nbsp;"+totalCount+"&nbsp;条记录</span>";
		return p;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	
	public static void main(String[] args) {
		Paging p = new Paging("http://baidu.com", 5, 30, 3);
		System.out.println(p.toString());
		System.out.println(p.getPage(4));
	}
}
