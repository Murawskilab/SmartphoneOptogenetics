package com.example.optogenetics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class third_activity extends AppCompatActivity {

    String height;
    String width;
    String X;
    String Y;
    String border;
    String fill;
    String shape;
    String colors;
    String repetition;
    String distance;
    String small_rectangle;
    String movement;
    String direction;

    String small_rectangleX;
    String small_rectangleY;

    String image_path;

    int afterOnCreate = 0;

    int counter = 0;

    public String colR = "";
    public String colG = "";
    public String colB = "";

    public String timeBlack = "";
    public String timeCol = "";
    public String timeBlack2 = "";
    public String timeCol2 = "";

    public int numCol;
    public int numRep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.afterOnCreate = 1;
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        //layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_third_activity);


        Intent intent = getIntent();

        shape = intent.getStringExtra("shape");
        repetition = intent.getStringExtra("repetition");

        if(shape.equals("Picture")){
            image_path = intent.getStringExtra("image_path");
        }
        else{
            height = intent.getStringExtra("height");
            width = intent.getStringExtra("width");
            X = intent.getStringExtra("X");
            Y = intent.getStringExtra("Y");
            border = intent.getStringExtra("border");
            fill = intent.getStringExtra("fill");

            colors = intent.getStringExtra("colors");

            distance = intent.getStringExtra("distance");
            small_rectangle = intent.getStringExtra("small_rectangle");
            movement = intent.getStringExtra("movement");
            direction = intent.getStringExtra("direction");


            small_rectangleX=intent.getStringExtra("small_rectangleX");
            small_rectangleY=intent.getStringExtra("small_rectangleY");





        }





        final EditText color_R_editText = (EditText) findViewById(R.id.color_R_txtView);
        final EditText color_G_editText = (EditText) findViewById(R.id.color_G_txtView);
        final EditText color_B_editText = (EditText) findViewById(R.id.color_B_txtView);
        color_R_editText.setText("0");
        color_G_editText.setText("0");
        color_B_editText.setText("0");

        final EditText black_secs_editText = (EditText) findViewById(R.id.black_secs_editText);
        final EditText black_msecs_editText = (EditText) findViewById(R.id.black_msecs_editText);
        black_secs_editText.setText("1");
        black_msecs_editText.setText("0");

        final EditText color_secs_editText = (EditText) findViewById(R.id.color_secs_editText);
        final EditText color_msecs_editText = (EditText) findViewById(R.id.color_msecs_editText);
        color_secs_editText.setText("1");
        color_msecs_editText.setText("0");

        final TextView RGB_txtView = (TextView) findViewById(R.id.RGB_txtView);
        RGB_txtView.setBackgroundColor(Color.rgb(Integer.parseInt(color_R_editText.getText().toString()),
                Integer.parseInt(color_G_editText.getText().toString()),
                Integer.parseInt(color_B_editText.getText().toString())));

        if(shape.equals("Picture")){
            RGB_txtView.setEnabled(false);
            color_R_editText.setEnabled(false);
            color_G_editText.setEnabled(false);
            color_B_editText.setEnabled(false);
        }

        black_secs_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(black_secs_editText.getText())){
                    //black_secs_editText.setText("1");

                }else if(Integer.parseInt(black_secs_editText.getText().toString())>3600) {
                    black_secs_editText.setText("3600");
                }
                else{
                }
                black_secs_editText.setSelection(black_secs_editText.getText().length());
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        black_msecs_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(black_msecs_editText.getText())){
                    //black_msecs_editText.setText("0");

                }else if(Integer.parseInt(black_msecs_editText.getText().toString())>3600) {
                    black_msecs_editText.setText("999");
                }
                else{
                }
                black_msecs_editText.setSelection(black_msecs_editText.getText().length());
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        color_secs_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(color_secs_editText.getText())){
                    //color_secs_editText.setText("1");

                }else if(Integer.parseInt(color_secs_editText.getText().toString())>3600) {
                    color_secs_editText.setText("3600");
                }
                else{
                }
                color_secs_editText.setSelection(color_secs_editText.getText().length());
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        color_msecs_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(color_msecs_editText.getText())){
                    //color_msecs_editText.setText("0");

                }else if(Integer.parseInt(color_msecs_editText.getText().toString())>3600) {
                    color_msecs_editText.setText("999");
                }
                else{
                }
                color_msecs_editText.setSelection(color_msecs_editText.getText().length());
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });


        color_R_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int r=0;
                int g=0;
                int b=0;
                if(TextUtils.isEmpty(color_R_editText.getText())){
                    //color_R_editText.setText("0");

                }else if(Integer.parseInt(color_R_editText.getText().toString())>255) {
                    color_R_editText.setText("255");
                }
                else{
                }
                color_R_editText.setSelection(color_R_editText.getText().length());
                if (!(TextUtils.isEmpty(color_R_editText.getText()))) {
                    r = Integer.parseInt(color_R_editText.getText().toString());
                }
                if (!(TextUtils.isEmpty(color_G_editText.getText()))) {
                    g = Integer.parseInt(color_G_editText.getText().toString());
                }
                if (!(TextUtils.isEmpty(color_B_editText.getText()))) {
                    b = Integer.parseInt(color_B_editText.getText().toString());
                }
                RGB_txtView.setBackgroundColor(Color.rgb(r,g,b));
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        color_G_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int r=0;
                int g=0;
                int b=0;
                if(TextUtils.isEmpty(color_G_editText.getText())){
                    //color_G_editText.setText("0");

                }else if(Integer.parseInt(color_G_editText.getText().toString())>255) {
                    color_G_editText.setText("255");
                }
                else{
                }
                color_G_editText.setSelection(color_G_editText.getText().length());

                if (!(TextUtils.isEmpty(color_R_editText.getText()))) {
                    r = Integer.parseInt(color_R_editText.getText().toString());
                }
                if (!(TextUtils.isEmpty(color_G_editText.getText()))) {
                    g = Integer.parseInt(color_G_editText.getText().toString());
                }
                if (!(TextUtils.isEmpty(color_B_editText.getText()))) {
                    b = Integer.parseInt(color_B_editText.getText().toString());
                }
                RGB_txtView.setBackgroundColor(Color.rgb(r,g,b));

            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        color_B_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int r=0;
                int g=0;
                int b=0;
                if(TextUtils.isEmpty(color_B_editText.getText())){
                    //color_B_editText.setText("0");


                }else if(Integer.parseInt(color_B_editText.getText().toString())>255) {
                    color_B_editText.setText("255");
                }
                else{
                }
                color_B_editText.setSelection(color_B_editText.getText().length());
                if (!(TextUtils.isEmpty(color_R_editText.getText()))) {
                    r = Integer.parseInt(color_R_editText.getText().toString());
                }
                if (!(TextUtils.isEmpty(color_G_editText.getText()))) {
                    g = Integer.parseInt(color_G_editText.getText().toString());
                }
                if (!(TextUtils.isEmpty(color_B_editText.getText()))) {
                    b = Integer.parseInt(color_B_editText.getText().toString());
                }
                RGB_txtView.setBackgroundColor(Color.rgb(r,g,b));
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        this.counter=this.counter-1;
        if(this.counter<0){
            Intent intent = new Intent(this, second_activity.class);
           startActivity(intent);
            finish();
        }
       else{
            String [] timeBlackS = timeBlack.split(";");
            String [] timeBlack2S = timeBlack2.split(";");
            String [] timeColS = timeCol.split(";");
            String [] timeCol2S = timeCol2.split(";");
            String [] colRS = colR.split(";");
            String [] colGS = colG.split(";");
            String [] colBS = colB.split(";");

            TextView RGB_txtView = findViewById(R.id.RGB_txtView);
            RGB_txtView.setBackgroundColor(Color.rgb(Integer.parseInt(colRS[this.counter]),Integer.parseInt(colGS[this.counter]),Integer.parseInt(colBS[this.counter])));

            TextView color_R_editText = findViewById(R.id.color_R_txtView);
            TextView color_G_editText = findViewById(R.id.color_G_txtView);
            TextView color_B_editText = findViewById(R.id.color_B_txtView);
            color_R_editText.setText(colRS[this.counter]);
            color_G_editText.setText(colGS[this.counter]);
            color_B_editText.setText(colBS[this.counter]);

            final EditText black_secs_editText = (EditText) findViewById(R.id.black_secs_editText);
            final EditText black_msecs_editText = (EditText) findViewById(R.id.black_msecs_editText);
            final EditText color_secs_editText = (EditText) findViewById(R.id.color_secs_editText);
            final EditText color_msecs_editText = (EditText) findViewById(R.id.color_msecs_editText);

            black_secs_editText.setText(timeBlackS[this.counter]);
            black_msecs_editText.setText(timeBlack2S[this.counter]);
            color_secs_editText.setText(timeColS[this.counter]);
            color_msecs_editText.setText(timeCol2S[this.counter]);

            this.timeBlack = "";
            this.timeBlack2 = "";
            this.timeCol = "";
            this.timeCol2 = "";
            this.colR = "";
            this.colG = "";
            this.colB = "";

            TextView time_color_txtView = (TextView) findViewById(R.id.time_color_txtView);
            TextView color_txtView = (TextView) findViewById(R.id.color_txtView);

            //this.counter=this.counter-1;
            time_color_txtView.setText("Time Colour " + Integer.toString(this.counter + 1) + ":");
            color_txtView.setText("Colour " + Integer.toString(this.counter + 1) + ":");
            for(int i = 0; i<this.counter; i++){
                this.timeBlack += timeBlackS[i] + ";";
                this.timeBlack2 += timeBlack2S[i] + ";";
                this.timeCol += timeColS[i] + ";";
                this.timeCol2 += timeCol2S[i] + ";";
                this.colR += colRS[i] + ";";
                this.colG += colGS[i] + ";";
                this.colB += colBS[i] + ";";
            }

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(this.afterOnCreate==1){}
        else {
            String[] timeBlackS = timeBlack.split(";");
            String[] timeBlack2S = timeBlack2.split(";");
            String[] timeColS = timeCol.split(";");
            String[] timeCol2S = timeCol2.split(";");
            String[] colRS = colR.split(";");
            String[] colGS = colG.split(";");
            String[] colBS = colB.split(";");
/*
        TextView RGB_txtView = findViewById(R.id.RGB_txtView);
        RGB_txtView.setBackgroundColor(Color.rgb(Integer.parseInt(colRS[this.counter]),Integer.parseInt(colGS[this.counter]),Integer.parseInt(colBS[this.counter])));

        TextView color_R_editText = findViewById(R.id.color_R_txtView);
        TextView color_G_editText = findViewById(R.id.color_G_txtView);
        TextView color_B_editText = findViewById(R.id.color_B_txtView);
        color_R_editText.setText(colRS[this.counter]);
        color_G_editText.setText(colGS[this.counter]);
        color_B_editText.setText(colBS[this.counter]);

        final EditText black_secs_editText = (EditText) findViewById(R.id.black_secs_editText);
        final EditText black_msecs_editText = (EditText) findViewById(R.id.black_msecs_editText);
        final EditText color_secs_editText = (EditText) findViewById(R.id.color_secs_editText);
        final EditText color_msecs_editText = (EditText) findViewById(R.id.color_msecs_editText);

        black_secs_editText.setText(timeBlackS[this.counter]);
        black_msecs_editText.setText(timeBlack2S[this.counter]);
        color_secs_editText.setText(timeColS[this.counter]);
        color_msecs_editText.setText(timeCol2S[this.counter]);
*/

            this.timeBlack = "";
            this.timeBlack2 = "";
            this.timeCol = "";
            this.timeCol2 = "";
            this.colR = "";
            this.colG = "";
            this.colB = "";

            TextView time_color_txtView = (TextView) findViewById(R.id.time_color_txtView);
            TextView color_txtView = (TextView) findViewById(R.id.color_txtView);

            this.counter=this.counter-1;
            time_color_txtView.setText("Time Colour " + Integer.toString(this.counter + 1) + ":");
            color_txtView.setText("Colour " + Integer.toString(this.counter + 1) + ":");
            for (int i = 0; i < this.counter; i++) {
                this.timeBlack += timeBlackS[i] + ";";
                this.timeBlack2 += timeBlack2S[i] + ";";
                this.timeCol += timeColS[i] + ";";
                this.timeCol2 += timeCol2S[i] + ";";
                this.colR += colRS[i] + ";";
                this.colG += colGS[i] + ";";
                this.colB += colBS[i] + ";";
            }
        }
    }

    public void onClickButton(View view) {

        numRep = Integer.parseInt(repetition);
        Intent intent = new Intent(this, final_activity.class);

        final EditText color_R_editText = (EditText) findViewById(R.id.color_R_txtView);
        final EditText color_G_editText = (EditText) findViewById(R.id.color_G_txtView);
        final EditText color_B_editText = (EditText) findViewById(R.id.color_B_txtView);

        final EditText black_secs_editText = (EditText) findViewById(R.id.black_secs_editText);
        final EditText black_msecs_editText = (EditText) findViewById(R.id.black_msecs_editText);

        final EditText color_secs_editText = (EditText) findViewById(R.id.color_secs_editText);
        final EditText color_msecs_editText = (EditText) findViewById(R.id.color_msecs_editText);

        if(shape.equals("Picture")){
           if(!(TextUtils.isEmpty(black_msecs_editText.getText()) && TextUtils.isEmpty(black_secs_editText.getText()))) {
                if (!(TextUtils.isEmpty(color_msecs_editText.getText().toString()) && TextUtils.isEmpty(color_msecs_editText.getText()))) {
                    //if (!(TextUtils.isEmpty(color_R_editText.getText()) && TextUtils.isEmpty(color_G_editText.getText()) && TextUtils.isEmpty(color_B_editText.getText()))) {
                    if (Integer.parseInt(black_secs_editText.getText().toString()) != 0 && Integer.parseInt(color_secs_editText.getText().toString()) != 0) {
                        this.timeCol += (Integer.parseInt(color_secs_editText.getText().toString()) + ";");
                        this.timeBlack += (Integer.parseInt(black_secs_editText.getText().toString()) + ";");
                        this.timeCol2 += (Integer.parseInt(color_msecs_editText.getText().toString()) + ";");
                        this.timeBlack2 += (Integer.parseInt(black_msecs_editText.getText().toString()) + ";");

                        //intent = new Intent(this, final_activity.class);
                        intent.putExtra("timeCol", repete(this.timeCol,  this.numRep));
                        intent.putExtra("timeBlack", repete(this.timeBlack,  this.numRep));
                        intent.putExtra("timeCol2", repete(this.timeCol2,  this.numRep));
                        intent.putExtra("timeBlack2", repete(this.timeBlack2, this.numRep));

                        intent.putExtra("shape", shape);

                        intent.putExtra("repetition", repetition);

                        intent.putExtra("image_path", image_path);
                        this.afterOnCreate=0;
                        startActivity(intent);
                   }
                }
            }
        }
        else{
            numCol = Integer.parseInt(colors);

        if(!(TextUtils.isEmpty(black_msecs_editText.getText()) && TextUtils.isEmpty(black_secs_editText.getText()))) {
            if(!(TextUtils.isEmpty(color_msecs_editText.getText().toString()) && TextUtils.isEmpty(color_msecs_editText.getText()))) {
                if (!(TextUtils.isEmpty(color_R_editText.getText()) && TextUtils.isEmpty(color_G_editText.getText()) && TextUtils.isEmpty(color_B_editText.getText()))) {
                    //if (Integer.parseInt(black_secs_editText.getText().toString()) != 0 && Integer.parseInt(color_secs_editText.getText().toString()) != 0) {
                        this.counter++;
                        this.colR += (Integer.parseInt(color_R_editText.getText().toString()) + ";");
                        this.colG += (Integer.parseInt(color_G_editText.getText().toString()) + ";");
                        this.colB += (Integer.parseInt(color_B_editText.getText().toString()) + ";");
                        this.timeCol += (Integer.parseInt(color_secs_editText.getText().toString()) + ";");
                        this.timeBlack += (Integer.parseInt(black_secs_editText.getText().toString()) + ";");
                        this.timeCol2 += (Integer.parseInt(color_msecs_editText.getText().toString()) + ";");
                        this.timeBlack2 += (Integer.parseInt(black_msecs_editText.getText().toString()) + ";");
                        if (counter == numCol) {
                            //send
                            //intent = new Intent(this, final_activity.class);
                            intent.putExtra("colR", repete(this.colR, this.numCol * this.numRep));
                            intent.putExtra("colG", repete(this.colG, this.numCol * this.numRep));
                            intent.putExtra("colB", repete(this.colB, this.numCol * this.numRep));
                            intent.putExtra("timeCol", repete(this.timeCol, this.numCol * this.numRep));
                            intent.putExtra("timeBlack", repete(this.timeBlack, this.numCol * this.numRep));
                            intent.putExtra("timeCol2", repete(this.timeCol2, this.numCol * this.numRep));
                            intent.putExtra("timeBlack2", repete(this.timeBlack2, this.numCol * this.numRep));

                            intent.putExtra("height", height);
                            intent.putExtra("width", width);
                            intent.putExtra("X", X);
                            intent.putExtra("Y", Y);
                            intent.putExtra("border", border);
                            intent.putExtra("fill", fill);
                            intent.putExtra("shape", shape);

                            intent.putExtra("colors", colors);
                            intent.putExtra("repetition", repetition);
                            intent.putExtra("distance", distance);
                            intent.putExtra("small_rectangle", small_rectangle);
                            intent.putExtra("movement", movement);
                            intent.putExtra("direction", direction);

                            intent.putExtra("small_rectangleX", small_rectangleX);
                            intent.putExtra("small_rectangleY", small_rectangleY);


                            intent.putExtra("image_path", image_path);

                            this.afterOnCreate=0;
                            startActivity(intent);
                        } else {
                            TextView time_color_txtView = (TextView) findViewById(R.id.time_color_txtView);
                            TextView color_txtView = (TextView) findViewById(R.id.color_txtView);

                            time_color_txtView.setText("Time Colour " + Integer.toString(this.counter + 1) + ":");
                            color_txtView.setText("Colour " + Integer.toString(this.counter + 1) + ":");

                            color_R_editText.setText("0");
                            color_G_editText.setText("0");
                            color_B_editText.setText("0");
                            black_secs_editText.setText("1");
                            black_msecs_editText.setText("0");
                            color_secs_editText.setText("1");
                            color_msecs_editText.setText("0");
                            TextView RGB_txtView = (TextView) findViewById(R.id.RGB_txtView);
                            RGB_txtView.setBackgroundColor(Color.rgb(Integer.parseInt(color_R_editText.getText().toString()),
                                    Integer.parseInt(color_G_editText.getText().toString()),
                                    Integer.parseInt(color_B_editText.getText().toString())));

                        }
                    }
                //}
            } }
        }
    }

    public static String repete(String str, int n)
    {
        if (n == 0) return "";

        String return_str = "";
        for (int i = 0; i < n; i++)
        {
            return_str += str;
        }

        return return_str;
    }

}
