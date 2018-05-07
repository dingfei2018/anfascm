/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.help.core.utils.Password;
import com.help.core.utils.UUIDUtil;
import com.help.core.utils.passfinal.JFinalPassword;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pc.entity.AddressBook;
import com.thinkgem.jeesite.modules.pc.entity.LibImage;
import com.thinkgem.jeesite.modules.pc.entity.Pccompany;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;
import com.thinkgem.jeesite.modules.pc.entity.mix.UserBook;
import com.thinkgem.jeesite.modules.pc.service.AddressBookService;
import com.thinkgem.jeesite.modules.pc.service.PccompanyService;
import com.thinkgem.jeesite.modules.pc.service.PcuserService;
import com.thinkgem.jeesite.modules.pc.service.VerifyService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 管理平台用户Controller
 * @author tfc
 * @version 2017-09-22
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/pcuser")
public class PcuserController extends BaseController {

	@Autowired
	private PcuserService pcuserService;
	
	@Autowired
	private PccompanyService pccompanyService;
	
	@Autowired
	private AddressBookService addressBookService;
	
	@Autowired
	private VerifyService verifyService;
	
	@ModelAttribute
	public Pcuser get(@RequestParam(required=false) String id) {
		Pcuser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pcuserService.get(id);
		}
		if (entity == null){
			entity = new Pcuser();
		}
		return entity;
	}
	
	@RequiresPermissions("pc:pcuser:view")
	@RequestMapping(value = {"list", ""})
	public String list(@RequestParam Map<String, Object> paramMap,Pcuser pcuser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Pcuser> page = pcuserService.findPage(new Page<Pcuser>(request, response), pcuser); 
		model.addAttribute("page", page);
		model.addAttribute("paramMap", paramMap);
		return "modules/pc/pcuserList";
	}

	@RequiresPermissions("pc:pcuser:view")
	@RequestMapping(value = "form")
	public String form(Pcuser pcuser, Model model) {	
		Pccompany pccompany = pccompanyService.get(pcuser.getId());
		pcuser.setPccompany(pccompany);		
		AddressBook book = addressBookService.get(pccompany.getCorpAddrUuid());
		
		UserBook userbook = new UserBook();
		userbook.setBook(book);
		userbook.setPcuser(pcuser);
		
		model.addAttribute("userbook", userbook);
		List<LibImage> vimages = verifyService.getAllVerityImages(pcuser.getId());

		for (LibImage img : vimages) {
			if (img == null)
				continue;
			if (img.getTag().equals("营业执照")) {
				model.addAttribute("yyzzimg", img.getImageUri());
			}

			if (img.getTag().equals("人与身份合影")) {
				model.addAttribute("sfzimg", img.getImageUri());
			}

			if (img.getTag().equals("公司门头照")) {
				model.addAttribute("mtimg", img.getImageUri());
			}
		}		
		return "modules/pc/pcuserForm";
	}
	
	@RequiresPermissions("pc:pcuser:view")
	@RequestMapping(value = "view-{userId}")
	public String view(@PathVariable String userId, Model model) {
		Pcuser pcuser = pcuserService.get(userId);
		model.addAttribute("pcuser", pcuser);
		return "modules/pc/pcuserView";
	}

	@RequiresPermissions("pc:pcuser:edit")
	@RequestMapping(value = "save")
	public String save(@RequestParam Map<String, Object> paramMap,UserBook userbook, Model model, RedirectAttributes redirectAttributes) {	
		Pcuser pcuser = userbook.getPcuser();
		Pccompany pccompany = pcuser.getPccompany();		

		String newPass = paramMap.get("password").toString();
		if(!newPass.equals("")) {
			Password passwd = new JFinalPassword();
			pcuser.setPassword(passwd.entryptPassword(newPass));
			pcuser.setPasswordModify("yes");
		}
		pcuserService.save(pcuser);
		
		AddressBook book = userbook.getBook();
		addressBookService.save(book);
		
		if(book.getIsNewRecord()) {
			pccompany.setCorpAddrUuid(book.getId());
		}		
		pccompanyService.save(pccompany);
		
		addMessage(redirectAttributes, "保存物流公司成功");
		return "redirect:" + Global.getAdminPath() + "/pc/pcuser/?repage";
	}
	
	@RequiresPermissions("pc:pcuser:delete")
	@RequestMapping(value = "delete")
	public String delete(Pcuser pcuser, RedirectAttributes redirectAttributes) {
		pcuserService.delete(pcuser);
		addMessage(redirectAttributes, "删除平台用户成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcuser/?repage";
	}

}