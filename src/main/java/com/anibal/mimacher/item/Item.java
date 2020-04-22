package com.anibal.mimacher.item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.ui.Model;

@Entity
@Indexed
@Inheritance
@Table(name = "tb_item")
public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@NotEmpty
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	
	public Item() {}
	
	public Item(String descricao, String tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}

    public Item(Long id){
	    this.id = id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Field
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
	
	@Override
	public String toString() {
		
		return "Descricao: "+ this.getDescricao();
	}
	
	


}
