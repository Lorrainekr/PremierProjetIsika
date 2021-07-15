package application;


import java.io.File;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class ViewPrincipale {


	private String fileName = "C:/GitHubRepo/IsikaProjet1_v2/STAGIAIRES.DON";

	private Annuaire annuaire;
	private VueAjoutStagiaire vueAjoutStagiaire;
	private EditerStagiaire editerStagiaire;
	private Connexion connexionStagiaire;



	public ViewPrincipale() {
		initAnnuaire();
	}

	private void initAnnuaire() {
		annuaire = new Annuaire();

		ArbreStagiaireJJL arbreBinaireJJL = new ArbreStagiaireJJL();
		File f = new File("C:/GitHubRepo/IsikaProjet1_v2/AnnuaireStagiaireApp2/src/application/fichiers/STAGIAIRES.bin");

		if (f.exists()) {
			List<Stagiaire> list = arbreBinaireJJL.lireFichierBin(f);
			list.forEach(stagiaire -> annuaire.ajouterStagiaire(stagiaire));
		} else {
			annuaire.lectureFichier(fileName);
			arbreBinaireJJL.creationFichierBin(annuaire.getListeStagiaires());
		}

	}

	public void start(Stage primaryStage) {

		Font fontText = Font.font("Calibri", FontWeight.BOLD, 14);
		Font fontTitre = Font.font("Calibri", FontWeight.BOLD, 18);
		Background backgroundButton = new Background(new BackgroundFill(Color.web("#04798a"), null, null));

		// Création d'un menu
		MenuBar menuBarApp = new MenuBar();

		// menus
		Menu ouvrirMenu = new Menu("Ouvrir");
		ouvrirMenu.setStyle("-fx-text-fill: #cbcbcb;"
				+ "-fx-font-family: 'Calibri';"
				+ "-fx-font-size: 14px;"
				+ "-fx-font-weight: bold;");

		Menu ajouterMenu = new Menu("Ajouter");
		ajouterMenu.setStyle("-fx-text-fill: #cbcbcb;"
				+ "-fx-font-family: 'Calibri';"
				+ "-fx-font-size: 14px;"
				+ "-fx-font-weight: bold;");

		Menu editerMenu = new Menu("Editer");
		editerMenu.setStyle("-fx-text-fill: #cbcbcb;"
				+ "-fx-font-family: 'Calibri';"
				+ "-fx-font-size: 14px;"
				+ "-fx-font-weight: bold;");

		Menu aideMenu = new Menu("Aide");
		aideMenu.setStyle("-fx-text-fill: #cbcbcb;"
				+ "-fx-font-family: 'Calibri';"
				+ "-fx-font-size: 14px;"
				+ "-fx-font-weight: bold;");

		//MenuItem download
		MenuItem downloadItem = new MenuItem("Télécharger la documentation");
		aideMenu.getItems().add(downloadItem);
		downloadItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String filename = OuvrirPDF.class.getResource("/application/fichiers/Manuel_d'utilisation_annuaire.pdf").getFile();
				OuvrirPDF pdf = new OuvrirPDF();
				pdf.open(filename);
			}
		});

		MenuItem ajouterStagiaireMenuItem = new MenuItem("Ajouter un Stagiaire");
		ajouterMenu.getItems().add(ajouterStagiaireMenuItem);
		ajouterStagiaireMenuItem.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent arg0) {
				afficherVueAjoutStagiaire();
			}

		});

		MenuItem editerStagiaireMenuItem = new MenuItem("Editer un Stagiaire");
		editerMenu.getItems().add(editerStagiaireMenuItem);


		// ajout de tous les menus
		menuBarApp.getMenus().addAll(ouvrirMenu, ajouterMenu, editerMenu, aideMenu);
		menuBarApp.setBackground(new Background(new BackgroundFill(Color.web("#f0f4f8"), null, null)));
		BorderPane root = new BorderPane();
		root.setTop(menuBarApp);

		//Button et hbox pour connexion 
		Button buttonConnexion = new Button("Connexion");
		buttonConnexion.setFont(fontText);
		buttonConnexion.setBackground(backgroundButton);
		buttonConnexion.setTextFill(Color.web("#ffffff"));
		HBox btnsBox = new HBox(10);
		buttonConnexion.setAlignment(Pos.BASELINE_LEFT);
		btnsBox.getChildren().add(buttonConnexion);
		buttonConnexion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				afficherVueConnexion();
			}

		});

		// Recherche Avancée

		// colonne de gauche et de droite pour le menu globale
		HBox itemGauche = new HBox();
		itemGauche.getChildren().add(root);
		itemGauche.setAlignment(Pos.BASELINE_LEFT);
		HBox itemDroite = new HBox();
		itemDroite.setAlignment(Pos.BASELINE_RIGHT);
		itemDroite.getChildren().add(btnsBox);
		itemDroite.setPadding(new Insets(0, 0, 0, 340));

		GridPane gridMenu = new GridPane();
		gridMenu.add(itemGauche, 0, 0);
		gridMenu.add(itemDroite, 3, 0);
		gridMenu.setVgap(5); 
		gridMenu.setHgap(5);
		gridMenu.setAlignment(Pos.BASELINE_LEFT);
		gridMenu.setPadding(new Insets(5, 0, 0, 0));

		Label nameLabel = new Label("Nom-Prénom");
		nameLabel.setFont(fontText);
		nameLabel.setPadding(new Insets(3, 0, 3, 0));
		TextField nameText = new TextField();
		nameText.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), null, null)));

		// ajout de la HBox
		HBox searchBar = new HBox(10);
		searchBar.getChildren().addAll(nameLabel, nameText);

		// ajout de la VBox
		VBox rootSearch = new VBox();
		rootSearch.getChildren().addAll(searchBar);

		// box des départements
		ChoiceBox departementchoiceBox = new ChoiceBox();
		departementchoiceBox.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), null, null)));
		departementchoiceBox.getItems().addAll(getAnnuaire().getAllDep());
		Button departementBtn = new Button("Départements", departementchoiceBox);
		departementBtn.setBackground(backgroundButton);
		departementBtn.setTextFill(Color.web("#ffffff"));
		departementBtn.setFont(fontText);
		departementBtn.setPadding(new Insets(4, 20, 4, 20));
		HBox departementBox = new HBox(10);
		departementBox.getChildren().add(departementBtn);

		// box des années
		ChoiceBox<Integer> anneechoiceBox = new ChoiceBox();
		anneechoiceBox.getItems().addAll(getAnnuaire().getAllYears());
		anneechoiceBox.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), null, null)));
		Button yearBtn = new Button("Année", anneechoiceBox);
		yearBtn.setBackground(backgroundButton);
		yearBtn.setTextFill(Color.web("#ffffff"));
		yearBtn.setFont(fontText);
		yearBtn.setPadding(new Insets(4, 20, 4, 20));
		HBox yearBox = new HBox(10);
		yearBox.getChildren().add(yearBtn);

		// box des promotions
		ChoiceBox<String> promotionschoiceBox = new ChoiceBox();
		promotionschoiceBox.getItems().addAll(getAnnuaire().getAllPromos());
		promotionschoiceBox.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), null, null)));
		Button promotionBtn = new Button("Promotions", promotionschoiceBox);
		promotionBtn.setBackground(backgroundButton);
		promotionBtn.setTextFill(Color.web("#ffffff"));
		promotionBtn.setFont(fontText);
		promotionBtn.setPadding(new Insets(4, 20, 4, 20));
		HBox promotionBox = new HBox(10);
		promotionBox.getChildren().add(promotionBtn);

		// Ajout des boutons à la grille
		Button validateBtn = new Button("Valider");
		validateBtn.setBackground(backgroundButton);
		validateBtn.setTextFill(Color.web("#ffffff"));
		validateBtn.setAlignment(Pos.BASELINE_RIGHT);
		validateBtn.setPadding(new Insets(9, 20, 9, 20));
		validateBtn.setFont(fontText);
		HBox validateBox = new HBox(10);
		validateBox.getChildren().add(validateBtn);
		validateBox.setPadding(new Insets(0, 0, 0, 20));

		// Hbox des comboBox
		HBox itemsChoiceBox = new HBox(10);
		itemsChoiceBox.getChildren().addAll(departementBox, yearBox, promotionBox, validateBox);

		GridPane gridPaneSearch = new GridPane();
		gridPaneSearch.setHgap(10);
		gridPaneSearch.setVgap(10);
		gridPaneSearch.setPadding(new Insets(20, 20, 20, 20));
		gridPaneSearch.add(searchBar, 0, 0);
		gridPaneSearch.add(itemsChoiceBox, 0, 1);
		gridPaneSearch.setBackground(new Background(new BackgroundFill(Color.web("#CACFE0"), null, null)));


		// Création du tableau 

		TableView<Stagiaire> tableauxList = new TableView();
		TableColumn<Stagiaire, String> NomCol = new TableColumn<>("Nom");
		TableColumn<Stagiaire, String> PrenomCol = new TableColumn<>("Prenom");
		TableColumn<Stagiaire, String> DepartementCol = new TableColumn<>("Département");
		TableColumn<Stagiaire, String> PromotionCol = new TableColumn<>("Promotion");
		TableColumn<Stagiaire, String> AnneeCol = new TableColumn<>("Année");

		tableauxList.setStyle("-fx-text-fill: #cbcbcb;"
				+ "-fx-border-color: #033131;"
				+ "-fx-border-width: 0 0 1 0;"
				+ "-fx-background-color: transparent;");

		NomCol.prefWidthProperty().bind(tableauxList.widthProperty().divide(5));
		PrenomCol.prefWidthProperty().bind(tableauxList.widthProperty().divide(5));
		DepartementCol.prefWidthProperty().bind(tableauxList.widthProperty().divide(5));
		PromotionCol.prefWidthProperty().bind(tableauxList.widthProperty().divide(5));
		AnneeCol.prefWidthProperty().bind(tableauxList.widthProperty().divide(5));

		NomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		PrenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		DepartementCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		PromotionCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));
		AnneeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));


		tableauxList.getColumns().addAll(NomCol, PrenomCol, DepartementCol, PromotionCol, AnneeCol);

		editerMenu.setDisable(true);
		tableauxList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(tableauxList.getSelectionModel().getSelectedItem() == null) {
					editerMenu.setDisable(true);
				} else {
					editerMenu.setDisable(false);
				}
			}
		});
		editerStagiaireMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stagiaire selectedLine = tableauxList.getSelectionModel().getSelectedItem();
				afficherVueEditerStagiaire(selectedLine);
			}
		});

		tableauxList.setMaxWidth(660);
		tableauxList.setMinWidth(660);

		List<Stagiaire> listStagiaireDansArbreBinaire = annuaire.getListStagiaireDansArbreBinaire();
		tableauxList.setItems(FXCollections.observableList(listStagiaireDansArbreBinaire));

		Button selectAllBtn = new Button("Sélectionner tout");
		HBox selectAllBoxBtn = new HBox(5);
		selectAllBoxBtn.getChildren().add(selectAllBtn);
		selectAllBtn.setBackground(new Background(new BackgroundFill(Color.web("#04798a"), null, null)));
		selectAllBtn.setFont(fontText);
		selectAllBtn.setTextFill(Color.web("#ffffff"));
		selectAllBtn.setPadding(new Insets(10, 30, 10, 30));

		GridPane topBtns = new GridPane();
		topBtns.add(selectAllBoxBtn, 0, 0);
		topBtns.setPadding(new Insets(10, 0, 0, 20));

		HBox tableauxBox = new HBox();
		tableauxBox.getChildren().add(tableauxList);
		tableauxBox.setPadding(new Insets(10 ,10 ,10 ,20));
		tableauxBox.setMinWidth(680);

		Button printBtn = new Button("Imprimer");
		printBtn.setBackground(new Background(new BackgroundFill(Color.web("#04798a"), null, null)));
		printBtn.setTextFill(Color.web("#ffffff"));
		printBtn.setPadding(new Insets(10, 30, 10, 30));
		printBtn.setFont(fontText);
		HBox printBoxBtn = new HBox(5);
		printBoxBtn.getChildren().add(printBtn);
		printBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					FichierImpression.createPdf(tableauxList.getItems());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
		});


		Button deleteBtn = new Button("Supprimer");
		deleteBtn.setBackground(new Background(new BackgroundFill(Color.web("#04798a"), null, null)));
		deleteBtn.setTextFill(Color.web("#ffffff"));
		deleteBtn.setPadding(new Insets(10, 30, 10, 30));
		deleteBtn.setFont(fontText);
		HBox deleteBoxBtn = new HBox(5);
		deleteBoxBtn.getChildren().add(deleteBtn);


		Button editBtn = new Button("Editer");
		editBtn.setBackground(new Background(new BackgroundFill(Color.web("#04798a"), null, null)));
		editBtn.setTextFill(Color.web("#ffffff"));
		editBtn.setPadding(new Insets(10, 30, 10, 30));
		editBtn.setFont(fontText);
		HBox editBoxBtn = new HBox(5);
		editBoxBtn.getChildren().add(editBtn);

		//		tableauxList.setOnMouseExited(new EventHandler<MouseEvent>() {
		//
		//			@Override
		//			public void handle(MouseEvent arg0) {
		//				tableauxList.getSelectionModel().clearSelection();
		//			}
		//		});
		//		editBtn.setDisable(true);
		tableauxList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {

				if(arg0.getButton().equals(MouseButton.SECONDARY)) {
					tableauxList.getSelectionModel().clearSelection();
					editBtn.setDisable(false);
					editerMenu.setDisable(false);
				} else {
					if(tableauxList.getSelectionModel().getSelectedItem() == null) {
						editBtn.setDisable(true);
						editerMenu.setDisable(true);
					} else {
						editBtn.setDisable(false);
						editerMenu.setDisable(false);
					}
				}
			}
		});


		editBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Stagiaire selectedLine = tableauxList.getSelectionModel().getSelectedItem();
				afficherVueEditerStagiaire(selectedLine);
			}

		});

		Button saveBtn = new Button("Sauvegarder");
		saveBtn.setBackground(new Background(new BackgroundFill(Color.web("#04798a"), null, null)));
		saveBtn.setTextFill(Color.web("#ffffff"));
		saveBtn.setPadding(new Insets(10, 30, 10, 30));
		saveBtn.setFont(fontText);
		HBox saveBoxBtn = new HBox(5);
		saveBoxBtn.getChildren().add(saveBtn);
		saveBoxBtn.setPadding(new Insets(0 ,0 ,0 ,170));
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				//			AnnuaireFileWriter annuaireFileWriter = new AnnuaireFileWriter();
				//		annuaireFileWriter.getListAnnuaireBinaire(listStagiaireDansArbreBinaire);



			}
		});

		GridPane bottomBtns = new GridPane();
		bottomBtns.add(printBoxBtn, 0, 0);
		bottomBtns.add(deleteBoxBtn, 1, 0);
		bottomBtns.add(editBoxBtn, 2, 0);
		bottomBtns.add(saveBoxBtn, 5, 0);
		bottomBtns.setPadding(new Insets(10, 0, 0, 20));
		bottomBtns.setVgap(5);
		bottomBtns.setHgap(5);
		bottomBtns.setBackground(new Background(new BackgroundFill(Color.web("#f0f4f8"), null, null)));


		VBox canvas = new VBox();
		canvas.setSpacing(5); 
		canvas.getChildren().addAll(gridMenu, gridPaneSearch, topBtns, tableauxBox,bottomBtns);
		canvas.setBackground(new Background(new BackgroundFill(Color.web("#f0f4f8"), null, null)));
		canvas.setPadding(new Insets(0, 0, 40, 0));

		Scene scene = new Scene(canvas, 700, 550);
		scene.getStylesheets().add(getClass().getResource("applicationApp.css").toExternalForm());
		primaryStage.setTitle("DÉVELOPPEURS D'AVANT - l'annuaire des étudiants Isika");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Annuaire getAnnuaire() {
		return annuaire;
	}

	public void editerStagiaire(Stagiaire stagiaire) {
		annuaire.modifierStagiaire(stagiaire);

	}

	private void afficherVueEditerStagiaire(Stagiaire stagiaire) {
		editerStagiaire = new EditerStagiaire(this, stagiaire);
		editerStagiaire.afficher();
	}

	private void afficherVueAjoutStagiaire() {
		vueAjoutStagiaire = new VueAjoutStagiaire(this);
		vueAjoutStagiaire.afficher();
	}

	private void afficherVueConnexion() {
		connexionStagiaire = new Connexion(this);
		connexionStagiaire.afficher();
	}
}
