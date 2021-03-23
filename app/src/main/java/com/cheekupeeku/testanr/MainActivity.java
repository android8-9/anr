package com.cheekupeeku.testanr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.cheekupeeku.testanr.databinding.FactBinding;

public class MainActivity extends AppCompatActivity {
    FactBinding binding;
    FactorialHandler handler = new FactorialHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FactBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(binding.etNumber.getText().toString());
                new FactorialThread(n).start();
            }
        });
    }
    class FactorialThread extends Thread{
        int n;
        public FactorialThread(int n){
            this.n = n;
        }
        public void run(){
               try {
                   int data = 1;
                   for (int i = 1; i <= n; i++) {
                       data = data * i;

                       Bundle bn = new Bundle();
                       bn.putString("data", "" + data);

                       Message message = new Message();
                       message.setData(bn);
                       Thread.sleep(2000);
                       handler.sendMessage(message);
                   }
               }
               catch (Exception e){

               }

        }
    }
    class FactorialHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bn = msg.getData();

            String data = bn.getString("data");

            binding.textView.setText("Factorail : "+data);
        }
    }
}