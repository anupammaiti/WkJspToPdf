package com.bluecoat.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nathan.fife on 4/22/2016.
 */
public class StreamEater extends Thread {

    private InputStream stream;
    private ByteArrayOutputStream bytes;

    private IOException error;

    public StreamEater(InputStream stream) {
        this.stream = stream;

        bytes = new ByteArrayOutputStream();
    }

    public void run() {
        try {
            int bytesRead = stream.read();
            while (bytesRead >= 0) {
                bytes.write(bytesRead);
                bytesRead = stream.read();
            }

            stream.close();
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
