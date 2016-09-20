package com.yuqing.action;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yuqing.model.Module;
import com.yuqing.model.OpinionReplyCount;
import com.yuqing.model.User;
import com.yuqing.service.IInterestService;
import com.yuqing.service.IModuleService;
import com.yuqing.service.IOpinionService;
import com.yuqing.service.ISysService;
import com.yuqing.tools.ValidateCode;

public class SysAction {
	
	private ISysService sysService;
	
	private IOpinionService opinionService;
	
	private IInterestService interestService;

	public String home() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		List<OpinionReplyCount> interestTop5 = interestService.getInterestTop5ByUserId(u.getUserId());
		List<OpinionReplyCount> latestTop5 = opinionService.getLatestTop5();
		List<OpinionReplyCount> hotTop5 = opinionService.getHotTop5();
		List<OpinionReplyCount> locationTop5 = opinionService.getLocationTop5ByArea(u.getArea());
		request.setAttribute("interestTop5", interestTop5);
		request.setAttribute("latestTop5", latestTop5);
		request.setAttribute("hotTop5", hotTop5);
		request.setAttribute("locationTop5", locationTop5);
		
		//兴趣统计图数据
		JSONArray interestReplyCountData = interestService.getInteresetReplyCount(u.getUserId());
		request.setAttribute("interestReplyCountData", interestReplyCountData);
		
		//热点回复图表的x轴日期数据
		JSONArray hotReplyCountY = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -5);
		for(int i=0;i<3;i++) {
			c.add(Calendar.DATE, 1);
			hotReplyCountY.add(sdf.format(c.getTime()));
		}
		hotReplyCountY.add("昨天");
		hotReplyCountY.add("今天");
		request.setAttribute("hotReplyCountY", hotReplyCountY);
//System.out.println(opinionService.getLatestHotReplyCount());
		//热点回复图表的数据
		JSONArray hotReplyCount = opinionService.getLatestHotReplyCount();
		request.setAttribute("hotReplyCount", hotReplyCount);
		return "ok";
	}	
	public void validateCode() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		response.setContentType("images/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0); 
		ValidateCode v = new ValidateCode();
		session.setAttribute("validateCode", v.getCode()); 
		try {
			ServletOutputStream out = response.getOutputStream();
			ImageIO.write(v.getImage(), "JPEG",out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public ISysService getSysService() {
		return sysService;
	}

	public void setSysService(ISysService sysService) {
		this.sysService = sysService;
	}

	public IOpinionService getOpinionService() {
		return opinionService;
	}

	public void setOpinionService(IOpinionService opinionService) {
		this.opinionService = opinionService;
	}
	
	public IInterestService getInterestService() {
		return interestService;
	}

	public void setInterestService(IInterestService interestService) {
		this.interestService = interestService;
	}
}
