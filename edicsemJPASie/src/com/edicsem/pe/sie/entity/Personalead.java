//package com.edicsem.pe.sie.entity;
//
//import java.io.Serializable;
//import javax.persistence.*;
//
//import com.edicsem.pe.sie.util.constants.Constants;
//
//
///**
// * The persistent class for the personalead database table.
// * 
// */
//@Entity
//@Table(name="personalead", schema = Constants.ESQUEMA_SIE_POSTGRE)
//public class Personalead implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@SequenceGenerator(name="PERSONALEAD_IDS_GENERATOR", sequenceName="SIE.PERSONALEAD_IDS_SEQ", initialValue=1, allocationSize =1)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONALEAD_IDS_GENERATOR")
//	private Integer ids;
//	
//	/**
//	 * @return the ids
//	 */
//	public Integer getIds() {
//		return ids;
//	}
//
//	/**
//	 * @param ids the ids to set
//	 */
//	public void setIds(Integer ids) {
//		this.ids = ids;
//	}
//
//	private Integer id;
//
//	private String nombre;
//
//    public Personalead() {
//    }
//
//	public Integer getId() {
//		return this.id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getNombre() {
//		return this.nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//}