package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import metier.Activite;
import metier.Sejour;

public class PdfFactureActiviteView extends AbstractPdfView {

	final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Sejour sejour = (Sejour) model.get("sejour");
		Font h1 = FontFactory.getFont(FontFactory.HELVETICA, 24);
		Font h2 = FontFactory.getFont(FontFactory.HELVETICA, 20);
		Font h3 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.UNDERLINE);
		Font h = FontFactory.getFont(FontFactory.HELVETICA, 12);

		// Création du coin en haut à gauche
		PdfPTable table = new PdfPTable(1);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Cerisaie", h2));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell.setPhrase(new Phrase("Route de la plage"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("33121 – CARCANS"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Etoiles : **"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Téléphone : 05 - 67-78-56-45"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Fax : 05 - 67 - 78 - 34 - 25"));
		table.addCell(cell);
		document.add(table);

		// ajout du titre
		Paragraph title = new Paragraph(new Phrase("Facturation des activités", h1));
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(10);
		document.add(title);

		Paragraph num = new Paragraph(new Phrase(
				"Numéro de facture :" + sejour.getClient().getNumCli() + sejour.getNumSej(), h3));
		num.setAlignment(Element.ALIGN_LEFT);
		num.setSpacingBefore(40);
		num.setSpacingAfter(0);
		document.add(num);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Paragraph date = new Paragraph(
				new Phrase("Date de facturation :" + dateFormat.format(sejour.getDateFinSej()), h3));
		date.setAlignment(Element.ALIGN_RIGHT);
		date.setSpacingBefore(0);
		document.add(date);
		
		// Création des info séjour
				PdfPTable sejourInfos = new PdfPTable(2);
				sejourInfos.setSpacingBefore(40);
				sejourInfos.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell(new Phrase("Numéro de séjour : ", h));
				sejourInfos.addCell(cell);
				cell.setPhrase(new Phrase(String.valueOf(sejour.getNumSej()), h));
				sejourInfos.addCell(cell);
				cell.setPhrase(new Phrase("Numéro d’emplacement : ", h));
				sejourInfos.addCell(cell);
				cell.setPhrase(new Phrase(String.valueOf(sejour.getEmplacement().getNumEmpl()), h));
				sejourInfos.addCell(cell);
				cell.setPhrase(new Phrase("Type d’emplacement :", h));
				sejourInfos.addCell(cell);
				cell.setPhrase(new Phrase(sejour.getEmplacement().getTypeEmplacement().getLibtypepl(), h));
				sejourInfos.addCell(cell);
				cell.setPhrase(new Phrase("Nombre de personnes :", h));
				sejourInfos.addCell(cell);
				cell.setPhrase(new Phrase(String.valueOf(sejour.getNbPersonnes()), h));
				sejourInfos.addCell(cell);
				document.add(sejourInfos);

		// Client
		Paragraph client = new Paragraph(new Phrase("Client :", h3));
		client.setIndentationLeft(350);
		client.setSpacingBefore(40);
		document.add(client);

		client = new Paragraph(new Phrase(sejour.getClient().getNomCli(), h2));
		client.setIndentationLeft(350);
		document.add(client);
		client = new Paragraph(new Phrase(sejour.getClient().getAdrRueCli()));
		client.setIndentationLeft(350);
		document.add(client);
		client = new Paragraph(new Phrase(sejour.getClient().getCpCli() + " " + sejour.getClient().getVilleCli()));
		client.setIndentationLeft(350);
		document.add(client);

		// Activites
				Paragraph activiteA = new Paragraph(new Phrase("** Activités **", h2));
				activiteA.setSpacingBefore(50);
				document.add(activiteA);
				PdfPTable activitesAI = new PdfPTable(4);
				activitesAI.setSpacingBefore(40);
				activitesAI.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				cell = new PdfPCell(new Phrase("Libelle Activite", h));
				activitesAI.addCell(cell);
				cell.setPhrase(new Phrase("Date", h));
				activitesAI.addCell(cell);
				cell.setPhrase(new Phrase("Durée", h));
				activitesAI.addCell(cell);
				cell.setPhrase(new Phrase("Tarif Unitaire", h));
				activitesAI.addCell(cell);
				
				for (Activite a : sejour.getActivites()){
					cell.setPhrase(new Phrase(a.getSport().getLibelleSport(), h));
					activitesAI.addCell(cell);
					cell.setPhrase(new Phrase(dateFormat.format(a.getDateJour()), h));
					activitesAI.addCell(cell);
					cell.setPhrase(new Phrase(a.getSport().getUniteTpsSport(), h));
					activitesAI.addCell(cell);
					cell.setPhrase(new Phrase(String.valueOf(a.getSport().getTarifUnite()), h));
					activitesAI.addCell(cell);
				}
				
				document.add(activitesAI);
				
		float prixTotal = 0;
		for (Activite a : sejour.getActivites()){
			prixTotal += (a.getSport().getTarifUnite() * sejour.getNbPersonnes());
		}
		Paragraph prix = new Paragraph(new Phrase("Prix total : " + prixTotal + " €", h3));
		prix.setIndentationLeft(300);
		prix.setSpacingBefore(40);
		document.add(prix);

	}

}
