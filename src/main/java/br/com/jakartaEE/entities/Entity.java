package br.com.jakartaEE.entities;

import java.io.Serializable;

public interface Entity<T> extends Serializable{

	public T getId();

}
