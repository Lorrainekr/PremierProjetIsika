package application;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import application.ArbreBinaire.Noeud;

public class ArbreStagiaireJJL {


	private static final String OUTPUT_PATH = "C:/GitHubRepo/IsikaProjet1_v2/AnnuaireStagiaireApp2/src/application/fichiers/";
	private ArbreBinaire <Stagiaire> arbre = new ArbreBinaire <Stagiaire>();

	public void creationFichierBin(Collection<Stagiaire> listStagiaire) {

		// Ecriture fichier Bin
		File f = new File(OUTPUT_PATH + "STAGIAIRES.bin");

		try (RandomAccessFile fichier = new RandomAccessFile(f, "rw"))
		{

			for (Stagiaire stagiaire : listStagiaire) {

				// nom
				char[] tabNom = Arrays.copyOf(stagiaire.getNom().toCharArray(), 30);
				for (char c : tabNom) {
					fichier.writeChar(c);
				}

				// prenom
				char[] tabPrenom = Arrays.copyOf(stagiaire.getPrenom().toCharArray(),30);
				for (char c : tabPrenom) {
					fichier.writeChar(c);
				}

				// dept en entier
				fichier.writeInt(stagiaire.getDepartement());

				// promo
				char[] tabClasse = Arrays.copyOf(stagiaire.getPromo().toCharArray(),30);
				for (char c : tabClasse) {
					fichier.writeChar(c);
				}

				// année en entier
				fichier.writeInt(stagiaire.getAnnee());

				// les index du noeud et de ses fils
				fichier.writeInt(stagiaire.getId());
				fichier.writeInt(-1);
				fichier.writeInt(-1);


				/*			//Recuperation reference du stagiaire
				if (arbre.getRacine() == null) {
					Stagiaire monStagiaire = stagiaire;
					fichier.writeInt(monStagiaire.getId());
					fichier.writeInt(-1);
					fichier.writeInt(-1);
				}
				else {
					Stagiaire monStagiaire = arbre.rechercher(arbre.getRacine(), stagiaire);
					fichier.writeInt(monStagiaire.getId());

					Stagiaire noeudDroit = arbre.rechercherRecursif(arbre.getRacine(), monStagiaire).droit.valeur;
					if (noeudDroit == null) {
						fichier.writeInt(-1);
					}
					else {
						fichier.writeInt(noeudDroit.getId());
					}

					Stagiaire noeudGauche = arbre.rechercherRecursif(arbre.getRacine(), monStagiaire).gauche.valeur;
					if (noeudGauche == null) {
						fichier.writeInt(-1);
					}
					else {
						fichier.writeInt(noeudGauche.getId());
					}

				}*/

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());

		} // fin méthode écrireFichierBin

	}


	// méthode de relecture fichie binaire
	public  List<Stagiaire> lireFichierBin(File f) {
		List<Stagiaire> listBin = new ArrayList<Stagiaire>();

		StringBuilder buffer = new StringBuilder();
		try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {

			for (int j =0; j<= raf.length(); j++) {

				buffer.setLength(0);
				for (int i = 0; i < 30; i++) {
					buffer.append(raf.readChar());
				}
				String nom = buffer.toString().trim();

				buffer.setLength(0);
				for (int i = 0; i <  30; i++) {
					buffer.append(raf.readChar());
				}
				String prenom = buffer.toString().trim();

				int departement = raf.readInt();

				buffer.setLength(0);
				for (int i = 0; i < 30; i++) {
					buffer.append(raf.readChar());
				}
				String promo = buffer.toString().trim();

				int annee = raf.readInt();
				int ref = raf.readInt();
				int refDroite = raf.readInt();
				int refGauche = raf.readInt();
				Stagiaire stagiaire = new Stagiaire(ref, nom, prenom, departement, promo, annee);
				listBin.add(stagiaire);
			}
			raf.seek(256);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return listBin;

	}

}