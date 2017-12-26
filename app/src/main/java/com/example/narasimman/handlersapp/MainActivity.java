package com.example.narasimman.handlersapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

/*
In this project we will see how to use the handlers in android

 */
public class MainActivity extends AppCompatActivity {
    Handler handler;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                progressBar.setProgress(msg.arg1);//we can access these UI elements only with main thread
            }
        };
        /*
        when we create handler inside oncreate method then it will be referenced with the Main thread

        And HandleMessage() is a method in which it handles the incoming messages.
         */
        Thread mythread=new Thread(new Mythread());//creating new thread and executing some process in that thread
        mythread.start();


    }

    class Mythread implements Runnable{

        @Override
        public void run() {//this method will be called when mythread.start() method called in oncreate method
        for(int i=0;i<100;i++){
            Message message=Message.obtain();//creating reference to the message object by which we can send some data to the main thread
            message.arg1=i;
            handler.sendMessage(message);//sending the message to the handler.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
    }
}

/*
Here we are sending the message and then we are processing in oncreate method instead we can call post method of the handler
anywhere and we can post some bunch of code to the handler and we can run it

 handler.post(new Runnable(){
 public void run(){
 processbar.setVisibility(View.VISIBLE);//here we are aceessing the ui elements from another thread
 });}

here runnable will take the bunch of code inside the run method and it will
run the code in the another thread in which the handler is attached to.

 */
