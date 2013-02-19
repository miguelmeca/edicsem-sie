package com.edicsem.pe.sie.beans;

import java.util.Map;

public class ReporteParams {
	private String jasperFileName;

	private Map queryParams;

	public void addQueryParam(Object key, Object value) {
		queryParams.put(key, value);
	}

	public String getJasperFileName() {
		return jasperFileName;
	}

	public void setJasperFileName(String jasperFileName) {
		this.jasperFileName = jasperFileName;
	}

	public Map getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map queryParams) {
		this.queryParams = queryParams;
	}
}
