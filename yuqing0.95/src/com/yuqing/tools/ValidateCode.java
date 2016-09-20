package com.yuqing.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class ValidateCode {

	private BufferedImage image;
	
	private String code;
	
	private int width;
	
	private int height;

	public ValidateCode() {
		this.width = 55;
		this.height = 20;
		// 取得一个4位随机字母数字字符串
		code = RandomStringUtils.random(4, true, true);
		image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设定字体
		Font mFont = new Font("Times New Roman", Font.BOLD, 20);// 设置字体
		g.setFont(mFont);


		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		// 生成随机类
		Random random = new Random();
		for (int i = 0; i < 155; i++) {
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);
			int x3 = random.nextInt(12);
			int y3 = random.nextInt(12);
			g.drawLine(x2, y2, x2 + x3, y2 + y3);
		}

		// 将认证码显示到图象中
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));

		g.drawString(code, 2, 16);
		
		//生成同验证码颜色的干扰线
		Point a1 = new Point(0, random.nextInt(height));
		Point a2 = new Point(0, random.nextInt(height));
		Point b1 = new Point(width, random.nextInt(height));
		Point b2 = new Point(width, random.nextInt(height));
		Point c1 = new Point(random.nextInt(width),random.nextInt(height));
		Point c2 = new Point(random.nextInt(width),random.nextInt(height));
		g.drawLine(a1.x, a1.y, c1.x, c1.y);
		g.drawLine(c1.x, c1.y, b1.x, b1.y);
		g.drawLine(a2.x, a2.y, c2.x, c2.y);
		g.drawLine(c2.x, c2.y, b2.x, b2.y);
		// 图象生效
		g.dispose();
	}

	
	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
