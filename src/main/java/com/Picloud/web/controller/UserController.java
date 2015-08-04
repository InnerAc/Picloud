package com.Picloud.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Picloud.exception.UserException;
import com.Picloud.hibernate.dao.impl.UserDaoImpl;
import com.Picloud.hibernate.entities.User;
import com.Picloud.utils.EncryptUtil;
import com.Picloud.utils.JspUtil;
import com.Picloud.utils.StringSplit;
import com.Picloud.web.dao.impl.LogDaoImpl;
import com.Picloud.web.model.Log;
import com.Picloud.web.model.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDaoImpl mUserDaoImpl;
	@Autowired
	private LogDaoImpl mLogDaoImpl;
	private String module = "用户中心";
	private static int pageNum = 20 + 1;
	
	       @RequestMapping(value = "/login", method = RequestMethod.GET)
	        public String login()  {
	                return "login";
	        }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String email, String password, HttpSession session,RedirectAttributes redirectAttributes) throws Exception {
		System.out.println(email);
	        User user = mUserDaoImpl.validate(email);
		String psw = EncryptUtil.encryptMD5(password.getBytes());
		System.out.println(psw);
		if (user == null) {
			redirectAttributes.addFlashAttribute("LOGIN_MSG", "用户不存在！");
			return "redirect:/user/login";			
		} else if (!psw.equals(user.getPassword())) {
			redirectAttributes.addFlashAttribute("LOGIN_MSG", "用户名或密码错误");
			return "redirect:/user/login";					
		}

		Log log = new Log(String.valueOf(user.getUid()), "登录系统");
		mLogDaoImpl.add(log);
		user.setLastLogin(new Date());

		session.setAttribute("LoginUser", user);
		return "redirect:../index";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		      return "redirect:/user/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("user") User user) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Validated User user, BindingResult br,
			HttpSession session,RedirectAttributes redirectAttributes) throws Exception {
		if (br.hasErrors()) {
			return "register";
		}
		if (mUserDaoImpl.validate(user.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("LOGIN_MSG", "该邮箱已被注册");
			return "redirect:/user/register";
		}
		String psw = StringSplit.stringSplit(user.getPassword(), ",").get(0);
		String password = EncryptUtil.encryptMD5(psw.getBytes());
		User u = new User("0", user.getEmail(), user.getNickname(), password, 0, 0, 0);
		mUserDaoImpl.add(u);
		redirectAttributes.addFlashAttribute("LOGIN_MSG", "注册成功，请登录！");
		return "redirect:/user/login";
	}

	/**
	 * 查看个人信息
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show() {
		return "user/show";
	}

	/**
	 * 个人信息修改页
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String update(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("action", "帐号管理");
		model.addAttribute("module", module);
		return "user/update";
	}

	/**
	 * 修改个人信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @Validated User user, BindingResult br,
			HttpSession session, String pwd_old) {
		model.addAttribute("action", "帐号管理");
		model.addAttribute("module", module);

		User LoginUser = (User) session.getAttribute("LoginUser");
		if (br.hasErrors()) {
			return "user/update";
		}
		if (!pwd_old.equals(LoginUser.getPassword())) {
			throw new UserException("原密码不正确");
		}
		System.out.println(user);
		mUserDaoImpl.update(user);
		session.setAttribute("LoginUser", user);
		return "redirect:/user/update";
	}

	/**
	 * 查看个人日志
	 */
	@RequestMapping(value = "/log/{page}", method = RequestMethod.GET)
	public String listLog(@PathVariable int page, HttpSession session,
			Model model) {
		model.addAttribute("action", "操作日志");
		model.addAttribute("module", module);
		User loginUser = (User) session.getAttribute("LoginUser");
		PageInfo pi = (PageInfo) session.getAttribute("logPageInfo");
		if (pi == null) {
			pi = new PageInfo();
			pi.setNum(1);
			pi.setPage(0);
			pi.getStartKeys().add(" ");
		}
		pi.setPage(page);
		List<Log> logs = mLogDaoImpl.logPage(loginUser.getNickname(), pi
				.getStartKeys().get(pi.getPage()), pageNum);
		System.out.println(logs);
		if (logs == null || logs.size() < pageNum) {
			pi.setIfHaveNext(false);
		} else {
			pi.setIfHaveNext(true);
			pi.setNum(pi.getNum() + 1);
			pi.getStartKeys().add(logs.get(pageNum - 1).getKey());
			logs.remove(pageNum - 1);
		}
		session.setAttribute("logPageInfo", pi);
		model.addAttribute("logs", logs);
		return "user/log";
	}

	@ExceptionHandler(value = (UserException.class))
	public String handlerException(UserException e, HttpServletRequest req) {
		req.setAttribute("e", e);
		return "error";
	}
}
