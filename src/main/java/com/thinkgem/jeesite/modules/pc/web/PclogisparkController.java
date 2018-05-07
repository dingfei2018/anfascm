/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.web;

import java.util.Date;
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

import com.help.core.utils.UUIDUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pc.entity.AddressBook;
import com.thinkgem.jeesite.modules.pc.entity.Pclogispark;
import com.thinkgem.jeesite.modules.pc.entity.mix.ParkBook;
import com.thinkgem.jeesite.modules.pc.service.AddressBookService;
import com.thinkgem.jeesite.modules.pc.service.PclogisparkService;

/**
 * 物流园Controller
 * 
 * @author tfc
 * @version 2017-10-16
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/pclogispark")
public class PclogisparkController extends BaseController {

	@Autowired
	private PclogisparkService pclogisparkService;

	@Autowired
	private AddressBookService addressBookService;

	@ModelAttribute
	public Pclogispark get(@RequestParam(required = false) String id) {
		Pclogispark entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pclogisparkService.get(id);
		}
		if (entity == null) {
			entity = new Pclogispark();
		}
		return entity;
	}

	@RequiresPermissions("pc:pclogispark:view")
	@RequestMapping(value = { "list", "" })
	public String list(Pclogispark pclogispark, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Pclogispark> page = pclogisparkService.findPage(new Page<Pclogispark>(request, response), pclogispark);
		model.addAttribute("page", page);
		return "modules/pc/pclogisparkList";
	}

	@RequiresPermissions("pc:pclogispark:view")
	@RequestMapping(value = "form")
	public String form(Pclogispark pclogispark, Model model) {
		AddressBook book = new AddressBook();
		if (pclogispark.getParkAddrUuid() != null) {
			book = addressBookService.get(pclogispark.getParkAddrUuid());
		}
		ParkBook parkbook = new ParkBook();
		parkbook.setBook(book);
		parkbook.setPark(pclogispark);
		model.addAttribute("parkbook", parkbook);
		return "modules/pc/pclogisparkForm";
	}

	@RequiresPermissions("pc:pclogispark:edit")
	@RequestMapping(value = "save")
	public String save(ParkBook parkbook, Model model, RedirectAttributes redirectAttributes) {
		AddressBook book = parkbook.getBook();
		addressBookService.save(book);

		Pclogispark pclogispark = parkbook.getPark();
		if (StringUtils.isBlank(pclogispark.getId())) {
			pclogispark.setIsNewRecord(true);
		}
		if (book.getIsNewRecord()) {
			pclogispark.setParkAddrUuid(book.getId());
		}
		pclogisparkService.save(pclogispark);

		addMessage(redirectAttributes, "保存物流园成功");
		return "redirect:" + Global.getAdminPath() + "/pc/pclogispark/?repage";
	}

	@RequiresPermissions("pc:pclogispark:edit")
	@RequestMapping(value = "delete")
	public String delete(Pclogispark pclogispark, RedirectAttributes redirectAttributes) {		
		pclogisparkService.delete(pclogispark);
		AddressBook book = addressBookService.get(pclogispark.getParkAddrUuid());
		addressBookService.delete(book);
		addMessage(redirectAttributes, "删除物流园成功");
		return "redirect:" + Global.getAdminPath() + "/pc/pclogispark/?repage";
	}

}