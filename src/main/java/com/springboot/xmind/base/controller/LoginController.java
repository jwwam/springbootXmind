package com.springboot.xmind.base.controller;

import com.springboot.xmind.base.utils.Captcha;
import com.springboot.xmind.base.utils.GifCaptcha;
import com.springboot.xmind.base.utils.StateParameter;
import com.springboot.xmind.entity.user.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/login")
public class LoginController extends  BaseController{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="getGifCode",method = RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			/**
			 * gif格式动画验证码
			 * 宽，高，位数。
			 */
			HttpSession session = request.getSession(true);

			Captcha captcha = new GifCaptcha(146,33,4);
			/* 输出 */
			captcha.out(response.getOutputStream());
			String vcodeText = captcha.text().toLowerCase();
			//存入Session
			session.setAttribute("_code",vcodeText);
			logger.info("保存vcode:"+vcodeText);

			System.out.println("------"+session.getAttribute("_code"));

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取验证码异常："+e.getMessage());
		}
	}


	@RequestMapping("")
    public String index(){
    	
    	logger.info("初始化登录页面....");
        return"/base/login";
        
    }
	@RequestMapping("/home")
	public String home(HttpServletResponse response){
		UserInfo ui = this.getCurrentUser();
		if( ui == null ){
			try {
				response.sendRedirect("../login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("初始化home页面....");
		return"/base/home";

	}
    
    @RequestMapping("/loginUser")
	@ResponseBody
    public ModelMap loginUser(HttpServletRequest request, UserInfo ui, String vcode) {
		    HttpSession session = request.getSession();
			//转化成小写字母
			vcode = vcode.toLowerCase();
			String v = (String)session.getAttribute("_code");//还可以读取一次后把验证码清空，这样每次登录都必须获取验证码;
		    logger.info("获取保存vcode:"+v);
	    	logger.info("验证vcode:"+vcode);
			if(!vcode.equals(v)){
				logger.info("对用户[" + ui.getUsername() + "]验证码不通过");
				request.setAttribute("message", "验证码不正确");
				//返回
				return getModelMap(StateParameter.LOGINFAULT, ui,"验证码不正确");
			}
        	logger.info("进行账号"+ ui.getUsername() +",密码验证"+ ui.getPassword() +".....");
        	UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(ui.getUsername(), ui.getPassword());
        	Subject subject = SecurityUtils.getSubject();
    		try {  
        		subject.login(usernamePasswordToken);   //完成登录
        		UserInfo user=(UserInfo) subject.getPrincipal();
        		session.setAttribute("user", user);
				return getModelMap(StateParameter.LOGINSUCCESS,"","登录成功");
	        }catch(UnknownAccountException uae){  
	            logger.info("对用户[" + ui.getUsername() + "]进行登录验证..验证未通过,未知账户");
				return getModelMap(StateParameter.LOGINFAULT,"","未知账户");
	        }catch(IncorrectCredentialsException ice){  
	            logger.info("对用户[" + ui.getUsername() + "]进行登录验证..验证未通过,错误的凭证");
				return getModelMap(StateParameter.LOGINFAULT,"","密码不正确");
	        }catch(LockedAccountException lae){  
	            logger.info("对用户[" + ui.getUsername() + "]进行登录验证..验证未通过,账户已锁定");
				return getModelMap(StateParameter.LOGINFAULT,"","账户已锁定");
	        }catch(ExcessiveAttemptsException eae){  
	            logger.info("对用户[" + ui.getUsername() + "]进行登录验证..验证未通过,错误次数过多");
				return getModelMap(StateParameter.LOGINFAULT,"","用户名或密码错误次数过多");
	        }catch(AuthenticationException ae){  
	            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
	            logger.info("对用户[" + ui.getUsername() + "]进行登录验证..验证未通过,堆栈轨迹如下");
	            ae.printStackTrace();  
				return getModelMap(StateParameter.LOGINFAULT,"","用户名或密码不正确");
	        }  
        
    }
    
    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        session.removeAttribute("user");
        return "/base/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }


	/**
	 * 给定范围获得随机颜色
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 随机生成4位验证码
	 * @param httpSession
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getRand")
	public void rand(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response){
		// 在内存中创建图象
		int width = 65, height = 20;
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(6位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 将认证码存入SESSION
		httpSession.setAttribute("_code",sRand);
		logger.info("保存vcode:"+sRand);

		System.out.println("------"+httpSession.getAttribute("_code"));
		// 图象生效
		g.dispose();
		try{
			ImageIO.write(image, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
		}catch (Exception e){
		}
	}



}