package co.edu.poli.examen2_Romero.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import co.edu.poli.examen2_Romero.modelo.Apartamento;
import co.edu.poli.examen2_Romero.modelo.Casa;
import co.edu.poli.examen2_Romero.modelo.Inmueble;
import co.edu.poli.examen2_Romero.modelo.Propietario;
import co.edu.poli.examen2_Romero.servicios.DAOInmueble;
import co.edu.poli.examen2_Romero.servicios.DAOPropietario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.application.Platform;

public class ControlFormCard {

	@FXML
	private Button bttConsulta;
	@FXML
	private Button bttCreacion;
	@FXML
	private TextField txtNumero1;
	@FXML
	private TextField txtNumero2;
	@FXML
	private TextArea txtAreaResultado;
	@FXML
	private DatePicker datepk;
	@FXML
	private ComboBox<Propietario> cmbPropietario;
	@FXML
	private RadioButton radioCasa;
	@FXML
	private RadioButton radioApartamento;
	@FXML
	private ToggleGroup tipo;
	@FXML
	private TextField txtExtra;

	private DAOInmueble daoInmueble;
	private DAOPropietario daoPropietario;

	@FXML
	private void initialize() {
		daoInmueble = new DAOInmueble();
		daoPropietario = new DAOPropietario();

		datepk.setValue(LocalDate.now());

		try {
			List<Propietario> lista = daoPropietario.readall();
			cmbPropietario.getItems().setAll(lista);
		} catch (Exception e) {
			mostrarAlerta(e.getMessage());
		}

		txtNumero1.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validarSoloNumeros(txtNumero1);
		});

		txtNumero2.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal)
				validarSoloNumeros(txtNumero2);
		});
	}

	@FXML
	private void pressConsulta(ActionEvent event) {
		txtAreaResultado.setText("");

		if (!txtNumero1.getText().isBlank()) {
			try {
				int numero = Integer.parseInt(txtNumero1.getText());
				Inmueble i = daoInmueble.readone(numero);

				if (i != null)
					txtAreaResultado.setText(i.toString());
				else
					mostrarAlerta("No existe el inmueble");

			} catch (Exception e) {
				mostrarAlerta(e.getMessage());
			}
		} else {
			mostrarAlerta("Ingrese número de inmueble");
		}
	}

	@FXML
	private void pressCreacion(ActionEvent event) {
		String numeroTxt = txtNumero2.getText().trim();
		if (numeroTxt.isEmpty()) {
			mostrarAlerta("Ingrese el número");
			return;
		}

		String extraTxt = txtExtra.getText().trim();
		if (extraTxt.isEmpty()) {
			mostrarAlerta("Ingrese los pisos o número de piso");
			return;
		}

		int numero = Integer.parseInt(numeroTxt);
		int extra = Integer.parseInt(extraTxt);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fecha = datepk.getValue().format(formatter);

		Propietario propietario = cmbPropietario.getValue();
		if (propietario == null) {
			mostrarAlerta("Seleccione un propietario");
			return;
		}

		Inmueble nuevo;
		if (radioCasa.isSelected()) {
			nuevo = new Casa(numero, fecha, "Disponible", propietario, extra);
		} else {
			nuevo = new Apartamento(numero, fecha, "Disponible", propietario, extra);
		}

		try {
			String resultado = daoInmueble.create(nuevo);
			mostrarAlerta(resultado);

			// ✅ CORREGIDO: antes buscaba "✔" pero el DAO retorna "OK"
			if (resultado.startsWith("OK")) {
				limpiarForm();
			}

		} catch (Exception e) {
			mostrarAlerta(e.getMessage());
		}
	}

	private void mostrarAlerta(String mensaje) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Resultado");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	private void limpiarForm() {
		txtNumero2.clear();
		txtExtra.clear();
		datepk.setValue(LocalDate.now());
		cmbPropietario.setValue(null);
		radioCasa.setSelected(true);
	}

	private void validarSoloNumeros(TextField txt) {
		String texto = txt.getText().trim();
		if (!texto.isBlank()) {
			if (!texto.matches("\\d+")) {
				txtAreaResultado.setText("");
				txt.setStyle("-fx-border-color: red;");
				mostrarAlerta("Solo números permitidos");
				txt.setText("");
				Platform.runLater(() -> txt.requestFocus());
			} else {
				txt.setStyle("");
			}
		} else {
			txt.setStyle("");
		}
	}
}