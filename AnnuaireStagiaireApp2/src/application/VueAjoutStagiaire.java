package application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VueAjoutStagiaire {

	private GridPane gridPane;
	private ViewPrincipale viewPrincipale;

	public VueAjoutStagiaire(ViewPrincipale viewPrincipale) {
		this.viewPrincipale = viewPrincipale;
		init();
	}

	private void init() {

		Font fontText = Font.font("Calibri", FontWeight.BOLD, 14);
		Font fontTitre = Font.font("Calibri", FontWeight.BOLD, 18);
		Text titre = new Text("Ajouter un stagiaire");
		titre.setFont(fontTitre);


		Label nameLabel = new Label("Nom");
		nameLabel.setFont(fontText);
		TextField nameText = new TextField();
		nameText.setStyle("-fx-border-color: #033131;"
				+ "-fx-border-width: 0 0 0 0;"
				+ "-fx-background-color: #ffffff;");

		HBox nameBox = new HBox(10);
		nameBox.getChildren().addAll(nameLabel, nameText);
		nameBox.setAlignment(Pos.CENTER);
		nameBox.setPadding(new Insets(10, 0, 0, 90));

		Label firstNameLabel = new Label("Prénom");
		firstNameLabel.setFont(fontText);
		TextField firstNameText = new TextField();
		firstNameText.setStyle( "-fx-border-color: #033131;"
				+ "-fx-border-width: 0 0 0 0;"
				+ "-fx-background-color: #ffffff;");

		HBox firstNameBox = new HBox(10);
		firstNameBox.getChildren().addAll(firstNameLabel, firstNameText);
		firstNameBox.setAlignment(Pos.CENTER);
		firstNameBox.setPadding(new Insets(0, 0, 0, 70));

		Label departementLabel = new Label("Département");
		departementLabel.setFont(fontText);
		TextField departementText = new TextField();
		departementText.setStyle("-fx-border-color: #033131;"
				+ "-fx-border-width: 0 0 0 0;"
				+ "-fx-background-color: #ffffff;");

		HBox departementBox = new HBox(10);
		departementBox.getChildren().addAll(departementLabel, departementText);
		departementBox.setAlignment(Pos.CENTER);
		departementBox.setPadding(new Insets(0, 0, 0, 40));

		Label promotionsLabel = new Label("Promotion");
		promotionsLabel.setFont(fontText);
		ChoiceBox<String> promochoiceBox = new ChoiceBox<String>();
		promochoiceBox.getItems().addAll(viewPrincipale.getAnnuaire().getAllPromos());
		promochoiceBox.setStyle("-fx-font-family: 'Calibri';"
				+ "-fx-font-size: 14px;");
		promochoiceBox.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), null, null)));

		HBox promoBox = new HBox(10);
		promoBox.getChildren().addAll(promotionsLabel, promochoiceBox);
		promoBox.setAlignment(Pos.CENTER);
		promoBox.setPadding(new Insets(0, 0, 0, 5));


		Label anneeLabel = new Label("Année");
		anneeLabel.setFont(fontText);
		ChoiceBox<Integer> anneechoiceBox = new ChoiceBox<Integer>();
		anneechoiceBox.getItems().addAll(viewPrincipale.getAnnuaire().getAllYears());
		anneechoiceBox.setStyle("-fx-font-family: 'Calibri';"
				+ "-fx-font-size: 14px;");
		anneechoiceBox.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), null, null)));

		HBox anneeBox = new HBox(10);
		anneeBox.getChildren().addAll(anneeLabel, anneechoiceBox);
		anneeBox.setAlignment(Pos.CENTER);
		anneeBox.setPadding(new Insets(0, 10, 0, 0));


		Button buttonConfirm = new Button("Valider");
		buttonConfirm.setFont(fontText);
		buttonConfirm.setBackground(new Background(new BackgroundFill(Color.web("#04798a"), null, null)));
		buttonConfirm.setTextFill(Color.web("#ffffff"));
		buttonConfirm.setPadding(new Insets(10, 30, 10, 30));
		buttonConfirm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

			}

		});


		HBox hbBtnConfirm = new HBox(20);
		hbBtnConfirm.setAlignment(Pos.CENTER);
		hbBtnConfirm.setMaxWidth(Double.MAX_VALUE);
		hbBtnConfirm.getChildren().add(buttonConfirm);
		hbBtnConfirm.setPadding(new Insets(0, 0, 0, 20));

		gridPane = new GridPane();
		gridPane.setMinSize(400, 400);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(15); 
		gridPane.setHgap(25);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(titre, 0, 0, 2, 1);
		GridPane.setHalignment(titre, HPos.CENTER);

		gridPane.add(nameBox, 0, 1); 
		gridPane.add(firstNameBox, 0, 2);
		gridPane.add(departementBox, 0, 3);
		gridPane.add(promoBox, 0, 4);
		gridPane.add(anneeBox, 0, 5);
		gridPane.add(hbBtnConfirm, 0, 7); 
		GridPane.setHalignment(buttonConfirm, HPos.CENTER);
		gridPane.setBackground(new Background(new BackgroundFill(Color.web("#CACFE0"), null, null)));
	}

	public void afficher() {
		Stage primaryStage = new Stage();
		Scene scene = new Scene(gridPane,400,400);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("applicationApp.css").toExternalForm());
		primaryStage.setTitle("DÉVELOPPEURS D'AVANT -  Formulaire d'Ajout d'un stagiaire");
		primaryStage.show();
	}

}
