package com.topojuly.commonsite.modules.cms.web;

import com.topojuly.commonsite.common.web.BaseController;
import com.topojuly.commonsite.modules.cms.entity.Site;
import com.topojuly.commonsite.modules.cms.service.FileTplService;
import com.topojuly.commonsite.modules.cms.service.SiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 站点Controller
 * @author SongLai
 * @version 2017-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/template")
public class TemplateController extends BaseController {

    @Autowired
   	private FileTplService fileTplService;
    @Autowired
   	private SiteService siteService;

    @RequiresPermissions("cms:template:edit")
   	@RequestMapping(value = "")
   	public String index() {
   		return "modules/cms/tplIndex";
   	}

    @RequiresPermissions("cms:template:edit")
   	@RequestMapping(value = "tree")
   	public String tree(Model model) {
        Site site = siteService.get(Site.getCurrentSiteId());
   		model.addAttribute("templateList", fileTplService.getListForEdit(site.getSolutionPath()));
   		return "modules/cms/tplTree";
   	}

    @RequiresPermissions("cms:template:edit")
   	@RequestMapping(value = "form")
   	public String form(String name, Model model) {
        model.addAttribute("template", fileTplService.getFileTpl(name));
   		return "modules/cms/tplForm";
   	}

    @RequiresPermissions("cms:template:edit")
   	@RequestMapping(value = "help")
   	public String help() {
   		return "modules/cms/tplHelp";
   	}
}
