package cn.tengshe789.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMsg {

	private int code;
	private String msg;

	//通用的错误码
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
	public static final CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "非法请求");
	//登录模块 5002XX
	public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效~");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空~");
	public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空~");
	public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误~");
	public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在~");
	public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误~");

	//商品模块 5003XX

	//订单模块 5004XX
	public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单为空~");
	public static CodeMsg ORDER_GOODSID_NULL = new CodeMsg(500400, "获取订单信息错误~");

	//秒杀模块 5005XX
	public static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品秒杀完了喔~");
	public static CodeMsg CHONG_FU_MIAOSHA = new CodeMsg(500501, "不能重复秒杀商品啊大兄弟！！！");
	public static final CodeMsg MIAO_SHA_FAIL = new CodeMsg(500502, "验证码引擎故障");

	private CodeMsg() {
	}

	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	//可以返回带参数的校验码
	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}


}
