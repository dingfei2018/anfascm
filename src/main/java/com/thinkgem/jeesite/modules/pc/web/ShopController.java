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
import com.thinkgem.jeesite.modules.pc.entity.Shop;
import com.thinkgem.jeesite.modules.pc.service.ShopService;

/**
 * 店铺管理Controller
 * @author tfc
 * @version 2017-10-31
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/shop")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopService;
	
	@ModelAttribute
	public Shop get(@RequestParam(required=false) String id) {
		Shop entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopService.get(id);
		}
		if (entity == null){
			entity = new Shop();
		}
		return entity;
	}
	
	@RequiresPermissions("pc:shop:view")
	@RequestMapping(value = {"list", ""})
	public String list(Shop shop, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Shop> page = shopService.findPage(new Page<Shop>(request, response), shop); 
		model.addAttribute("page", page);
		return "modules/pc/shopList";
	}

	@RequiresPermissions("pc:shop:view")
	@RequestMapping(value = "form")
	public String form(Shop shop, Model model) {
		model.addAttribute("shop", shop);
		return "modules/pc/shopForm";
	}

	@RequiresPermissions("pc:shop:edit")
	@RequestMapping(value = "save")
	public String save(Shop shop, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shop)){
			return form(shop, model);
		}
		shopService.save(shop);
		addMessage(redirectAttributes, "保存店铺成功");
		return "redirect:"+Global.getAdminPath()+"/pc/shop/?repage";
	}
	
	@RequiresPermissions("pc:shop:edit")
	@RequestMapping(value = "delete")
	public String delete(Shop shop, RedirectAttributes redirectAttributes) {
		shopService.delete(shop);
		addMessage(redirectAttributes, "删除店铺成功");
		return "redirect:"+Global.getAdminPath()+"/pc/shop/?repage";
	}

}