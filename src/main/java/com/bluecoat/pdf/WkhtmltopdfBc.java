package com.bluecoat.pdf;

import com.bluecoat.util.StreamEater;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by nathan.fife on 4/18/2016.
 */
public class WkhtmltopdfBc {

    private String source;
    private ClassLoader classLoader;

    public WkhtmltopdfBc() {
        this.classLoader = getClass().getClassLoader();
    }

    public WkhtmltopdfBc(String source) {
        this.classLoader = getClass().getClassLoader();
        this.source = source;
    }

    public byte[] generatePdfAsBytes(boolean isUrl) {
        Process wkhtml;
        byte[] pdfBytes = null;
        try {
            String command = "wkhtmltopdf \"" + (isUrl ? this.source : getSource()) + "\" -"; // the '-' will make wkhtmltopdf stream the output
            wkhtml = Runtime.getRuntime().exec(command);
            StreamEater outputStreamEater = new StreamEater(wkhtml.getInputStream());
            outputStreamEater.start();

            StreamEater errorStreamEater = new StreamEater(wkhtml.getErrorStream());
            errorStreamEater.start();

            outputStreamEater.join();
            errorStreamEater.join();
            wkhtml.waitFor();
            if (wkhtml.exitValue() != 0) {
                throw new RuntimeException("Process (" + command + ") exited with status code " + wkhtml.exitValue() + ":\n" + new String(errorStreamEater.getBytes()));
            }

            if (outputStreamEater.getError() != null) {
                throw outputStreamEater.getError();
            }

            if (errorStreamEater.getError() != null) {
                throw errorStreamEater.getError();
            }

            return outputStreamEater.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfBytes;
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
}
