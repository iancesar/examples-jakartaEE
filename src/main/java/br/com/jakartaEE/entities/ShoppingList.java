package br.com.jakartaEE.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShoppingList implements br.com.jakartaEE.entities.Entity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String name;

	public ShoppingList() {
	}

	public ShoppingList(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
