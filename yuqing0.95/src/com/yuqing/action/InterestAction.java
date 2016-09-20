package com.yuqing.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.yuqing.model.Interest;
import com.yuqing.model.ReplyCountAndTime;
import com.yuqing.model.User;
import com.yuqing.service.IInterestService;
import com.yuqing.service.IOpinionService;
import com.yuqing.service.IReplyService;

public class InterestAction {
	
	private IInterestService interestService;
	
	private IOpinionService opinionService;
	
	private IReplyService replyService;
	
	private List<Interest> interests;
	
	private String keyWord;
	
	private String interestId;
	
	private String data;
	
	
	
	
	public String interest() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		interests = interestService.getInterestsByUserId(userId);
		return "ok";
	}
	
	public String interestAddAction() {

		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("user");
		interestService.add(user,keyWord.trim());
		return "ok";
	}
	
	public void interestDelAction() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		interestService.del(interestId.trim());
		PrintWriter out = response.getWriter();
		out.write("deleted");
		out.flush();
		out.close();
	}

	public String interestTrend() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User u = (User)session.getAttribute("user");
		interests = interestService.getInterestsByUserId(u.getUserId());
		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		for(Interest i:interests) {
			jsonObject = new JSONObject();
			jsonObject.put("name", i.getContent());
			JSONArray data = new JSONArray();
			for(ReplyCountAndTime rcat:replyService.getTimeByInterest(i)) {
				JSONArray ja = new JSONArray();
				ja.add(rcat.getReplyTtime().getTime());
				ja.add(rcat.getCount());
				data.add(ja);
			}			
			//排序保证趋势图不乱
			int len = data.size();
			for(int q=0;q<len-1;q++) {
				int k = q;
				for(int j=q+1;j<len;j++) {
					JSONArray arrayk = data.getJSONArray(k);
					JSONArray arrayj = data.getJSONArray(j);
					if(arrayk.getLong(0) > arrayj.getLong(0)) {
						k = j;
					}
				}
				if(k!=q) {
					JSONArray temp = data.getJSONArray(q);
					data.set(q, data.get(k));
					data.set(k, temp);
				}
			}
			jsonObject.put("data", data);
			jsonArray.add(jsonObject);
		}
		data = jsonArray.toString();
		return "ok";
	}
	
	public IInterestService getInterestService() {
		return interestService;
	}

	public void setInterestService(IInterestService interestService) {
		this.interestService = interestService;
	}
	
	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getInterestId() {
		return interestId;
	}

	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public IReplyService getReplyService() {
		return replyService;
	}

	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}

	public IOpinionService getOpinionService() {
		return opinionService;
	}

	public void setOpinionService(IOpinionService opinionService) {
		this.opinionService = opinionService;
	}

}
