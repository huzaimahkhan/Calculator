package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;



public class MainActivity extends AppCompatActivity {

    private class MyThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        private static final String TAG = "";

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            //Log.e(TAG, "Received exception '" + ex.getMessage() + "' from thread " + thread.getName(), ex);

           error_func();

        }
        public void error_func(){

            TextView tx=(TextView)findViewById(R.id.textView);
            tx.setText("ERROR");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread t = Thread.currentThread();
        t.setDefaultUncaughtExceptionHandler(new MyThreadUncaughtExceptionHandler());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean flag_equal;
    public double last_answer;
    //public TextView t1=(TextView)findViewById(R.id.textView);
    public void onPress(View v){
        Button b=(Button)v;
        TextView t2=(TextView)findViewById(R.id.textView);
        if(flag_equal){
            String s=b.getText().toString();
            t2.setText(s);
            flag_equal=false;
        }
        else {
            String s = t2.getText() + b.getText().toString();

            t2.setText(s);
        }
    }
    public void evaluate(View v){

        Button c=(Button)v;
        flag_equal=true;
        TextView t3=(TextView)findViewById(R.id.textView);
        String s2=t3.getText().toString();
        if(t3.getText()==""){t3.setText("");}
        else {
            try{
            Expression expression = new ExpressionBuilder(s2).build();

            double result = expression.evaluate();
                t3.setText(Double.toString(result));
                last_answer=result;}
            catch(Exception e){
                String erro="ERROR";
                t3.setText(erro);}

        }

    }
    public void lastanswer(View v){
        Button e=(Button)v;
        TextView t=(TextView)findViewById(R.id.textView);
        t.setText(Double.toString(last_answer));

    }
    public void close_exit(View v){
        Button b=(Button)v;
        finish();
        System.exit(0);
    }
    public void clear(View v){
        Button d=(Button)v;
        TextView t4=(TextView)findViewById(R.id.textView);
        t4.setText("");
    }
    public void backspace(View v){
        ImageButton b=(ImageButton)v;
        TextView t=(TextView)findViewById(R.id.textView);
        String s = t.getText().toString();
        if (s=="" || s.length()==1 || flag_equal) { 

            t.setText("");
        }
        else{

            t.setText(s.substring(0, s.length() - 1));
        }
    }
    public void pythagorean(View v){
        Button e=(Button)v;
        TextView t5=(TextView)findViewById(R.id.textView);


            String[] s = t5.getText().toString().split(",");
            try {
                t5.setText(Double.toString(Math.sqrt(Math.pow(Double.parseDouble(s[0]), 2) + Math.pow(Double.parseDouble(s[1]), 2))));
            }
            catch(Exception f){t5.setText("ERROR PYTH1");}
    }
    public void pyth2(View v){

        Button e=(Button)v;
        TextView t6=(TextView)findViewById(R.id.textView);
        String[] s = t6.getText().toString().split(",");
        try {
            t6.setText(Double.toString(Math.sqrt(Math.pow(Double.parseDouble(s[0]), 2) - Math.pow(Double.parseDouble(s[1]), 2))));
        }
        catch(Exception g){t6.setText("ERROR PYTH2");}

    }
    public void squareroot(View v){
        Button b=(Button)v;
        TextView t7=(TextView)findViewById(R.id.textView);
        try {
            t7.setText(Double.toString(Math.sqrt(Double.parseDouble(t7.getText().toString()))));
        }
        catch(Exception h){t7.setText("ERROR SQRT");}

    }

}
