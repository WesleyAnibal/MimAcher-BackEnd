package com.anibal.mimacher.item.DTO;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class ItemInput {

	@ApiModelProperty(example = "Descrição item perdido", required = true)
	@NotEmpty
	private String descricao;

	@ApiModelProperty(example = "tipo do item perdido", required = true)
	@NotEmpty
	private String tipo;

	public ItemInput() {}

	public ItemInput(String nome, String descricao, String tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
