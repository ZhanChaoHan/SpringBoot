<%@ page contentType="image/jpeg; charset=utf-8"%>
<%@ page import="java.awt.*"%>
<%@ page import="java.awt.image.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.imageio.*"%>
<%@ page import="java.io.OutputStream"%>

<%!
	//生成随机颜色
	Color getRandColor(Random random, int fc, int bc) {
		if (fc > 255) fc = 255;
		if (bc > 255) bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
%>
<%
	//设置页面不缓存
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	// 设置图片的长宽
	int width = 60, height = 20;
	//产生图片数字数组 
	//数字去掉0和1，字母去掉O和I已经所有的小写字母
	String[] code={
			"2","3","4","5","6","7","8","9",
			"A","B","C","D","E","F","G","H","J",
			"K","L","M","N","P","Q","R","S","T",
			"U","V","W","X","Y","Z"
	};
	//创建内存图像
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	// 获取图形上下文
	Graphics g = image.getGraphics();
	//创建随机类的实例
	Random random = new Random();
	// 设定图像背景色(因为是做背景，所以偏淡)
	g.setColor(getRandColor(random, 200, 250));
	g.fillRect(0, 0, width, height);
	
	// 随机产生100条干扰线，使图象中的认证码不易被其它程序探测到    
	g.setColor(getRandColor(random, 160, 200));
	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	for (int i=0;i<100;i++)    
	{    
		int x = random.nextInt(width);    
		int y = random.nextInt(height);    
		int xl = random.nextInt(12);    
		int yl = random.nextInt(12);    
		g.drawLine(x,y,x+xl,y+yl);    
		}
	//取随机产生的认证码(4个汉字)
	//保存生成的汉字字符串
	StringBuffer sRand = new StringBuffer();
	for (int i = 0; i < 4; i++) {
		String rand= code[random.nextInt(code.length)];
		sRand.append(rand);    
		// 将认证码显示到图象中    
		g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));    
		// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成    
		g.drawString(rand,13*i+6,16);
	}
	//将认证码存入session
	session.setAttribute("rand", sRand.toString());
	sRand = null;
	g.dispose();
	//输出图象到页面
	ImageIO.write(image, "JPEG", response.getOutputStream());
	//response.reset();
    out.clear();
    out=pageContext.pushBody();
	//response.reset();
%>
