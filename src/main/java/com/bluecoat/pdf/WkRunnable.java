package com.bluecoat.pdf;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * Created by njfif on 7/6/2016.
 */
public class WkRunnable implements Runnable {

    private InputStream sourceStream;
    private OutputStream destinationStream;
    private String url;

    public WkRunnable() {}

    public WkRunnable(InputStream sourceStream, OutputStream destinationStream) {
        this.sourceStream = sourceStream;
        this.destinationStream = destinationStream;
    }

    private static final String WK_HTML_TO_PDF_CMD = "wkhtmltopdf";
    private static final String AS_STREAM = "-";

    @Override
    public void run() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    WK_HTML_TO_PDF_CMD,
                    !StringUtils.isEmpty(url) ? url : AS_STREAM,
                    AS_STREAM);
            try {
                Process wkhtml = processBuilder.start();

                // Read and Print Error Stream
                Thread errorThread = new Thread(() -> {
                    try {
                        IOUtils.copy(wkhtml.getErrorStream(), System.out);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });

                // Read source stream and copy it to WkHtml's source stream (wk's OutputStream)
                Thread sourceThread = new Thread(() -> {
                    try {
                        IOUtils.copy(sourceStream, wkhtml.getOutputStream());
                        wkhtml.getOutputStream().flush();
                        wkhtml.getOutputStream().close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });

                // Read Wk's inputStream (the pdf) and copy to the destinationStream
                Thread destinationThread = new Thread(() -> {
                    try {
                        IOUtils.copy(wkhtml.getInputStream(), destinationStream);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });

                errorThread.start();
                if(StringUtils.isEmpty(url)) {
                    sourceThread.start();
                }
                destinationThread.start();

                errorThread.join();
                if(StringUtils.isEmpty(url)) {
                    sourceThread.join();
                }
                destinationThread.join();

                wkhtml.waitFor();

                if (wkhtml.exitValue() != 0) {
                    throw new RuntimeException("Stuff got Broke'ed");
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
