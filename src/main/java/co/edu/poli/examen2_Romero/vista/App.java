package co.edu.poli.examen2_Romero.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {

		// ✔ Ruta corregida (IMPORTANTE)
		TabPane root = FXMLLoader.load(getClass().getResource("/formCard.fxml"));

		scene = new Scene(root);

		stage.setTitle("Gestión de Inmuebles");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	// Método para cambiar vistas si lo necesitas
	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	// ✔ Ruta corregida aquí también
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/co/edu/poli/examen2_Romero/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}
}