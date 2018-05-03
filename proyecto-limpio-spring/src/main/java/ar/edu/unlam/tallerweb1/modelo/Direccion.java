package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Direccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String calle;
	private String numero;
	@ManyToOne
	private Barrio barrio;
	
	public Direccion(String calle, String numero, Barrio barrio) {
		this.calle = calle;
		this.numero = numero;
		this.barrio = barrio;
	}
	
	public Direccion() {
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}
	
	
}