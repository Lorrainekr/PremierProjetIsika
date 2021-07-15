package application;


import static javafx.scene.paint.Color.FIREBRICK;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

public class Connexion {


	GridPane gridPane = new GridPane();
	private ViewPrincipale viewPrincipale;


	public Connexion(ViewPrincipale viewPrincipale) {
		this.viewPrincipale = viewPrincipale;
		init();
	}

	private void init() {


		//Remplir la grille
		Font fontText = Font.font("Calibri", FontWeight.BOLD, 14);


		Label titre = new Label("Connecte toi pour accéder à plus d'options !");
		titre.setFont(fontText);
		titre.setTextFill(Color.web("#044E54"));
		titre.setId("titreText");

		VBox titreBox = new VBox(10);
		titreBox.getChildren().addAll(titre);
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


		//Ajout du bouton � la grille
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

		//Ajout d'une zone de texte
		Text actionTexte = new Text();
		actionTexte.setId("actionTexte");

		//Action Bouton
		btn.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {
				actionTexte.setFill(FIREBRICK);
				actionTexte.setText("Vérification en cours");

			}

		});

		gridPane.setBackground(new Background(new BackgroundFill(Color.web("#CACFE0"), null, null)));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(0, 20, 0, 20));
		gridPane.add(titreBox, 0, 0);
		gridPane.add(loginBox, 0, 3);
		gridPane.add(pswdBox, 0, 4);
		gridPane.add(hbBtn, 0, 6);
		
	}

	public void afficher() {
		Stage primaryStage = new Stage();
		Scene scene = new Scene(gridPane,400,350);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("applicationApp.css").toExternalForm());
		primaryStage.setTitle("DÉVELOPPEURS D'AVANT -  Formulaire de connexion");
		primaryStage.show();
	}

}
