
package com.example.myapplication_gp1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.RemoteActionCompatParcelizer;
import android.view.View;
import android.widget.Button;
public class MainActivity extends Activity {


    //主布局中定义了一个按钮用以启动线程
    Button button;

    //步骤1:创建线程类，实现Runnable接口
    private class MyThread1 implements Runnable{

        private int ticket = 100;//一个窗口有100张票

        //在run方法里复写需要进行的操作:卖票速度1s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);

                try {
                    Thread.sleep(1000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button按下时会开启一个新线程执行卖票
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //步骤2:创建线程类的实例
                //创建二个线程，模拟二个窗口卖票
                MyThread1 mt1 = new MyThread1();
                MyThread1 mt2 = new MyThread1();

                Thread mt11 = new Thread(mt1, "窗口1");
                Thread mt22 = new Thread(mt2, "窗口2");

                //步骤3：调用start()方法开启线程
                //启动二个线程，也即是窗口，开始卖票
                mt11.start();
                mt22.start();
            }
        });
    }
}
