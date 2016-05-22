package com.jiaohuan.jiaohuan;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class TestServer extends AsyncTask<String, Void, Void> {

    private Exception exception;

    protected Void doInBackground(String... urls) {

        Socket socket = null;
        try {
            socket = new Socket("54.199.254.108", 50505);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(socket != null) {
            Log.wtf("NOT", "NOT");
        } else{
            Log.wtf("NULL", "NULL");
        }


        OutputStream out = null;

        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter output = new PrintWriter(out);

        output.println("Hello from Android");

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


       return null;
    }

    protected void onPostExecute(String feed) {

        Log.wtf("DONE", "HI");

    }
}