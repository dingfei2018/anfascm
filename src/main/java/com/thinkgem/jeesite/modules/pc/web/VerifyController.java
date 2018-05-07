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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.pc.entity.LibImage;
import com.thinkgem.jeesite.modules.pc.entity.Pccompany;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;
import com.thinkgem.jeesite.modules.pc.entity.VerifyData;
import com.thinkgem.jeesite.modules.pc.entity.mix.VerifyCompany;
import com.thinkgem.jeesite.modules.pc.service.PccompanyService;
import com.thinkgem.jeesite.modules.pc.service.PcuserService;
import com.thinkgem.jeesite.modules.pc.service.VerifyService;

/**
 * 文章Controller
 * 
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/verify")
public class VerifyController extends BaseController {

	@Autowired
	private VerifyService verifyService;

	@Autowired
	private PcuserService pcuserService;

	@Autowired
	private PccompanyService pccompanyService;

	@ModelAttribute
	public VerifyData get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return verifyService.get(id);
		} else {
			return new VerifyData();
		}
	}

	@RequiresPermissions("pc:verify:view")
	@RequestMapping(value = { "list", "" })
	public String list(@RequestParam Map<String, Object> paramMap, VerifyData verify, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<VerifyData> page = verifyService.findPage(new Page<VerifyData>(request, response), verify, paramMap);
		model.addAttribute("page", page);
		Map<String, String> statusList = new HashMap<String, String>();
		statusList.put("1", "审核中");
		statusList.put("2", "审核成功");
		statusList.put("3", "审核失败");
		model.addAttribute("statusList", statusList);
		model.addAttribute("paramMap", paramMap);
		return "modules/pc/verifyList";
	}

	@RequiresPermissions("pc:verify:audit")
	@RequestMapping(value = "form")
	public String form(VerifyData verify, Model model) {
		Pcuser pcuser = pcuserService.get(verify.getPcuser().getId());
		Pccompany pccompany = pccompanyService.get(pcuser.getPccompany().getId());
		VerifyCompany verifyCompany = new VerifyCompany();
		verifyCompany.setCompany(pccompany);
		verifyCompany.setVerify(verify);
		model.addAttribute("verifyCompany", verifyCompany);
		List<LibImage> images = verifyService.getAllVerityImages(verify.getPcuser().getId());
		String yyzzimg = "";
		String sfzimg = "";
		String mtimg = "";
		for (LibImage img : images) {
			if(img == null) continue;
			if (img.getTag().equals("营业执照"))
				yyzzimg = img.getImageUri();
			if (img.getTag().equals("人与身份合影"))
				sfzimg = img.getImageUri();
			if (img.getTag().equals("公司门头照"))
				mtimg = img.getImageUri();
		}
		model.addAttribute("yyzzimg", yyzzimg);
		model.addAttribute("sfzimg", sfzimg);
		model.addAttribute("mtimg", mtimg);
		return "modules/pc/verifyForm";
	}

	@RequiresPermissions("pc:verify:audit")
	@RequestMapping(value = "/save", params = "verify_yes", method = RequestMethod.POST)
	public String verifyYes(VerifyCompany verifyCompany, Model model, RedirectAttributes redirectAttributes) {		
		return updateVerify(verifyCompany,model,redirectAttributes,2);
	}

	@RequiresPermissions("pc:verify:audit")
	@RequestMapping(value = "/save", params = "verify_no", method = RequestMethod.POST)
	public String verifyNo(VerifyCompany verifyCompany, Model model, RedirectAttributes redirectAttributes) {
		return updateVerify(verifyCompany,model,redirectAttributes,3);
	}
	
	private String updateVerify(VerifyCompany verifyCompany, Model model, RedirectAttributes redirectAttributes,int verifyStatus) {
		pccompanyService.updateVerify(verifyCompany.getCompany());
		VerifyData verify = verifyCompany.getVerify();
		verify.setStatus(verifyStatus);
		verifyService.save(verifyCompany.getVerify());
		addMessage(redirectAttributes, "审核'" + StringUtils.abbr(verify.getId(), 50) + "'完成");
		return "redirect:" + adminPath + "/pc/verify/list";
	}
}
