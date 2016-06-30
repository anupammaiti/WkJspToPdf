package com.bluecoat.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by nathan.fife on 4/22/2016.
 */
public class StreamEater extends Thread {

    private InputStream inputStream;
    private ByteArrayOutputStream bytes;
    private OutputStream outputStream;

    private IOException error;

    public StreamEater(InputStream stream) {
        this.inputStream = stream;
        bytes = new ByteArrayOutputStream();
        outputStream = null;
    }

    public StreamEater(InputStream stream, OutputStream outputStream) {
        this.inputStream = stream;
        this.outputStream = outputStream;
        bytes = new ByteArrayOutputStream();
    }

    @Override
    public void run() {
        try {
            int bytesRead = inputStream.read();
            while (bytesRead >= 0) {
                if(outputStream == null) {
                    bytes.write(bytesRead);
                } else {
                    this.outputStream.write(bytesRead);
                }
                bytesRead = inputStream.read();
            }
            if(outputStream != null) {
                outputStream.flush();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();

            error = e;
        }
    }

    public IOException getError() {
        return error;
    }

    public byte[] getBytes() {
        return bytes.toByteArray();
    }
}
