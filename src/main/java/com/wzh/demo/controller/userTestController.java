package com.wzh.demo.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.core.status.StatusUtil;
import com.wzh.config.utils.BeanHeader;
import com.wzh.demo.dao.UserDao;
import com.wzh.demo.mapper.UserMapper;
import com.wzh.demo.service.SystemOutService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wzh.demo.domain.UserBean;
import com.wzh.demo.service.IUserService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class userTestController {

	@Autowired
	@Qualifier(value="userService")
	private IUserService userServiceImpl;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Resource
	private UserMapper userMapper;

	@RequestMapping("/userInfo.do")
	public String showUserInfoByName(HttpServletRequest requset,HttpServletResponse response,Model mode)
	{
		//这里名字写死，然后只取一个，方便测试
		UserBean user = userServiceImpl.selectUserByName("张三").get(0);
		mode.addAttribute("user",user);
		return "/test/userInfo";
		
	}
	@ResponseBody
	@RequestMapping("/update")
	public String updateUser()
	{
		UserBean user = userServiceImpl.selectUserByName("张三").get(0);
		user.setAge(40l);
		userMapper.updateUser(user);
		return "update";

	}


	// 异常未被捕获到，导致事务未完成
	@ResponseBody
	@RequestMapping("/demo1")
	public String demo1()
	{
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			UserBean userBean = new UserBean();
			userBean.setName("张三");
			userMapper.forupdateUser(userBean); // for update占用这条记录
			System.out.println(0/0); // 模拟异常
			Thread.sleep(1000);
			transactionManager.commit(status);
			System.out.println("已提交");
		} catch (InterruptedException e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			System.out.println("异常了 回滚");
		}
		return "demo1";
	}

	// 异常未被捕获到，导致事务未完成  添加finally有用么
	@ResponseBody
	@RequestMapping("/demo2")
	public String demo2()
	{
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			UserBean userBean = new UserBean();
			userBean.setName("张三");
			userMapper.forupdateUser(userBean); // for update占用这条记录
			System.out.println(0/0); // 模拟异常
			Thread.sleep(1000);
			transactionManager.commit(status);
			System.out.println("已提交");
		} catch (InterruptedException e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			System.out.println("异常了 回滚");
		}finally {
			if(!status.isCompleted()){
				System.out.println("事务未完成 进行回滚");
				transactionManager.rollback(status);
			}
		}
		return "demo2";
	}

	// 异常未被捕获到，导致事务未完成  用Exception捕获所有异常
	@ResponseBody
	@RequestMapping("/demo3")
	public String demo3()
	{
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			UserBean userBean = new UserBean();
			userBean.setName("张三");
			userMapper.forupdateUser(userBean); // for update占用这条记录
			System.out.println(0/0); // 模拟异常
			Thread.sleep(1000);
			transactionManager.commit(status);
			System.out.println("已提交");
		} catch (InterruptedException e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			System.out.println("InterruptedException异常了 回滚");
		}catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
			System.out.println("Exception异常了 回滚");
		}finally {
			if(!status.isCompleted()){
				System.out.println("事务未完成 进行回滚");
				transactionManager.rollback(status);
			}
		}
		return "demo3";
	}



	// 事务注解，看能搞定么  只要抛出了异常，事务就会触发回滚
	@Transactional
	@ResponseBody
	@RequestMapping("/demo4")
	public String demo4()
	{
		try {
			UserBean userBean = new UserBean();
			userBean.setName("张三");
			userMapper.forupdateUser(userBean); // for update占用这条记录
			System.out.println(0/0); // 模拟异常
			Thread.sleep(1000);
			System.out.println("已提交");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("异常了 回滚");
		}
		return "demo4";
	}

	// 不开启事务

	@ResponseBody
	@RequestMapping("/demo5")
	public String demo5()
	{
		try {


			UserBean userBean2 = new UserBean();
			userBean2.setId(200l);
			userBean2.setAge(66l);
			userMapper.updateUser(userBean2); // for update占用这条记录

//			UserBean userBean = new UserBean();
//			userBean.setId(1l);
//			userBean.setAge(66l);
//			userMapper.updateUser(userBean);
			Thread.sleep(1000);
			System.out.println("已提交");
		}catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常了");
		}
		return "demo5";
	}
	// for update 语句会自动提交么
	@Transactional
	@ResponseBody
	@RequestMapping("/demo6")
	public String demo6()
	{
		try {
			userMapper.forupdateTable("T_USER"); // for update占用这条记录
			UserBean userBean2 = new UserBean();
			userBean2.setId(1l);
			userBean2.setAge(66l);
//			userMapper.updateUser(userBean2);
			Thread.sleep(1000);
			System.out.println("已提交");
		}catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常了");
		}
		return "demo6";
	}

	// for update 语句会自动提交么
	@Transactional
	@ResponseBody
	@RequestMapping("/demo7")
	public String demo7()
	{
		try {
			UserBean userBean2 = new UserBean();
			userBean2.setId(1l);
			userBean2.setAge(66l);
			int i = userMapper.updateUser(userBean2);
			System.out.println("成功条数： "+i);
			Thread.sleep(1000);
			System.out.println("已提交");
		}catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常了");
		}
		return "demo6";
	}

	@RequestMapping("/img.do")
	public String showImg(HttpServletRequest request,Model model)
	{
		request.getSession();
		model.addAttribute("imgName","test.png");
		return "/test/img";
	}

	@RequestMapping("/sysout.do")
	public String sysout(HttpServletRequest request,Model model)
	{
		SystemOutService service = BeanHeader.getBean("systemOutService");
		service.sysout();
		return "";
	}

	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,Model model)
	{

		return "/test/login";
	}
}
