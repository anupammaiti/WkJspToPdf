import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by nathan.fife on 4/18/2016.
 */
public class WkhtmltopdfBc {

    private String source;
    private String destination;
    private ClassLoader classLoader;

    public WkhtmltopdfBc() {
        classLoader = getClass().getClassLoader();
    }

    public WkhtmltopdfBc(String source, String destination) {
        classLoader = getClass().getClassLoader();
        this.source = source;
        this.destination = destination;
    }

    public String generatePdf() {
        StringBuffer inputString = new StringBuffer();
        StringBuffer errorString = new StringBuffer();
        StringBuffer outputString = new StringBuffer();

        Process wkhtml;
        try {
            String command = "wkhtmltopdf \"" + getSource() + "\" -"; // the '-' will make wkhtmltopdf stream the output
            wkhtml = Runtime.getRuntime().exec(command);
            wkhtml.waitFor();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(wkhtml.getInputStream()));
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(wkhtml.getErrorStream()));
            BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(wkhtml.getOutputStream()));

            String line = "";
            while ((line = inputStream.readLine())!= null) {
                inputString.append(line + "\n");
            }
            while ((line = errorStream.readLine())!= null) {
                errorString.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputString.toString();
    }

    public String getSource() {
        URL url = classLoader.getResource(source);
        if(url == null) {
            return "";
        }
        try {
            return url.toURI().getPath().substring(1);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        URL url = classLoader.getResource("");
        if(url == null) {
            return "";
        }
        try {
            return url.toURI().getPath().substring(1) + this.destination;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
