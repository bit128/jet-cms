package com.lanxin.pandora.tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

@Component
public class JsonResponse {

	public final static int RES_UNKNOW			= 0;	//未知
	public final static int RES_OK				= 1;	//成功
	public final static int RES_FAIL			= 2;	//失败
	
	private HttpServletResponse response;
	
	public JsonResponse() {}
	
	public JsonResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setContext(HttpServletResponse response) {
		this.response = response;
	}
	
	/**
	 * 生成正常结果
	 * @param result
	 * @return
	 */
	public void write(Object result) {
		if(result instanceof Integer) {
			String res = "未知";
			switch ((int) result) {
			case RES_OK:
				res = "操作成功";
				break;
			case RES_FAIL:
				res = "操作失败";
				break;
			default:
				res = "未知操作结果";
			}
			if((int) result == RES_OK) {
				printResult(RES_OK, res, "");
			} else {
				printResult((int)result, "", res);
			}
		} else {
			printResult(RES_OK, result, "");
		}
	}
	
	/**
	 * 生成结果
	 * @param code
	 * @param result
	 * @param error
	 * @return
	 */
	public void write(int code, String result, String error) {
		printResult(code, result, error);
	}

	/**
	 * 输出结果
	 * @param code
	 * @param result
	 * @param error
	 * @return
	 */
	private void printResult(int code, Object result, String error) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("code", code);
			data.put("result", result);
			data.put("error", error);
			data.put("date", getNowTime());
			String out = mapper.writeValueAsString(data);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(out);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	private String getNowTime() {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sp.format(ca.getTime());
	}
	
}
