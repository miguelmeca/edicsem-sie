package com.edicsem.pe.sie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_pago_vendedor database table.
 * 
 */
@Entity
@Table(name="tb_pago_vendedor", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class PagoVendedorSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PAGO_VENDEDOR_IDTBPAGOVENDEDOR_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PAGO_VENDEDOR_IDTBPAGOVENDEDOR_GENERATOR")
	@Column(name="idtb_pago_vendedor")
	private Integer idtbPagoVendedor;

    public PagoVendedorSie() {
    }

	public Integer getIdtbPagoVendedor() {
		return this.idtbPagoVendedor;
	}

	public void setIdtbPagoVendedor(Integer idtbPagoVendedor) {
		this.idtbPagoVendedor = idtbPagoVendedor;
	}

}