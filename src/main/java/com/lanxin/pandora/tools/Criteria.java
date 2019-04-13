package com.lanxin.pandora.tools;

public class Criteria {
	
	private String select = "*";
	
	private String union = "";

	private String condition = "";
	
	private String order = "";
	
	private String group = "";
	
	private int offset = 0;
	
	private int limit = 1;
	
	/**
	 * 通过键值添加查询条件
	 * @param key 键
	 * @param value 值
	 */
	public void add(String key, String value) {
		addCondition(key + "='" + addSlashes(value) +"'");
	}
	
	/**
	 * 通过键值添加查询条件
	 * @param key 键
	 * @param value 值
	 * @param symbol 赋值符
	 * @param operator 操作符
	 */
	public void add(String key, String value, String symbol, String operator) {
		addCondition(key + " " + symbol + " '" + addSlashes(value) +"'", operator);
	}
	
	/**
	 * 添加自定义查询条件
	 * @param condition 条件
	 */
	public void addCondition(String condition) {
		if (! this.condition.equals("")) {
			this.condition = "(" + this.condition + ") AND (" + condition + ")";
		} else {
			this.condition = condition;
		}
	}
	
	/**
	 * 添加自定义查询条件
	 * @param condition 条件
	 * @param operator 操作符
	 */
	public void addCondition(String condition, String operator){
		if (! this.condition.equals("")) {
			this.condition = "(" + this.condition + ")" + operator + "(" + condition + ")";
		} else {
			this.condition = condition;
		}
	}
	
	/**
	 * 联合查询体
	 * @param table_name 联表名称
	 * @param foreign 外键
	 * @param type 联合类型 inner | left | right
	 */
	public void addUnion(String table_name, String foreign, String type) {
		this.union = type + " join " + table_name + " on " + foreign;
	}
	
	/**
	 * 过滤SQL注入字符
	 * @param str
	 * @return
	 */
	private String addSlashes(String str) {
		str = str.replace("\'", "\\'");
		str = str.replace("\"", "\\\"");
		str = str.replace("\\", "\\\\");
		return str;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getUnion() {
		return union;
	}

	public void setUnion(String union) {
		this.union = union;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
