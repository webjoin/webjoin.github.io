package com.viewt.xml.dom4j;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.tree.DefaultElement;

public class Dom4jSample {
	public static void main(String[] args) {
		String text = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <Msg Version=\"1\" name=\"EPGlist\"> <EPGChannal Desc=\"CCTV-1\"> <Program name=\"因为爱情有奇缘(8)\" starttime=\"2015-06-25 15:39:00\" continuetime=\"00:50:00\"/> <Program name=\"生活提示\" starttime=\"2015-06-25 16:29:00\" continuetime=\"00:10:00\"/> <Program name=\"第一动画乐园\" starttime=\"2015-06-25 16:39:00\" continuetime=\"02:21:00\"/> <Program name=\"新闻联播\" starttime=\"2015-06-25 19:00:00\" continuetime=\"00:30:00\"/> <Program name=\"天气预报\" starttime=\"2015-06-25 19:30:00\" continuetime=\"00:08:00\"/> <Program name=\"焦点访谈\" starttime=\"2015-06-25 19:38:00\" continuetime=\"00:23:00\"/> <Program name=\"前情提要:怒放(16)\" starttime=\"2015-06-25 20:01:00\" continuetime=\"00:04:00\"/> <Program name=\"怒放(16)\" starttime=\"2015-06-25 20:05:00\" continuetime=\"00:52:00\"/> <Program name=\"前情提要:怒放(17)\" starttime=\"2015-06-25 20:57:00\" continuetime=\"00:04:00\"/> <Program name=\"怒放(17)\" starttime=\"2015-06-25 21:01:00\" continuetime=\"00:50:00\"/> <Program name=\"星推荐\" starttime=\"2015-06-25 21:51:00\" continuetime=\"00:09:00\"/> <Program name=\"晚间新闻\" starttime=\"2015-06-25 22:00:00\" continuetime=\"00:37:00\"/> <Program name=\"撒贝宁时间\" starttime=\"2015-06-25 22:37:00\" continuetime=\"01:00:00\"/> <Program name=\"2015中美舞林对抗赛(2)\" starttime=\"2015-06-25 23:37:00\" continuetime=\"00:22:00\"/> <Program name=\"动物世界\" starttime=\"2015-06-26 01:02:00\" continuetime=\"00:35:00\"/> <Program name=\"撒贝宁时间\" starttime=\"2015-06-26 01:37:00\" continuetime=\"00:50:00\"/> <Program name=\"生活早参考\" starttime=\"2015-06-26 02:27:00\" continuetime=\"00:30:00\"/> <Program name=\"天天饮食\" starttime=\"2015-06-26 02:57:00\" continuetime=\"00:16:00\"/> <Program name=\"2015中美舞林对抗赛(2)\" starttime=\"2015-06-26 03:13:00\" continuetime=\"01:18:00\"/> <Program name=\"今日说法\" starttime=\"2015-06-26 04:31:00\" continuetime=\"00:29:00\"/> <Program name=\"新闻联播\" starttime=\"2015-06-26 05:00:00\" continuetime=\"00:30:00\"/> <Program name=\"人与自然\" starttime=\"2015-06-26 05:30:00\" continuetime=\"00:30:00\"/> <Program name=\"朝闻天下\" starttime=\"2015-06-26 06:00:00\" continuetime=\"02:35:00\"/> <Program name=\"生活早参考\" starttime=\"2015-06-26 08:35:00\" continuetime=\"00:34:00\"/> <Program name=\"天天饮食\" starttime=\"2015-06-26 09:09:00\" continuetime=\"00:20:00\"/> <Program name=\"绝地刀锋(20)\" starttime=\"2015-06-26 09:29:00\" continuetime=\"00:49:00\"/> <Program name=\"绝地刀锋(21)\" starttime=\"2015-06-26 10:18:00\" continuetime=\"00:49:00\"/> <Program name=\"绝地刀锋(22)\" starttime=\"2015-06-26 11:07:00\" continuetime=\"00:53:00\"/> <Program name=\"新闻30分\" starttime=\"2015-06-26 12:00:00\" continuetime=\"00:34:00\"/> <Program name=\"今日说法\" starttime=\"2015-06-26 12:34:00\" continuetime=\"00:38:00\"/> <Program name=\"因为爱情有奇缘(9)\" starttime=\"2015-06-26 13:12:00\" continuetime=\"00:49:00\"/> <Program name=\"因为爱情有奇缘(10)\" starttime=\"2015-06-26 14:01:00\" continuetime=\"00:50:00\"/> <Program name=\"因为爱情有奇缘(11)\" starttime=\"2015-06-26 14:51:00\" continuetime=\"00:48:00\"/> <Program name=\"因为爱情有奇缘(12)\" starttime=\"2015-06-26 15:39:00\" continuetime=\"00:51:00\"/> <Program name=\"时代楷模发布厅(6)\" starttime=\"2015-06-26 16:30:00\" continuetime=\"00:49:00\"/> <Program name=\"第一动画乐园\" starttime=\"2015-06-26 17:19:00\" continuetime=\"01:41:00\"/> <Program name=\"新闻联播\" starttime=\"2015-06-26 19:00:00\" continuetime=\"00:30:00\"/> </EPGChannal> </Msg>";
		try {
			Document   document = DocumentHelper.parseText(text);
			List ls = document.selectNodes("/Msg/EPGChannal/Program");
			Iterator it = ls.iterator();
			while(it.hasNext()){
				System.out.println(((DefaultElement)it.next()).attributeValue("name"));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
	}
	
}
		
