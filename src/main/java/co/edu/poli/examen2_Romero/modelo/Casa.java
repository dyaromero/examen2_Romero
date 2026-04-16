
package co.edu.poli.examen2_Romero.modelo;

public class Casa extends Inmueble {

	private int cantidadPisos;

	public Casa(int numero, String fechaCompra, String estado, Propietario propietario, int cantidadPisos) {
		super(numero, fechaCompra, estado, propietario);
		this.cantidadPisos = cantidadPisos;
	}

	public int getCantidadPisos() {
		return cantidadPisos;
	}

	public void setCantidadPisos(int cantidadPisos) {
		this.cantidadPisos = cantidadPisos;
	}

	@Override
	public String toString() {
		return super.toString() + ", Casa{" + "cantidadPisos=" + cantidadPisos + '}';
	}
}
