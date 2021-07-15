package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FichierImpression {
	  public static final String DEST = "results/tables/fichierImpression.pdf";
	    
	    public static void createPdf(List<Stagiaire> listeFiltree) throws IOException, DocumentException {
	    	
	    	File file = new File(DEST);
		     file.getParentFile().mkdirs();// creation de dossier
		    
	    	Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(DEST));
	        
	        document.open();
	        
	        
	        document.add(new Phrase("Liste générée le " + LocalDate.now() + "\n"));
	        document.add(new Phrase("Liste des stagiaires\n"));
	        document.add(new Phrase("**************************************************"));
	        
	        PdfPTable table = new PdfPTable(5);
	        table.setWidthPercentage(100); //permet d'occuper tout le pdf
	        table.addCell("Nom");
	        table.addCell("Prénom");
	        table.addCell("Département");
	        table.addCell("Type de Formation");
	        table.addCell("Année de Formation");
	        
	        for(Stagiaire stagiaire : listeFiltree) {
	        	table.addCell(new Phrase (stagiaire.getNom()));
	        	table.addCell(new Phrase (stagiaire.getPrenom()));
	        	table.addCell(new Phrase(stagiaire.getDepartement()));
	        	table.addCell(new Phrase (stagiaire.getPromo()));
	        	table.addCell(new Phrase(String.valueOf(stagiaire.getAnnee())));
	        }
	   
	        document.add(table);
	        document.close();
	        
	        new OuvrirPDF().open(file.getPath());
	    }

	}