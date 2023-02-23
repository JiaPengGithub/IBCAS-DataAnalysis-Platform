/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.web;

import com.jeesite.common.codec.DesUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.msg.EmailUtils;
import com.jeesite.common.msg.SmsUtils;
import com.jeesite.common.service.ServiceException;
import com.jeesite.common.shiro.authc.FormToken;
import com.jeesite.common.shiro.filter.FormFilter;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.UserService;
import com.jeesite.modules.sys.utils.PwdUtils;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.sys.utils.ValidCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 大屏展示服务Controller
 * @author hyy
 * @version 2023-02-20
 */
@Controller
@RequestMapping(value = "/desktop")
@ConditionalOnProperty(name="web.core.enabled", havingValue="true", matchIfMissing=true)
@Api(tags = "Desktop - 大屏展示")
public class DesktopController extends BaseController{

	@Autowired
	private UserService userService;

	/**
	 * 数据预处理
	 * @param user 用户信息参数
	 */
	@GetMapping(value = "dataPre")
	@ApiIgnore
	public String dataPre(User user, HttpServletRequest request) {
		return "modules/sys/dataPre";
	}

	/**
	 * 数据分析
	 * @param user 用户信息参数
	 */
	@GetMapping(value = "dataAnalysis")
	@ApiIgnore
	public String dataAnalysis(User user, HttpServletRequest request) {
		return "modules/sys/dataAnalysis";
	}

	/**
	 * 数据结果展示
	 * @param user 用户信息参数
	 */
	@GetMapping(value = "dataResult")
	@ApiIgnore
	public String dataResult(User user, HttpServletRequest request) {
		return "modules/sys/dataResult";
	}

}
