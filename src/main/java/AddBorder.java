import com.aspose.pdf.*;

public class AddBorder {

    public  AddBorder() {
    }

    public static void main(String[] args) {
        new AddBorder().Run();
    }

    private void Run() {
        try {
            //////////////////// Set License for Every Aspose API ///////////////////
            com.aspose.pdf.License lic = new com.aspose.pdf.License();
            //lic.setLicense("D:\\Aspose.Total.Java.lic");
            DrawZigZagLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void DrawZigZagLine() {
        Document doc = new Document();
        // Add page to pages collection of PDF file
        Page page = doc.getPages().add();
        // Set page margin on all sides as 0
        page.getPageInfo().getMargin().setLeft(0);
        page.getPageInfo().getMargin().setRight(0);
        page.getPageInfo().getMargin().setBottom(0);
        page.getPageInfo().getMargin().setTop(0);
        // Create Graph object with Width and Height equal to page dimensions
        com.aspose.pdf.drawing.Graph graph = new com.aspose.pdf.drawing.Graph((float) page.getPageInfo().getWidth(), (float) page.getPageInfo().getHeight());

        // Create bottom border with 10 points from bottom, right and left; here 72 points = 1 inch
        float llx = 10, lly = 10, urx = 20, ury = 20;

        for (float i = llx; i <= page.getPageInfo().getWidth() - 10; i = i + 10) {
            com.aspose.pdf.drawing.Line line = new com.aspose.pdf.drawing.Line(new float[]{llx, lly, urx, ury});
            // Add line to shapes collection of Graph object
            graph.getShapes().add(line);
            llx = urx;
            lly = ury;
            if (((urx / 10) % 2) == 0) {
                ury = 10;
            } else {
                ury = 20;
            }
            urx = urx + 10;
        }

        // Create top border with 10 points from top, right and left; here 72 points = 1 inch
        llx = 10;
        lly = (float) page.getRect().getURY() - 10;
        urx = 20;
        ury = (float) page.getRect().getURY() - 20;
        for (float i = llx; i <= page.getPageInfo().getWidth() - 10; i = i + 10) {
            com.aspose.pdf.drawing.Line line = new com.aspose.pdf.drawing.Line(new float[]{llx, lly, urx, ury});
            // Add line to shapes collection of Graph object
            graph.getShapes().add(line);
            llx = urx;
            lly = ury;
            if (((urx / 10) % 2) == 0) {
                ury = (float) page.getRect().getURY() - 10;
            } else {
                ury = (float) page.getRect().getURY() - 20;
            }
            urx = urx + 10;
        }

        // Add Graph object to paragraphs collection of page
        page.getParagraphs().add(graph);
        // Save resultant PDF file
        doc.save("D:\\ZigZag_Border_On_Page.pdf");
    }
}
