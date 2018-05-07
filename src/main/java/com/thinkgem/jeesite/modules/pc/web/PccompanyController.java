/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.help.core.utils.Password;
import com.help.core.utils.passfinal.JFinalPassword;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pc.entity.AddressBook;
import com.thinkgem.jeesite.modules.pc.entity.LibImage;
import com.thinkgem.jeesite.modules.pc.entity.Pccompany;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;
import com.thinkgem.jeesite.modules.pc.service.AddressBookService;
import com.thinkgem.jeesite.modules.pc.service.PccompanyService;
import com.thinkgem.jeesite.modules.pc.service.PcuserService;
import com.thinkgem.jeesite.modules.pc.service.VerifyService;

/**
 * 平台物流公司Controller
 * 
 * @author tfc
 * @version 2017-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/pccompany")
public class PccompanyController extends BaseController {

	@Autowired
	private PccompanyService pccompanyService;

	@Autowired
	private PcuserService pcuserService;

	@Autowired
	private VerifyService verifyService;

	@Autowired
	private AddressBookService addressBookService;
	
	@ModelAttribute
	public Pccompany get(@RequestParam(required = false) String id) {
		Pccompany entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pccompanyService.get(id);
		}
		if (entity == null) {
			entity = new Pccompany();
		}
		return entity;
	}

	@RequiresPermissions("pc:pccompany:view")
	@RequestMapping(value = { "list", "" })
	public String list(Pccompany pccompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Pccompany> page = pccompanyService.findPage(new Page<Pccompany>(request, response), pccompany);
		model.addAttribute("page", page);
		return "modules/pc/pccompanyList";
	}

	@RequiresPermissions("pc:pccompany:view")
	@RequestMapping(value = "form")
	public String form(Pccompany pccompany, Model model) {
		Pcuser pcuser = pcuserService.get("1");
		//pccompany.setPcuser(pcuser);
		model.addAttribute("pccompany", pccompany);
        
		AddressBook book = addressBookService.get(pccompany.getCorpAddrUuid());
		model.addAttribute("book", book);
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

		return "modules/pc/pccompanyForm";
	}

	@RequiresPermissions("pc:pccompany:edit")
	@RequestMapping(value = "save")
	public String save(@RequestParam Map<String, Object> paramMap,Pccompany pccompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pccompany)) {
			return form(pccompany, model);
		}
		pccompanyService.save(pccompany);
		addMessage(redirectAttributes, "保存物流公司成功");
		
		/*Pcuser pcuser=pccompany.getPcuser();
		pcuser.setMobile(pccompany.getPcuser().getMobile());*/
		Pcuser pcuser = new Pcuser();
		String newPass = paramMap.get("password").toString();
		if(!newPass.equals("")) {
			Password passwd = new JFinalPassword();
			pcuser.setPassword(passwd.entryptPassword(newPass));
			pcuser.setPasswordModify("yes");
		}
		pcuserService.save(pcuser);
		
		String regionCode= paramMap.get("area").toString();
		String tailAddress= paramMap.get("tailAddress").toString();
		AddressBook book = new AddressBook();
		book.setId(pccompany.getCorpAddrUuid());
		book.setRegionCode(regionCode);
		book.setTailAddress(tailAddress);
		addressBookService.save(book);
		return "redirect:" + Global.getAdminPath() + "/pc/pcuser/?repage";
	}

	@RequiresPermissions("pc:pccompany:edit")
	@RequestMapping(value = "delete")
	public String delete(Pccompany pccompany, RedirectAttributes redirectAttributes) {
		pccompanyService.delete(pccompany);
		addMessage(redirectAttributes, "删除物流公司成功");
		return "redirect:" + Global.getAdminPath() + "/pc/pccompany/?repage";
	}

}