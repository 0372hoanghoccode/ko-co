package PDF;

import java.awt.Color;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.JTable;

public class InPDF {
    private int margin = 20;

    public void generatePDF(String filePath, JTable table) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(doc, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.setNonStrokingColor(Color.BLACK);

                float yStart = page.getMediaBox().getHeight() - margin;
                float tableWidth = 700; 
                float rowHeight = 30;
                float cellWidth = tableWidth / table.getColumnCount();
                float yPosition = yStart;

                for (int i = 0; i <= table.getRowCount(); i++) {
                    float xPosition = margin;
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        String text = i == 0 ? table.getColumnName(j) : (String) table.getValueAt(i - 1, j);
                        drawCell(contentStream, xPosition, yPosition, cellWidth, rowHeight, text);
                        xPosition += cellWidth;
                    }
                    yPosition -= rowHeight;
                }

                drawTableBorders(contentStream, margin, yStart, tableWidth, rowHeight, table.getColumnCount(), table.getRowCount() + 1);
            }

            doc.save(filePath);
            System.out.println("PDF generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawCell(PDPageContentStream contentStream, float x, float y, float width, float height, String text) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x + 5, y - 15); // Adjust text position within cell
        String validText = text != null ? text.replaceAll("[^\\x00-\\x7F]", "") : ""; 
        contentStream.showText(validText);
        contentStream.endText();
    
        drawCellBorder(contentStream, x, y, width, height);
    }
    

    private void drawCellBorder(PDPageContentStream contentStream, float x, float y, float width, float height) throws IOException {
        contentStream.moveTo(x, y);
        contentStream.lineTo(x + width, y);
        contentStream.lineTo(x + width, y - height);
        contentStream.lineTo(x, y - height);
        contentStream.lineTo(x, y);
        contentStream.stroke();
    }

    private void drawTableBorders(PDPageContentStream contentStream, float margin, float yStart, float tableWidth, float rowHeight, int numColumns, int numRows) throws IOException {
        for (int i = 0; i <= numRows; i++) {
            contentStream.moveTo(margin, yStart - i * rowHeight);
            contentStream.lineTo(margin + tableWidth, yStart - i * rowHeight);
            contentStream.stroke();
        }

        float xPosition = margin;
        for (int j = 0; j <= numColumns; j++) {
            contentStream.moveTo(xPosition, yStart);
            contentStream.lineTo(xPosition, yStart - numRows * rowHeight);
            contentStream.stroke();
            xPosition += tableWidth / numColumns;
        }
    }
}
