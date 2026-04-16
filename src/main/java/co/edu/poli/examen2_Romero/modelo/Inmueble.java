
package co.edu.poli.examen2_Romero.modelo;

public abstract class Inmueble {

	private int numero;
	private String fechaCompra;
	private String estado;
	private Propietario propietario;

	public Inmueble(int numero, String fechaCompra, String estado, Propietario propietario) {
		this.numero = numero;
		this.fechaCompra = fechaCompra;
		this.estado = estado;
		this.propietario = propietario;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public String arrendar() {
		this.estado = "ARRENDADO";
		return "Inmueble " + numero + " ARRENDADO.";
	}

	public String vender() {
		this.estado = "VENDIDO";
		return "Inmueble " + numero + " VENDIDO.";
	}

	@Override
	public String toString() {
		return "numero=" + numero + ", fechaCompra=" + fechaCompra + ", estado=" + estado + ", propietario="
				+ propietario;
	}
}
