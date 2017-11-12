package com.liang.common.util.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * description  公共方法提供类
 * @author 		周强
 * @date 		2016年1月11日 下午4:38:52
 * @version 	1.0
 * copyright  	gc technology
 */
public final class CommonUtil {

	/**
	 * 机构级别的数据字典对应的代码
	 */
	public static final String DICT_CODE_BRANCHLEVEL = "branchLevel";
	/**
	 * 消息模板的数据字典对应的代码
	 */
	public static final String DICT_CODE_TEMPLATETYPE = "templateType";
	/**
	 * 性别的数据字典对应的代码
	 */
	public static final String DICT_CODE_SEX = "sex";
	/**
	 * 项目状态对应的代码
	 */
	public static final String DICT_CODE_PROJECT_STATUS = "projectStatus";
	/**
	 * 工作经验对应的代码
	 */
	public static final String DICT_CODE_EXPERIENCE_CODE = "experienceCode";
	/**
	 * 学历的数据字典对应的代码
	 * 
	 * @author qingui
	 */
	public static final String DICT_CODE_EDUCATION = "education";
	/**
	 * 离职原因的数据字典对应的代码
	 * 
	 * @author qingui
	 */
	public static final String DICT_CODE_LEAVEREASON = "leaveReason";

	/**
	 * 招聘状态对应的代码
	 */
	public static final String DICT_CODE_INVITE_STATE = "inviteState";

	/**
	 * 需求状态的数据字典对应的代码
	 * 
	 * @author qingui
	 */
	public static final String DICT_CODE_DEMAND_TYPE = "demandType";

	/**
	 * 应聘者状态的数据字典对应的代码
	 * 
	 * @author qingui
	 */
	public static final String DICT_CODE_APPLICANT_STATUS = "applicantStatus";

	/**
	 * 客户类型的数据字典对应的代码
	 * 
	 * @author qingui
	 */
	public static final String DICT_CODE_CUSTOM_TYPE = "customType";

	/**
	 *	英語等級的数据字典对应的代码
	 */
	public static final String DICT_CODE_ENGLISHLEVEL = "englishLevel";

	/**
	 *	紧急程度的数据字典对应的代码
	 */
	public static final String DICT_CODE_URGENCYLEVEL = "urgencyLevel";

	/**
	 *	权限组级别的数据字典对应的代码
	 */
	public static final String DICT_CODE_GROUPLEVEL = "groupLevel";

	/**
	 * 月份
	 */
	public static final String[] monthArray = { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月",
			"十二月" };
	/** 当前日期-示例： 151212 */
	public static final SimpleDateFormat dateFormat_yyMMdd = new SimpleDateFormat("yyMMdd");
	/** 当前日期-示例： 151212 */
	public static final SimpleDateFormat dateFormat_yyyy_MM = new SimpleDateFormat("yyyy-MM");
	/** 当前日期-示例： 20151212 */
	public static final SimpleDateFormat dateFormat_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	/** 当前日期-示例： 20151212080808 */
	public static final SimpleDateFormat dateFormat_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	/** 当前日期-示例： 2015-12-12 */
	public static final SimpleDateFormat dateFormat_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	/** 当前日期-示例： 2015-12-12 12:12:12 */
	public static final SimpleDateFormat dateFormat_yyyy_MM_ddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 当前日期-示例： 2015-12-12 12:12 */
	public static final SimpleDateFormat dateFormat_yyyy_MM_ddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/** 手机正则 **/
	public static final String REGEX_MOBILE = "^1[3|5|7|8|9][0-9]{9}$";
	public static final String ERRORPAGE = null;

	/**
	 * description  验证字符串是否为空，为空时返回false不为空时返回true
	 * @author 		周强
	 * @date 		2016年1月11日 下午4:48:36
	 * @param 		str
	 * @return 		boolean
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static boolean hasText(Object str) {
		// 如果是null,返回false;
		if (null == str)
			return false;

		str = str.toString().trim();

		if (("".equals(str)) || ("null".equals(str)))
			return false;

		return true;
	}

	/**
	 * description  格式化手机号
	 * @author 		周强
	 * @date 		2016年1月11日 下午4:50:06
	 * @param 		str
	 * @return 		String like this 138****1438
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String getSafeMobile(Object str) {
		// 如果是null,返回false;
		if (hasText(str)) {
			String mobile = str.toString();
			if (mobile.length() == 11) {
				return mobile.substring(0, 3) + "****" + mobile.substring(7);
			}
			return "非法手机号码";
		}
		return "未知";
	}

	/**
	 * description  格式化银行卡号
	 * @author 		周强
	 * @date 		2016年1月11日 下午4:57:49
	 * @param		//要格式化的银行卡号
	 * @return  	String 格式化后的银行卡号 like this 6226 **** **** 1438
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String getSafeBankCard(Object str) {
		if (hasText(str)) {
			String cardNo = str.toString();
			if (cardNo.length() >= 12) {
				return cardNo.substring(0, 4) + " **** **** " + cardNo.substring(cardNo.length() - 4);
			} else {
				return cardNo;
			}
		}
		return "卡号为空";
	}

	/**
	 * description  格式化身份证号
	 * @author 		周强
	 * @date 		2016年1月11日 下午4:57:49
	 * @param		str 要格式化的身份证号码
	 * @return 		String 格式化后的身份证号 like this 1100 ***** ***** 1438
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String getSafeIdCard(Object str) {
		if (hasText(str)) {
			String cardNo = str.toString();
			if (cardNo.length() >= 12) {
				return cardNo.substring(0, 4) + " ***** ***** " + cardNo.substring(cardNo.length() - 4);
			} else {
				return cardNo;
			}
		}
		return "卡号为空";
	}

	/**
	 * description  转义特殊字符（如果需要返回json数据，换行等特殊字符会导致json数据换行导致解析错误）
	 * @author 		周强
	 * @date 		2016年1月11日 下午4:51:26
	 * @param 		str
	 * @return 		转义后的字符串
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String decodeText(String str) {
		String s = "";
		if (!hasText(str))
			return "";

		s = str.replace("&amp;", "&");
		s = s.replace("&lt;", "<");
		s = s.replace("&gt;", ">");
		s = s.replace("&nbsp;", " ");
		s = s.replace("&#39;", "\'");
		s = s.replace("&quot;", "\"");
		return s;
	}

	/**
	 * description  生成6位验证码
	 * @author 		周强
	 * @date 		2016年1月11日 下午4:52:46
	 * @return 		String 6位验证码
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static synchronized String getCaptcha() {
		String captcha = "";

		for (int i = 0; i < 6; i++) {
			captcha += new Random().nextInt(10);
		}
		return captcha;
	}

	/**
	 * description  获取两个日期之间相差天数
	 * @author 		周强
	 * @date 		2016年1月11日 下午5:19:41
	 * @param 		fDate
	 * @param 		oDate
	 * @return 		第二个日期-第一个日期相差天数
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static int getDateDiff(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	/**
	 * description  根据创建时间获取发布距离现在多久
	 * @author 		周强
	 * @date 		2016年1月11日 下午5:15:38
	 * @param 		createTime
	 * @return 		格式化后的时间 类似微信的发布时间 like this 刚刚、x分钟前、x小时前、昨天...
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String getPublishTime(Date createTime) {
		long time1 = createTime.getTime();

		long time2 = new Date().getTime();
		long resultTime = (time2 - time1) / 1000 / 60;
		String publishTime = "";
		if (resultTime < 10) {
			publishTime = "刚刚";
		} else if (resultTime >= 10 && resultTime < 60) {
			publishTime = resultTime / 10 + "0分钟前";
		} else if (resultTime > 60 && resultTime < 1440) {
			publishTime = resultTime / 60 + "小时前";
		} else if (resultTime > 1440 && resultTime < 2880) {
			publishTime = "昨天";
		} else {
			publishTime = dateFormat_yyyy_MM_dd.format(createTime);
		}
		return publishTime;

	}

	/**
	 * description  获得UUID，去中划线后转为大写
	 * @author 		周强
	 * @date 		2016年1月11日 下午5:17:40
	 * @return		String 格式化后的UUID
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * description  根据日期取得月份的中文名称
	 * @author 		周强
	 * @date 		2016年1月11日 下午5:18:15
	 * @param 		date 格式化的日期
	 * @return 		月份的中文名称
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String getChinaMonth(Date date) {
		if (null == date) {
			return monthArray[Integer.parseInt(new SimpleDateFormat("MM").format(new Date())) - 1];
		}
		return monthArray[Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1];
	}

	/**
	 * description  格式化数字，四舍五入固定保留2位小数
	 * @author 		周强
	 * @date 		2016年1月11日 下午5:20:13
	 * @param 		num 格式化的参数
	 * @return 		格式化后的数字 String类型
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String round2Scale(Number num) {
		return new DecimalFormat("#0.00").format(hasText(num) ? num : 0d);
	}

	/**
	 * description  将数据转换为Integer类型，null的情况下处理成0
	 * @author 		周强
	 * @date 		2016年1月11日 下午5:45:38
	 * @param 		value
	 * @return		Integer
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static Integer parseInteger(String value) {
		if (!hasText(value) || !value.matches("\\d+"))
			return 0;
		return Integer.valueOf(value);
	}

	/**
	 * description  获取当前网络ip(主要处理反向代理时无法获取真实ip的问题)
	 * @author 		周强
	 * @date 		2016年1月11日 下午5:45:58
	 * @param 		request
	 * @return 		真实ip地址
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
					return "";
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * description  根据汉字获取拼音首字母
	 * @author 		周强
	 * @date 		2016年1月11日 下午6:08:13
	 * @param 		//要转换的汉字
	 * @version 	1.0
	 * @return 		每个汉字的首字母
	 * copyright  	gc technology
	 */
	public static String getPinYinFromCN(String cn) {
		if (!hasText(cn)) {
			return "";
		}
		return LetterUtil.cn2py(cn);
	}

	/**
	 * description  将提示信息放入session中
	 * @author 		周强
	 * @date 		2016年1月13日 下午2:31:58
	 * @param 		message
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static void setMessage(String message) {
		HttpServletRequest request = getRequest();
		request.getSession().setAttribute("MESSAGE", message);
	}





	/**
	 * description  获取request
	 * @author 		zhouqiang
	 * @date 		2016年5月11日 下午7:45:49
	 * @return 		HttpServletRequest
	 * @version 	1.0
	 * copyright 	gc technology
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * description  获取response
	 * @author		zhouqiang
	 * @date 		2016年5月11日 下午7:45:49
	 * @return 		HttpServletResponse
	 * @version 	1.0
	 * copyright	gc technology
	 */
	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * description  	获取文件类型
	 * @author 			Sun xushe
	 * @date 			2016年5月5日
	 * @param 			fileName
	 * @version 		1.0
	 * copyright  		gc technology
	 * @return			String
	 */
	public static String getFileType(String fileName) {
		if (hasText(fileName) && fileName.indexOf(".") > -1 && fileName.lastIndexOf(".") < fileName.length() - 1) {
			return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		}
		return null;
	}



	/**
	 * 异步请求时，返回json数据用的方法，针对jquery.form.js上传文件时解决IE7,8,9返回值乱码问题，
	 * 
	 * @author	Sun xushe
	 * @date	2016年6月7日
	 * @param 	obj
	 */
	public static void returnJson(Object obj) {
		if (null == obj)
			return;

		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(JSONObject.toJSONString(obj));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (null != out)
				out.close();
		}
	}

	/**
	 * 把url的地址的查询值转化为map形式
	 * 
	 * @author		Sun xushe
	 * @date		2016年6月12日
	 * @param 		url
	 * @return
	 */
	public static Map<String, String> putUrlSearchToMap(String url) {
		Map<String, String> result = new HashMap<String, String>();
		if (hasText(url)) {
			String[] keyValue;
			if (url.lastIndexOf("?") > 0)
				keyValue = url.split("\\?")[1].split("&");
			else keyValue = url.split("&");
			int length = keyValue.length;
			for (int i = 0; i < length; i++) {
				String[] _arr = keyValue[i].split("=");
				if (_arr.length != 2)
					continue;
				result.put(_arr[0], _arr[1]);
			}

		}
		return result;
	}

	/**
	 * 清除null的字符串，防止页面出现null的情况
	 * 
	 * @author		Sun xushe
	 * @date		2016年7月4日
	 * @return		String
	 */
	public static String wipeNull(String str) {
		if (hasText(str))
			return str;
		return "";
	}

	/**
	 * 获取项目根路径
	 * 
	 * @author		Sun xushe
	 * @date		2016年7月28日
	 * @param		request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		if (null == request)
			request = getRequest();

		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";

	}

	/**
	 * 获取项目根路径
	 * 
	 * @author		Sun xushe
	 * @date		2016年7月28日
	 * @param		//request
	 * @return
	 */
	public static String getBasePath() {

		HttpServletRequest request = getRequest();

		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";

	}

	/**
	 * 转long类型的，换失败的默认值为0
	 * 
	 * @author		Sun xushe
	 * @date		2016年9月2日
	 * @param 		longStr
	 * @param 		//defValue
	 * @return
	 */
	public static Long parseLong(String longStr) {
		return parseLong(longStr, 0);
	}

	/**
	 * 转long类型的，可以传一个转换失败的默认值
	 * 
	 * @author		Sun xushe
	 * @date		2016年9月2日
	 * @param 		longStr
	 * @param 		defValue
	 * @return
	 */
	public static Long parseLong(String longStr, long defValue) {

		if (null == longStr || !longStr.matches("\\d+"))
			return defValue;

		return Long.valueOf(longStr);
	}

	/**
	 * 传入字段field，和list,返回list 根据field的分组Map
	 * 
	 * @author		Sun xushe
	 * @date		2016年9月7日
	 * @param 		field
	 * @param 		list
	 * @return
	 */
	public static <E> Map<String, List<E>> gruopListToMap(String field, List<E> list) {

		if (null == list || list.isEmpty())
			return null;

		Map<String, List<E>> mm = new HashMap<String, List<E>>();

		Method getMethod;
		try {
			getMethod = Class.forName(list.get(0).getClass().getName())
					.getDeclaredMethod("get" + upCaseFirstChar(field));
			getMethod.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<E> tTist;
		String key;
		for (E e : list) {
			try {
				key = getMethod.invoke(e).toString();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			tTist = mm.get(key);
			if (null == tTist)
				tTist = new ArrayList<E>();
			tTist.add(e);
			mm.put(key, tTist);
		}
		return mm;
	}

	/**
	 * 首字母大写
	 * 
	 * @author		Sun xushe
	 * @date		2016年9月7日
	 * @param 		str
	 * @return
	 */
	public static String upCaseFirstChar(String str) {
		if (!hasText(str))
			return null;
		str = (str.charAt(0) + "").toUpperCase() + str.substring(1, str.length());

		return str;
	}

	/**
	 * description  将数据转换为Integer类型，null的情况下处理成0
	 * @author 		周强
	 * @date 		2016年1月11日 下午5:45:38
	 * @param 		value
	 * @return		Integer
	 * @version 	1.0
	 * copyright  	gc technology
	 */
	public static Integer parseInteger(Object value) {
		try {
			return Integer.valueOf(value.toString());
		} catch (Exception e) {
			return 0;
		}
	}
	
}
