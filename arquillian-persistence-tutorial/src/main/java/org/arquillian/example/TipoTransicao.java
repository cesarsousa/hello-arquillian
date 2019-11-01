package org.arquillian.example;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TA_TIPO_TRANSICAO")
public class TipoTransicao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ID_TIPO_TRANSICAO")
	private Long id;

	@Basic(optional = false)
	@Column(name = "CD_TIPO_TRANSICAO")
	private String codigoTipoTransicao;

	@Basic(optional = false)
	@Column(name = "DS_TIPO_TRANSICAO")
	private String descricaoTipoTransicao;

	@Basic(optional = false)
	@Column(name = "TP_EXECUCAO")
	private String execucao;
	
	public TipoTransicao() {}
	
	public TipoTransicao(Long id, String codigoTipoTransicao, String descricaoTipoTransicao, String execucao) {
		super();
		this.id = id;
		this.codigoTipoTransicao = codigoTipoTransicao;
		this.descricaoTipoTransicao = descricaoTipoTransicao;
		this.execucao = execucao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoTipoTransicao() {
		return codigoTipoTransicao;
	}

	public void setCodigoTipoTransicao(String codigoTipoTransicao) {
		this.codigoTipoTransicao = codigoTipoTransicao;
	}

	public String getDescricaoTipoTransicao() {
		return descricaoTipoTransicao;
	}

	public void setDescricaoTipoTransicao(String descricaoTipoTransicao) {
		this.descricaoTipoTransicao = descricaoTipoTransicao;
	}

	public String getExecucao() {
		return execucao;
	}

	public void setExecucao(String execucao) {
		this.execucao = execucao;
	}

}

