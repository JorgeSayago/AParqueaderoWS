package ec.edu.ups.ppw.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LugarParqueo {

	@Id
	@Column(name="lug_codigo")
	private int codigo;
	
	@Column(name="lug_numeroSitio")
	private int numeroSitio;
	
	@Column(name="lug_piso")
	private int piso;
	
	@Column(name="lug_estado")
	private boolean estado;

	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNumeroSitio() {
		return numeroSitio;
	}

	public void setNumeroSitio(int numeroSitio) {
		this.numeroSitio = numeroSitio;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "LugarParqueo [codigo=" + codigo + ", numeroSitio=" + numeroSitio + ", piso=" + piso + ", estado="
				+ estado + "]";
	}
}
