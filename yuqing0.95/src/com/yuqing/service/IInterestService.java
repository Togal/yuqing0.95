package com.yuqing.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.yuqing.model.Interest;
import com.yuqing.model.OpinionReplyCount;
import com.yuqing.model.User;

public interface IInterestService {

	public List<Interest> getInterestsByUserId(Integer userId);

	public boolean add(User user, String keyWord);

	public void del(String interestId);

	public List<OpinionReplyCount> getInterestTop5ByUserId(Integer userId);

	public JSONArray getInteresetReplyCount(Integer userId);
}
