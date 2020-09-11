package com.example.optogenetics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//Final Activity to show colors on screen
public class final_activity extends AppCompatActivity {

    String height;
    String width;
    String X;
    String Y;
    String border;
    String fill;
    String shapeS;

    String colors;
    String repetition;
    String distance;
    String small_rectangle;
    String smallR;
    String movement;
    String direction;
    int min_dim =0;

    String small_rectangleX;
    String small_rectangleY;



    //Method on create to show colors
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        setContentView(R.layout.activity_final_activity);

        Intent intent = getIntent();

        shapeS = intent.getStringExtra("shape");

        ConstraintLayout linearLayout = (ConstraintLayout) findViewById(R.id.linla);



        /*
        final TextView tt = new TextView(this);
        tt.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT));
        tt.getLayoutParams().height = 400;
        tt.getLayoutParams().width = 400;
        //textView1.setText(rectH+"\n" +rectW+"\n" +rectX+"\n" +rectY+numCol);

        tt.setX(300);
        tt.setY(300);
        //textView1.setX(0);
        //textView1.setY(0);
        tt.setBackgroundColor(Color.rgb(0, 245, 0));
        linearLayout.addView(tt);*/




        final String repeat = intent.getStringExtra("repetition");
        String timeCol = intent.getStringExtra("timeCol");
        String timeBlack = intent.getStringExtra("timeBlack");
        String timeCol2 = intent.getStringExtra("timeCol2");
        String timeBlack2 = intent.getStringExtra("timeBlack2");


        final String timeBlackA[] =timeBlack.split(";");
        final String timeColA[] =timeCol.split(";");

        final String timeBlackA2[] =timeBlack2.split(";");
        final String timeColA2[] =timeCol2.split(";");

      //  tt.setText(timeCol+"\n"+timeCol2+"\n"+timeBlack+"\n"+timeBlack2);



        //if Picture show picture for time selected with run thread
        if(shapeS.equals("Picture")) {
            //linearLayout.setBackgroundColor(Color.BLUE);
            final ImageView imgview = new ImageView(this);
            imgview.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT));
            //final ImageView imgview = (ImageView) findViewById(R.id.imageView);
            Bundle extras = getIntent().getExtras();
            String name = extras.getString("image_path");
            Uri imageFinal = Uri.parse(name);
            imgview.setImageURI(imageFinal);
            linearLayout.addView(imgview);
            imgview.setVisibility(View.GONE);



            final Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        final int[] counter = {0};
                        while (counter[0] < Integer.parseInt(repeat)) {
                            Thread.sleep(1000 * Integer.parseInt(timeBlackA[counter[0]]) + Integer.parseInt(timeBlackA2[counter[0]]));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imgview.setVisibility(View.VISIBLE);
                                    counter[0]++;
                                }
                            });

                            Thread.sleep(1000 * Integer.parseInt(timeColA[counter[0]]) + Integer.parseInt(timeColA2[counter[0]]));

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imgview.setVisibility(View.GONE);
                                }
                            });

                        }
                    } catch (InterruptedException e) {
                    }
                }
            };

            t.start();
        }
        else{//else show colors on screen
            //linearLayout.setBackgroundColor(Color.RED);
            distance = intent.getStringExtra("distance");
            movement = intent.getStringExtra("movement");
            direction = intent.getStringExtra("direction");
            border = intent.getStringExtra("border");
            fill = intent.getStringExtra("fill");

            smallR = intent.getStringExtra("small_rectangle");

            //System.out.println(smallR);
            //System.out.println(Boolean.parseBoolean(smallR));

            small_rectangleX=intent.getStringExtra("small_rectangleX");
            small_rectangleY=intent.getStringExtra("small_rectangleY");

            System.out.println(shapeS);
            System.out.println(small_rectangle);
            System.out.println(Integer.parseInt(small_rectangleX));
            System.out.println(Integer.parseInt(small_rectangleY));

            final String rectH = intent.getStringExtra("height");
            final String rectW = intent.getStringExtra("width");
            String rectX = intent.getStringExtra("X");
            String rectY = intent.getStringExtra("Y");

            final String numCol = intent.getStringExtra("colors");
            String colR = intent.getStringExtra("colR");
            String colG = intent.getStringExtra("colG");
            String colB = intent.getStringExtra("colB");
            final String colRR[] = colR.split(";");
            final String colGG[] = colG.split(";");
            final String colBB[] = colB.split(";");



            // Add textview 1
            final TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT));


            // Add textview 1
            final TextView textView2 = new TextView(this);
            textView2.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT));

            //textView1.setText(shapeS);


            textView1.getLayoutParams().height = Integer.parseInt(rectH);
            textView1.getLayoutParams().width = Integer.parseInt(rectW);
            //textView1.setText(rectH+"\n" +rectW+"\n" +rectX+"\n" +rectY+numCol);
            //textView1.setText(small_rectangleX +"\n"+small_rectangleY +"\n" +smallR +"\n");

            textView1.setX(Integer.parseInt(rectX));
            textView1.setY(Integer.parseInt(rectY));
            //textView1.setX(0);
            //textView1.setY(0);
            textView1.setBackgroundColor(Color.rgb(0, 0, 0));
            linearLayout.addView(textView1);


            if(Integer.parseInt(rectH)>Integer.parseInt(rectW))
            {
                min_dim=Integer.parseInt(rectW)/2;
            }
            else{
                min_dim=Integer.parseInt(rectH)/2;
            }
            //if(smallR=="true"){
            textView2.getLayoutParams().height = 50;
            textView2.getLayoutParams().width = 50;
            //textView1.setText(smallR);//rectH+"\n" +rectW+"\n" +rectX+"\n" +rectY+numCol);
            textView2.setX(Integer.parseInt(small_rectangleX));//Integer.parseInt(rectX) + Integer.parseInt(rectW) + 50);
            textView2.setY(Integer.parseInt(small_rectangleY));//Integer.parseInt(rectY));



            //textView1.setX(0);
            //textView1.setY(0);
            textView2.setBackgroundColor(Color.rgb(0, 0, 0));
            linearLayout.addView(textView2);//}
            if(!Boolean.parseBoolean(smallR)) {
                textView2.setVisibility(View.GONE);
            }
            //textView1.setBackgroundColor(Color.rgb(255,0,0));
//textView1.setText(fill);


            //textView1.setBackgroundDrawable(shape);

            final Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        final int[] counter = {0};
                        while (counter[0] < Integer.parseInt(numCol)*Integer.parseInt(repeat)) {
                            Thread.sleep(1000 * Integer.parseInt(timeBlackA[counter[0]]) + Integer.parseInt(timeBlackA2[counter[0]]));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //updateColor(true, textView1, Integer.parseInt(colRR[counter[0]]), Integer.parseInt(colGG[counter[0]]), Integer.parseInt(colBB[counter[0]]));

                                    //shape.setStroke(200,Color.BLUE);

                                    //shape.setColor(Color.rgb(Integer.parseInt(colRR[counter[0]]), Integer.parseInt(colGG[counter[0]]), Integer.parseInt(colBB[counter[0]])));
                                    if (Boolean.parseBoolean(fill)) {
                                        //updateColor(true, textView1, Integer.parseInt(colRR[counter[0]]), Integer.parseInt(colGG[counter[0]]), Integer.parseInt(colBB[counter[0]]));
                                        GradientDrawable shape = new GradientDrawable();
                                        shape.setShape(GradientDrawable.RECTANGLE);
                                        shape.setColor(Color.rgb(Integer.parseInt(colRR[counter[0]]), Integer.parseInt(colGG[counter[0]]), Integer.parseInt(colBB[counter[0]])));
                                        if(shapeS.equals("Circle"))
                                            shape.setCornerRadius(min_dim);
                                        textView1.setBackgroundDrawable(shape);
                                    }else {
                                        GradientDrawable shape = new GradientDrawable();
                                        shape.setShape(GradientDrawable.RECTANGLE);
                                        shape.setColor(Color.BLACK);
                                        //shape.setCornerRadius(200);
                                        if(shapeS.equals("Circle"))
                                            shape.setCornerRadius(min_dim);
                                        shape.setStroke(Integer.parseInt(border), Color.rgb(Integer.parseInt(colRR[counter[0]]), Integer.parseInt(colGG[counter[0]]), Integer.parseInt(colBB[counter[0]])));
                                        textView1.setBackgroundDrawable(shape);
                                    }
                                    //else
                                    //  shape.setStroke(Integer.parseInt(rectW), Color.rgb(Integer.parseInt(colRR[counter[0]]), Integer.parseInt(colGG[counter[0]]), Integer.parseInt(colBB[counter[0]])));


                                    // updateColor(true, textView1, Integer.parseInt(colRR[counter[0]]), Integer.parseInt(colGG[counter[0]]), Integer.parseInt(colBB[counter[0]]));
                                    //shape.setStroke(Integer.parseInt(border), Color.YELLOW);
                                    //textView1.setBackgroundDrawable(shape);
                                    updateColor(Boolean.parseBoolean(smallR), textView2, 255,255,255);
                                    //counter[0]++;
                                    textView1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                                    moveView(textView1, Integer.parseInt(distance), 1000 * Integer.parseInt(timeColA[counter[0]]) + Integer.parseInt(timeColA2[counter[0]]), direction);
                                    textView1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                                    counter[0]++;
                                }
                            });

                            Thread.sleep(1000 * Integer.parseInt(timeColA[counter[0]]) + Integer.parseInt(timeColA2[counter[0]]));

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    updateColor(false, textView1, 0, 0, 0);

                                    updateColor(Boolean.parseBoolean(smallR), textView2, 0,0,0);
                                    //counter[0]++;
                                }
                            });

                        }
                    } catch (InterruptedException e) {
                    }
                }
            };

            t.start();




        }




        //imgview.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
          //      WindowManager.LayoutParams.WRAP_CONTENT));






        /*TextView txt = (TextView) findViewById(R.id.textView);
        txt.setText(height+"\n"+width+"\n"+X+"\n"+Y+"\n"+border+"\n"+colors+"\n"+repetition+"\n"
                +distance+"\n"+small_rectangle+"\n"+movement+"\n"
                +colR+"\n"+colG+"\n"+colB+"\n"
                +timeCol+"\n"+timeCol2+"\n"+timeBlack+"\n"+timeBlack2);
        */


    }


    //Method to move view
    public void moveView(TextView view, int distance, int duration, String direction){
        TranslateAnimation anim;
        if(direction.equals("vertical")) {
            anim = new TranslateAnimation(0, 0, 0, distance);
        }
        else{
            anim = new TranslateAnimation(0, distance, 0, 0);
        }
        anim.setDuration(duration);
        anim.setFillAfter( true );
        view.startAnimation(anim);
    }

    //Method to update color of shape
    public void updateColor(boolean ok, TextView textView, int r, int g, int b){
        if(ok) textView.setBackgroundColor(Color.rgb(r,g,b));
        else textView.setBackgroundColor(Color.BLACK);

    }

    //Method for back button to go back to the initial activity
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }



}
