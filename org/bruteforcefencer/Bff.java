package org.bruteforcefencer;

import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

public class Bff {
    private String addr;
    private int port;

    public Bff(String addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    public boolean hit(String direction, Object value) throws IOException {
        Socket s = new Socket(addr, port);
        PrintWriter out = new PrintWriter(s.getOutputStream());
        InputStream in = s.getInputStream();

        JSONObject obj = new JSONObject();
        obj.put("Direction", direction);
        obj.put("Value", value);

        out.print(obj);
        out.flush();

        char result = (char)in.read();
        return result == 't';
    }
}
