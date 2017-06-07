package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.service.ContentService;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@Autowired
	private ContentService contenService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		String adJson = contenService.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}

	@RequestMapping(value = "/httpclient/post", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public TaotaoResult testPost(String username, String password) {
		String string = "username:" + username + "\tpassword:" + password;
		System.out.println(string);
		return TaotaoResult.ok();
	}
	
}
