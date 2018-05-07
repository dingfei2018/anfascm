/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pc.entity.Pcmessage;
import com.thinkgem.jeesite.modules.pc.service.PcmessageService;

/**
 * 站内信Controller
 * @author tfc
 * @version 2017-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/pcmessage")
public class PcmessageController extends BaseController {

	@Autowired
	private PcmessageService pcmessageService;
	
	@ModelAttribute
	public Pcmessage get(@RequestParam(required=false) String id) {
		Pcmessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pcmessageService.get(id);
		}
		if (entity == null){
			entity = new Pcmessage();
		}
		return entity;
	}
	
	@RequiresPermissions("pc:pcmessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(Pcmessage pcmessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Pcmessage> page = pcmessageService.findPage(new Page<Pcmessage>(request, response), pcmessage); 
		model.addAttribute("page", page);
		return "modules/pc/pcmessageList";
	}

	@RequiresPermissions("pc:pcmessage:view")
	@RequestMapping(value = "form")
	public String form(Pcmessage pcmessage, Model model) {
		model.addAttribute("pcmessage", pcmessage);
		return "modules/pc/pcmessageForm";
	}

	@RequiresPermissions("pc:pcmessage:edit")
	@RequestMapping(value = "save")
	public String save(Pcmessage pcmessage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pcmessage)){
			return form(pcmessage, model);
		}
		pcmessageService.save(pcmessage);
		addMessage(redirectAttributes, "保存站内信成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcmessage/?repage";
	}
	
	@RequiresPermissions("pc:pcmessage:edit")
	@RequestMapping(value = "delete")
	public String delete(Pcmessage pcmessage, RedirectAttributes redirectAttributes) {
		pcmessageService.delete(pcmessage);
		addMessage(redirectAttributes, "删除站内信成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcmessage/?repage";
	}

}