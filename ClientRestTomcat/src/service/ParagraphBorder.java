package service;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

class ParagraphBorder extends PdfPageEventHelper {
	public boolean active = false;

	public void setActive(boolean active) {
		this.active = active;
	}

	public float offset = 5;
	public float startPosition;

	@Override
	public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
		this.startPosition = paragraphPosition;
	}

	@Override
	public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
		if (active) {
			PdfContentByte cb = writer.getDirectContentUnder();
			cb.rectangle(document.left(), paragraphPosition - offset, document.right() - document.left(),
					startPosition - paragraphPosition);
			cb.stroke();
		}
	}
}