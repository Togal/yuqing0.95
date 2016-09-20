package com.yuqing.tools;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yuqing.model.User;

public class PowerLinkTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String href;
	private String context;
	
	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		try {
            JspWriter out = this.pageContext.getOut();
            if(user == null) {
                out.println("user is null");
                return SKIP_BODY;
            }
            String str = "<a href="+href+" target=main style='color:red;display: none;'>"+context+"</a>";
            out.println(str);
        } catch(Exception e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		super.release();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	

}
