package com.ln.demo.common.data;

import java.util.Map;

/**
 * 分页请求类
 * 
 * @author Lining
 * @date 2017/11/7
 */
public final class PageRequest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int offset;

	private int limit;

	private String order;

	private Map<String, Object> parameters;

	public PageRequest(int offset, int limit) {
		this(offset, limit, null);
	}

	public PageRequest(int offset, int limit, Map<String, Object> parameters) {
		this(offset, limit, parameters, null);
	}

	public PageRequest(int offset, int limit, Map<String, Object> parameters, String order) {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset must not be less than zero!");
		}

		if (limit < 1) {
			throw new IllegalArgumentException("Limit must not be less than one!");
		}

		this.offset = offset;
		this.limit = limit;
		this.parameters = parameters;
		this.order = order;
	}

	public int getPage() {
		return this.offset;
	}

	public int getSize() {
		return this.limit;
	}

	public String getOrder() {
		return this.order;
	}

	public Map<String, Object> getSearch() {
		return this.parameters;
	}

	public int getOffset() {
		return this.offset;
	}

}
