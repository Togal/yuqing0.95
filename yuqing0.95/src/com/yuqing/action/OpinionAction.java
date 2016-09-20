package com.yuqing.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.yuqing.constant.Classify;
import com.yuqing.constant.Level;
import com.yuqing.model.Opinion;
import com.yuqing.model.ReplyCountAndTime;
import com.yuqing.model.Website;
import com.yuqing.service.IOpinionService;
import com.yuqing.service.IReplyService;
import com.yuqing.service.IWebsiteService;
import com.yuqing.tools.Digital;
import com.yuqing.tools.Paging;

public class OpinionAction {
	
	private IOpinionService opinionService;
	
	private IReplyService replyService;
	
	private IWebsiteService websiteService;
	
	private String opinionId;
	
	private Opinion opinion;
	
	private String searchKey;
	
	private String classify;
	
	private String searchMethod;
	
	private String page;
	
	private String paging;//分页函数返回的字符串
	
	private List<Opinion> opinions;
	
	private String data;
	
	private String head;
	
	private String dataName;
	
	private String opinionTitle;
	
	private String fromCategoryId;
	
	private String level;
	

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String detail() {		
		int id = Digital.conversion(opinionId,1);
		opinion = opinionService.getOpinionById(id);
		return "ok";
	}
	
	@SuppressWarnings("unchecked")
	public String search() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int dclassify = Digital.conversion(classify,0);
		int dsearchMethod = Digital.conversion(searchMethod,1);
		int dpage = Digital.conversion(page, 1);	
		int pageSize = 6;
		int totalCount = dsearchMethod==1?opinionService.getOpinionCountByLikeKeyWord(searchKey,dclassify)
				:opinionService.getCountByTitle(searchKey,dclassify);
		String url = request.getRequestURI()+"?searchKey="+searchKey+"&classify="+classify+"&searchMethod="+searchMethod;
		Paging p = new Paging(url, pageSize, totalCount, Paging.GROUPSIZE);
		paging = p.getPage(dpage);
		opinions = opinionService.getOpinions(searchKey,dclassify,dsearchMethod,(dpage-1)*pageSize,pageSize);
		return "ok";
	}

	@SuppressWarnings("unchecked")
	public String fromCategory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int dfromCategoryId = Digital.conversion(fromCategoryId,1);
		int dpage = Digital.conversion(page, 1);
		int totalCount = opinionService.getCountByWebSiteCategory(dfromCategoryId);
		int pageSize = 7;
		String url = request.getRequestURI()+"?fromCategoryId="+fromCategoryId;
		Paging p = new Paging(url, pageSize, totalCount, Paging.GROUPSIZE);
		paging =  p.getPage(dpage);
		opinions = opinionService.getOpinions(dfromCategoryId,(dpage-1)*pageSize,pageSize);
		head =  Classify.classifyMap.get(dfromCategoryId);
		return "ok";
	}
	
	public String trendCount() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String opinionIdStr = request.getParameter("opinionId");
		int opinionId = Digital.conversion(opinionIdStr, -1);
		Opinion opinion = opinionService.getOpinionById(opinionId);
		List<ReplyCountAndTime> rcats = replyService.getCountAndTimeByOpinionId(opinionId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", opinion.getTitle());
		JSONArray array = new JSONArray();
		for(ReplyCountAndTime rcat:rcats) {
			JSONArray ja = new JSONArray();
			ja.add(rcat.getReplyTtime().getTime());
			ja.add(rcat.getCount());
			array.add(ja);
		}		
//		array[i]
		int len = array.size();
		for(int i=0;i<len-1;i++) {
			int k = i;
			for(int j=i+1;j<len;j++) {
				JSONArray arrayk = array.getJSONArray(k);
				JSONArray arrayj = array.getJSONArray(j);
				if(arrayk.getLong(0) > arrayj.getLong(0)) {
					k = j;
				}
			}
			if(k!=i) {
				JSONArray temp = array.getJSONArray(i);
				array.set(i, array.get(k));
				array.set(k, temp);
			}
		}
		
		jsonObject.put("data", array);
		JSONArray dataArray = new JSONArray();
		dataArray.add(jsonObject);
		data =  dataArray.toString();
		opinionTitle = opinion.getTitle();
		return "ok";
	}
	public String locationCount() {
		String[] province = {"香港","海南","云南","北京","天津",
				"新疆","西藏","青海","甘肃","内蒙古",
				"宁夏","山西","辽宁","吉林","黑龙江",
				"河北","山东","河南","陕西","四川",
				"重庆","湖北","安徽","江苏","上海",
				"浙江","福建","台湾","江西","湖南",
				"贵州","广西","广东","澳门"};
		int dopinionId = Digital.conversion(opinionId, -1);
		Integer[] array = new Integer[34];
		for(int i = 0; i < 34; i ++) {
			array[i] = replyService.getCountByOpinionIdAndRegion(dopinionId,province[i]);
		}
		JSONArray datas = JSONArray.fromObject(array);
		data = datas.toString();
		return "ok";
	}
	
	public String categroyTopic() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int dlevel = Digital.conversion(level,1);
		int dpage = Digital.conversion(page, 1);
		int totalCount = opinionService.getOpinionCountByLevel(dlevel);
		int pagesize = 7;
		String url = request.getRequestURI()+"?level="+level;
		Paging p = new Paging(url, pagesize, totalCount, Paging.GROUPSIZE);
		paging = p.getPage(dpage);
		opinions = opinionService.getOpinionByLevel(dlevel,(dpage-1)*pagesize,pagesize);
		head = Level.levelMap.get(level);
		return "ok";
	}
	
	public String categoryCount() {
		data = opinionService.getCategoryCount();
		return "ok";
	}
	public String levelCount() {
		data = opinionService.getLevelCount();
		dataName = opinionService.getLevelName();
		return "ok";
	}
	
	public String websiteCount() {
		List<Website> websites = websiteService.getAllWebsite();
		List<String> datas = new ArrayList<String>();
		for(Website w:websites) {
			int num = websiteService.getOpinionCountById(w.getWebSiteId());
			String str = "['"+w.getName()+"'," +num +"]";
			datas.add(str);
		}
		JSONArray jsons = JSONArray.fromObject(datas);
		data = jsons.toString();
		return "ok";
	}

	public IOpinionService getOpinionService() {
		return opinionService;
	}

	public void setOpinionService(IOpinionService opinionService) {
		this.opinionService = opinionService;
	}
	

	public String getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(String opinionId) {
		this.opinionId = opinionId;
	}

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
	}

	public String getSearchMethod() {
		return searchMethod;
	}

	public String getFromCategoryId() {
		return fromCategoryId;
	}

	public void setFromCategoryId(String fromCategoryId) {
		this.fromCategoryId = fromCategoryId;
	}

	public void setSearchMethod(String searchMethod) {
		this.searchMethod = searchMethod;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPaging() {
		return paging;
	}

	public void setPaging(String paging) {
		this.paging = paging;
	}

	public List<Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(List<Opinion> opinions) {
		this.opinions = opinions;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getDataName() {
		return dataName;
	}
	public IWebsiteService getWebsiteService() {
		return websiteService;
	}

	public void setWebsiteService(IWebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	public IReplyService getReplyService() {
		return replyService;
	}

	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getOpinionTitle() {
		return opinionTitle;
	}

	public void setOpinionTitle(String opinionTitle) {
		this.opinionTitle = opinionTitle;
	}
	
}
