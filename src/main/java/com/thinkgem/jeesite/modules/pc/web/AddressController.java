/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.help.core.utils.Password;
import com.help.core.utils.UUIDUtil;
import com.help.core.utils.passfinal.JFinalPassword;
import com.thinkgem.jeesite.common.annotation.AccessToken;
import com.thinkgem.jeesite.common.annotation.DynamicSourcedb;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.EmployeeService;
import com.thinkgem.jeesite.modules.cms.service.FileTplService;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.cms.utils.TplUtils;
import com.thinkgem.jeesite.modules.pc.entity.AddressBook;
import com.thinkgem.jeesite.modules.pc.entity.Pccompany;
import com.thinkgem.jeesite.modules.pc.entity.Pclogispark;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;
import com.thinkgem.jeesite.modules.pc.entity.mix.ParkBook;
import com.thinkgem.jeesite.modules.pc.entity.mix.PcBook;
import com.thinkgem.jeesite.modules.pc.entity.mix.UserBook;
import com.thinkgem.jeesite.modules.pc.service.AddressBookService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 文章Controller
 * 
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/addressbook")
public class AddressController extends BaseController {

	// test
	@Autowired
	private AddressBookService addressBookService;

	@ModelAttribute
	public AddressBook get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return addressBookService.get(id);
		} else {
			return new AddressBook();
		}
	}

	@RequiresPermissions("pc:addressbook:view")
	@RequestMapping(value = { "list", "" })	
	public String list(AddressBook book, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AddressBook> page  = addressBookService.findPage(new Page<AddressBook>(request, response), book);
		model.addAttribute("page", page);
		return "modules/pc/bookList";
	}
	
	@RequiresPermissions("pc:addressbook:view")
	@RequestMapping(value = "form")
	public String form(AddressBook book, Model model) {
		PcBook pcbook = new PcBook();
		pcbook.setBook(book);
		model.addAttribute("pcbook", pcbook);
		return "modules/pc/bookForm";
	}
	
	@RequiresPermissions("pc:addressbook:edit")
	@RequestMapping(value = "save")
	public String save(PcBook pcbook, Model model, RedirectAttributes redirectAttributes) {		
		AddressBook book = pcbook.getBook();		
		addressBookService.save(book);		
		addMessage(redirectAttributes, "保存地址成功");
		return "redirect:" + Global.getAdminPath() + "/pc/addressbook/?repage";
	}
	
	@RequiresPermissions("pc:addressbook:edit")
	@RequestMapping(value = "delete")
	public String delete(AddressBook book, RedirectAttributes redirectAttributes) {
		addressBookService.delete(book);
		addMessage(redirectAttributes, "删除地址成功");
		return "redirect:"+Global.getAdminPath()+"/pc/addressbook/?repage";
	}
}
