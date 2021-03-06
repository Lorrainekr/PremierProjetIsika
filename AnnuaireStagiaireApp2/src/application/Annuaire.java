package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import application.ArbreBinaire.Noeud;


public class Annuaire {


	// Transforme une chaine en un objet de type Stagiaire

	private ArbreBinaire<Stagiaire> arbre;
	private List<Stagiaire> listeStagiaires;

	public Annuaire() {
		if(arbre == null) arbre = new ArbreBinaire<Stagiaire>();
		if(listeStagiaires == null) listeStagiaires = new ArrayList<Stagiaire>();
	}

	
	public void lectureFichier(String fileName) {

		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			Scanner scanner = new Scanner(fis);
			while(scanner.hasNextLine()) {

				String nom = scanner.nextLine();
				String prenom = scanner.nextLine();
				int departement = Integer.parseInt(scanner.nextLine());
				String promo = scanner.nextLine();
				int annee = Integer.parseInt(scanner.nextLine());

				// la ligne avec * => ignorée
				scanner.nextLine();

				// On reconstitue une ligne complète avec les infos 
				String ligneCompleteAvecSeparateur = nom + "*" + prenom + "*" + departement+ "*"+promo+ "*"+annee;
				Stagiaire stagiaire = fabriqueStagiaire(ligneCompleteAvecSeparateur);

				arbre.ajouterNoeud(stagiaire);

			}
			scanner.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}

		listeStagiaires = getListStagiaireDansArbreBinaire();
	}

	public List<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
	}

	// StringTokenizer qui permet de délimiter les attributs avec une *

	Stagiaire fabriqueStagiaire(String chaine){
		Stagiaire stag=null;
		StringTokenizer st = new StringTokenizer(chaine, "*");
		if(st.countTokens()==5){
			String nom = st.nextToken();
			String prenom = st.nextToken();
			int departement = Integer.parseInt(st.nextToken());
			String promo = st.nextToken(); 
			int annee = Integer.parseInt(st.nextToken());
			stag = new Stagiaire(nom,prenom,departement,promo,annee);
		}   
		return stag;
	}


	// Méthode qui récupère toutes les promos pour les mettre
	// dans la ChoiceBox EditerStagiaire

	public Set<String> getAllPromos() {
		// TODO : correction temporaire
		if(listeStagiaires.isEmpty()) listeStagiaires = getListStagiaireDansArbreBinaire();
		return listeStagiaires.stream()
				.map(stagiaire -> stagiaire.getPromo())
				.sorted()
				.collect(Collectors.toSet());
	}


	// Méthode qui récupère toutes les années pour les mettre
	// dans la ChoiceBox EditerStagiaire

	public Set<Integer> getAllYears() {
		// TODO : correction temporaire
		if(listeStagiaires.isEmpty()) listeStagiaires = getListStagiaireDansArbreBinaire();

		return listeStagiaires.stream()
				.map(stagiaire -> stagiaire.getAnnee())
				.sorted()
				.collect(Collectors.toSet());
	}

	public Set<Integer> getAllDep() {
		// TODO : correction temporaire
		if(listeStagiaires.isEmpty()) listeStagiaires = getListStagiaireDansArbreBinaire();
		return listeStagiaires.stream()
				.map(stagiaire -> stagiaire.getDepartement())
				.sorted()
				.collect(Collectors.toSet());
	}


	/**
	 * Recupere la liste dans l'arbre binaire
	 * 
	 * @return stagiaires
	 */

	public List<Stagiaire> getListStagiaireDansArbreBinaire() {
		List<Stagiaire> stagiaires = new ArrayList<>();
		if(arbre.getRacine() == null) return listeStagiaires;

		getElementsArbreBinaire(stagiaires, arbre.getRacine());
		return stagiaires;
	}


	/**
	 * return le nombre d'elements dans l'arbre binaire
	 * 
	 * @param elements
	 * @param noeud
	 */

	private void getElementsArbreBinaire(List<Stagiaire> elements, Noeud<Stagiaire> noeud) {
		if(noeud.gauche != null) {
			getElementsArbreBinaire(elements, noeud.gauche);
		}
		if(noeud.droit != null ) {
			getElementsArbreBinaire(elements, noeud.droit);
		}
		elements.add(noeud.valeur);
	}



	// ???

	private static void modifierEncodageDe(String fichier_src,String fichier_dest, String ancien_enc, String nouveau_enc) throws IOException {
		FileInputStream src =  new FileInputStream(fichier_src);
		BufferedReader r = new BufferedReader(new InputStreamReader(src, ancien_enc));

		FileOutputStream dest = new FileOutputStream(fichier_dest);
		Writer w = new BufferedWriter(new OutputStreamWriter(dest, nouveau_enc));

		String donnee;
		while ( (donnee= r.readLine()) != null) {

			w.write(donnee);
			w.flush();

		}

		w.close();
		r.close();
		//System.exit(0); => ferme la JVM
	}
	public void modifierStagiaire(Stagiaire stagiaire) {
		// Algorithme de modification de stagiaire (soit dans l'arbre soit à coder complètement)
		
	}
	public void ajouterStagiaire(Stagiaire stagiaire) {
		arbre.ajouterNoeud(stagiaire);
	}

}

