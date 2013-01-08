package com.edicsem.pe.sie.beans;

import java.io.Serializable;

public class MenuDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipodeMenu;
	private String nombreMenu;
	private String urlMenu;
	private String nombreActionListener;
	public MenuDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getTipodeMenu() {
		return tipodeMenu;
	}

	public void setTipodeMenu(String tipodeMenu) {
		this.tipodeMenu = tipodeMenu;
	}

	public String getNombreMenu() {
		return nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getUrlMenu() {
		return urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

	public String getNombreActionListener() {
		return nombreActionListener;
	}

	public void setNombreActionListener(String nombreActionListener) {
		this.nombreActionListener = nombreActionListener;
	}

	
}
