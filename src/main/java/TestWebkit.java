/**
 * Created by nathan.fife on 4/18/2016.
 */
public class TestWebkit {

    public static void main(String[] args) {
        WkhtmltopdfBc ec = new WkhtmltopdfBc("reports/testDoc.html", "reports/outputDoc.pdf");
        System.out.println(ec.generatePdf());
    }
}
