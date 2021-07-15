package application;
import static javafx.scene.paint.Color.FIREBRICK;

import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewGlobalConnexion {

	public void start(Stage primaryStage) {

		Font fontText = Font.font("Calibri", FontWeight.BOLD, 14);
		Font fontTitre = Font.font("Calibri", FontWeight.BOLD, 18);


		//Remplir la grille
		Label titre = new Label("Bienvenue sur Développeurs d'avant !".toUpperCase());
		titre.setFont(fontTitre);
		titre.setTextFill(Color.web("#044E54"));
		Label titre2 = new Label("Connecte toi pour accéder à plus d'options !");
		titre2.setFont(fontText);
		titre2.setTextFill(Color.web("#044E54"));


		//Utilisé dans le fichier css pour identifier le noeud
		titre.setId("titreText");
		VBox titreBox = new VBox(10);
		titreBox.getChildren().addAll(titre, titre2);
		titreBox.setAlignment(Pos.CENTER);
		titreBox.setPadding(new Insets(0, 0, 0, 0));


		Label login = new Label("Identifiant");
		login.setFont(fontText);
		login.setTextFill(Color.web("#033131"));
		TextField loginTextField = new TextField();
		loginTextField.setStyle("-fx-text-fill: #cbcbcb;"
				+ "-fx-border-color: #033131;"
				+ "-fx-border-width: 0 0 0 0;"
				+ "-fx-background-color: #ffffff;");
		loginTextField.setMaxWidth(Double.MAX_VALUE);
		loginTextField.setPadding(new Insets(5, 30, 2, 5));
		HBox loginBox = new HBox(26);
		loginBox.getChildren().addAll(login, loginTextField);
		loginBox.setAlignment(Pos.CENTER);


		Label pswd = new Label("Mot de passe");
		pswd.setFont(fontText);
		PasswordField passwordField = new PasswordField();
		passwordField.setStyle("-fx-text-fill: #cbcbcb;"
				+ "-fx-border-color: #033131;"
				+ "-fx-border-width: 0 0 0 0;"
				+ "-fx-background-color: #ffffff;");
		passwordField.setMaxWidth(Double.MAX_VALUE);
		passwordField.setPadding(new Insets(5, 30, 2, 5));
		HBox pswdBox = new HBox(10);
		pswdBox.getChildren().addAll(pswd, passwordField);
		pswdBox.setAlignment(Pos.CENTER);


		//Ajout du bouton à la grille
		Button btn = new Button("Connexion".toUpperCase());
		btn.setFont(fontText);
		btn.setMaxWidth(Double.MAX_VALUE);
		btn.setBackground(new Background(new BackgroundFill(Color.web("#04798a"), null, null)));
		btn.setTextFill(Color.web("#ffffff"));
		btn.setPadding(new Insets(10, 30, 10, 30));
		btn.setStyle("-fx-background-radius: 20 20 20 20;");
		btn.setStyle("-fx-border-radius: 20 20 20 20;");

		HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.getChildren().add(btn);
		hbBtn.setPadding(new Insets(30, 0, 0, 0));


		//Action Bouton
		btn.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				ViewPrincipale viewPrincipale = new ViewPrincipale();
				viewPrincipale.start(primaryStage);
			}

		});


		//créer une grille (GridPane Layout)

		GridPane grilleConnexion = new GridPane();
		grilleConnexion.setBackground(new Background(new BackgroundFill(Color.web("#CACFE0"), null, null)));
		grilleConnexion.setAlignment(Pos.CENTER);
		grilleConnexion.setHgap(10);
		grilleConnexion.setVgap(10);
		grilleConnexion.setPadding(new Insets(0, 20, 0, 20));
		grilleConnexion.add(titreBox, 0, 0);
		grilleConnexion.add(loginBox, 0, 3);
		grilleConnexion.add(pswdBox, 0, 4);
		grilleConnexion.add(hbBtn, 0, 6);
		Scene scene = new Scene(grilleConnexion, 400, 350);

		scene.getStylesheets().add(getClass().getResource("applicationApp.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setTitle("DÉVELOPPEURS D'AVANT - l'annuaire des étudiants ISIKA");
		primaryStage.show();
	}
}
