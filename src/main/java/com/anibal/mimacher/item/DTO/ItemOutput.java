package com.anibal.mimacher.item.DTO;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "itemOutput")
public class ItemOutput implements Serializable {
	
	 	@ApiModelProperty(example = "1")
	    private Long id;

	    @ApiModelProperty(example = "item perdido")
	    private String descricao;

	    @ApiModelProperty(example = "teste")
	    private String tipo;
	    
	    @ApiModelProperty(example = "teste")
	    private Float score;
	    
	    public ItemOutput() {
		
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public Float getScore() {
			return score;
		}

		public void setScore(Float socore) {
			this.score = socore;
		}
		
		
	    
	    
}
