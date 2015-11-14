package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import metier.Sejour;

public class PdfFactureSejourView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Sejour sejour = (Sejour) model.get("sejour");
		Paragraph header = new Paragraph(new Chunk("Facture", FontFactory.getFont(FontFactory.HELVETICA, 30)));
		Paragraph by = new Paragraph(
				new Chunk("Numéro : " + sejour.getNumSej(), FontFactory.getFont(FontFactory.HELVETICA, 20)));
		Paragraph by2 = new Paragraph(new Chunk("Nombre de personnes : " + sejour.getNbPersonnes(),
				FontFactory.getFont(FontFactory.HELVETICA, 20)));

		by2.add(new Chunk("Date début : " + sejour.getDateDebSej()));
		by2.add(new Chunk("Date fin : " + sejour.getDateFinSej()));
		by2.add(new Chunk("Emplacement (nb personnes max) : " + sejour.getEmplacement().getNbPersMaxEmpl()));

		// TODO remplir le pdf

		document.add(header);
		document.add(by);
		document.add(by2);
	}

}
