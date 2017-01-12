package com.example.ziri.gopigo_new;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class GopigoActivity extends Activity {

    Button up, down, right, left, start, pause, connect;
    Socket socket;
    int stop=0;
    String last_command="";
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gopigo);

        start = (Button)findViewById(R.id.start);
        pause = (Button)findViewById(R.id.pause);
        up = (Button)findViewById(R.id.up);
        down = (Button)findViewById(R.id.down);
        right = (Button)findViewById(R.id.right);
        left = (Button)findViewById(R.id.left);
        connect = (Button)findViewById(R.id.connect);

        webView = (WebView)findViewById(R.id.webview);

        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<img src=\"http://192.168.43.88:8081/\">\n" +
                "</body>\n" +
                "</html>";
        String mime = "text/html";
        String encoding = "utf-8";

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL(null, html, mime, encoding, null);
    }


    public void onClick(View view){
        Intent i;
        final int id = view.getId();
        switch (id) {
                case R.id.start:
                i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://192.168.43.88/start.php"));
                startActivity(i);
                break;
            case R.id.stop:
                i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://192.168.43.88/stop.php"));
                startActivity(i);
                break;
            case R.id.connect:
                MyClientTask myClientTask = new MyClientTask("192.168.43.88",12345);
                myClientTask.execute();
                break;
            case R.id.up:
                sendMessage("fwd");
                last_command="fwd";
                break;
            case R.id.down:
                sendMessage("bwd");
                last_command="bwd";
                break;
            case R.id.right:
                sendMessage("right");
                last_command="right";
                break;
            case R.id.left:
                sendMessage("left");
                last_command="left";
                break;
            case R.id.pause:
                if(stop==0){sendMessage("stop");
                }else{sendMessage(last_command);}
                stop=1-stop;
                break;
        }
    }

    private void sendMessage(String msg){
        if(socket==null){
            Toast.makeText(getApplicationContext(), "Press on Connection before", Toast.LENGTH_LONG).show();
        }else {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);
                out.println(msg);
            } catch (IOException ioe) {
                Log.e(getClass().getSimpleName(), ioe.toString());
            }
        }
    }

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response;

        MyClientTask(String addr, int port){
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                socket = new Socket(dstAddress, dstPort);
                InputStream inputStream = socket.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream =
                        new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                socket.close();
                response = byteArrayOutputStream.toString("UTF-8");

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //textResponse.setText(response);
            super.onPostExecute(result);
        }

    }
}