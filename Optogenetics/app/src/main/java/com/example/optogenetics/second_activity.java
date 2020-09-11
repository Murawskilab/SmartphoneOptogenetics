package com.example.optogenetics;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.SeekableByteChannel;

//Second activity to select small rectangle, movement ...
public class second_activity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;
    String height;
    String width;
    String X;
    String Y;
    String border;
    String fill;
    String shape;

    Uri imageUri;
    int image_OK = 0;

    //OnCreate method to get previous values and listener for button, textview...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        //layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_second_activity);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int screen_width = size.x;
        final int screen_height = size.y + getNavigationBarHeight();

        final EditText small_rectangle_X_editText = (EditText) findViewById(R.id.smallrectX_editText);
        final SeekBar small_rectagle_X_seekbar = (SeekBar) findViewById(R.id.smallrectX_seekBar);
        final EditText small_rectangle_Y_editText = (EditText) findViewById(R.id.smallrectY_editText);
        final SeekBar small_rectagle_Y_seekbar = (SeekBar) findViewById(R.id.smallrectY_seekBar);

        small_rectagle_X_seekbar.setMax(screen_width-50);
        small_rectagle_Y_seekbar.setMax(screen_width-50);

        CheckBox movement_checkbox = (CheckBox) findViewById(R.id.movement_checkBox);
        Switch direction_switch = (Switch) findViewById(R.id.direction_switch);
        if(movement_checkbox.isChecked()){
          direction_switch.setEnabled(true);
        }
        else {
            direction_switch.setEnabled(false);
        }
        //listener for textview, button,...
        small_rectangle_X_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(small_rectangle_X_editText.getText().toString())){
                }
                else {
                    if(Integer.parseInt(small_rectangle_X_editText.getText().toString())>small_rectagle_X_seekbar.getMax())
                    {
                      small_rectangle_X_editText.setText(small_rectagle_X_seekbar.getMax());
                    }
                    else {
                        small_rectagle_X_seekbar.setProgress(Integer.parseInt(small_rectangle_X_editText.getText().toString()));
                    }
                }
                small_rectangle_X_editText.setSelection(small_rectangle_X_editText.getText().length());

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });


        small_rectangle_Y_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(small_rectangle_Y_editText.getText().toString())){
                }
                else {
                    if(Integer.parseInt(small_rectangle_Y_editText.getText().toString())>small_rectagle_Y_seekbar.getMax())
                    {
                        small_rectangle_Y_editText.setText(small_rectagle_Y_seekbar.getMax());
                    }
                    else {
                        small_rectagle_Y_seekbar.setProgress(Integer.parseInt(small_rectangle_Y_editText.getText().toString()));
                    }
                }
                small_rectangle_Y_editText.setSelection(small_rectangle_Y_editText.getText().length());

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        small_rectagle_X_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                small_rectangle_X_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        small_rectagle_Y_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                small_rectangle_Y_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        Intent intent = getIntent();

        shape = intent.getStringExtra("shape");

        if(!shape.equals("Picture")) {

            height = intent.getStringExtra("height");
            width = intent.getStringExtra("width");
            X = intent.getStringExtra("X");
            Y = intent.getStringExtra("Y");
            border = intent.getStringExtra("border");
            fill = intent.getStringExtra("fill");
        }


        final EditText colors_editText = (EditText) findViewById(R.id.colors_editText);
        final EditText repetition_editText = (EditText) findViewById(R.id.repetition_editText);
        final EditText distance_editText = (EditText) findViewById(R.id.distance_editText);
        colors_editText.setText("1");
        repetition_editText.setText("1");
        distance_editText.setText("0");
        distance_editText.setEnabled(false);

        final SeekBar colors_seekBar = (SeekBar) findViewById(R.id.colors_seekBar);
        final SeekBar repetition_seekBar = (SeekBar) findViewById(R.id.repetition_seekBar);
        final SeekBar distance_seekBar = (SeekBar) findViewById(R.id.distance_seekBar);
        colors_seekBar.setMax(100);
        colors_seekBar.setProgress(1);

        repetition_seekBar.setMax(100);
        repetition_seekBar.setProgress(1);
        if(!shape.equals("Picture")) {
            distance_seekBar.setMax(screen_height - Integer.parseInt(Y) - Integer.parseInt(height));
        }
        distance_seekBar.setProgress(0);
        distance_seekBar.setEnabled(false);

        CheckBox small_rectangle_checkBox = (CheckBox) findViewById(R.id.small_rectangle_checkBox);
        CheckBox movement_checkBox = (CheckBox) findViewById(R.id.movement_checkBox);
        //movement_checkBox.setChecked(true);


        if(shape.equals("Picture")){
            colors_editText.setEnabled(false);
            colors_seekBar.setEnabled(false);
            small_rectangle_checkBox.setEnabled(false);
            movement_checkBox.setEnabled(false);
            distance_editText.setEnabled(false);
            distance_seekBar.setEnabled(false);
        }

        Button buttonPicture = (Button) findViewById(R.id.buttonPicture);

        if(shape.equals("Picture")){
            buttonPicture.setEnabled(true);
        }
        else{
            buttonPicture.setEnabled(false);
        }



        buttonPicture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"),1);
            }
        });


        //colors, repetition and distance
        colors_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(colors_editText.getText().toString())) {
                    //colors_editText.setText("1");
                    //colors_seekBar.setProgress(1);
                } else {
                    if (Integer.parseInt(colors_editText.getText().toString()) > 100) {
                        colors_editText.setText("100");
                        colors_seekBar.setProgress(100);
                    } else {
                        colors_seekBar.setProgress(Integer.parseInt(colors_editText.getText().toString()));
                    }
                }
                colors_editText.setSelection(colors_editText.getText().length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        colors_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                colors_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        repetition_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(repetition_editText.getText().toString())) {
                    //repetition_editText.setText("1");
                    //repetition_seekBar.setProgress(1);
                } else {
                    if (Integer.parseInt(repetition_editText.getText().toString()) > 100) {
                        repetition_editText.setText("100");
                        repetition_seekBar.setProgress(100);
                    } else {
                        repetition_seekBar.setProgress(Integer.parseInt(repetition_editText.getText().toString()));
                    }
                }
                repetition_editText.setSelection(repetition_editText.getText().length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        repetition_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                repetition_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        distance_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int max = distance_seekBar.getMax();
                if (TextUtils.isEmpty(distance_editText.getText().toString())) {
                    //distance_editText.setText("0");
                    //distance_seekBar.setProgress(0);
                } else {
                    if (Integer.parseInt(distance_editText.getText().toString()) >  max) {
                        distance_editText.setText(Integer.toString(max));//screen_height - Integer.parseInt(Y) - Integer.parseInt(height));
                        distance_seekBar.setProgress(max);
                    } else {
                        distance_seekBar.setProgress(Integer.parseInt(distance_editText.getText().toString()));
                    }
                }
                distance_editText.setSelection(distance_editText.getText().length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        distance_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                distance_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    //Method for switch direction button
    public void onSwitchDirection(View view){
        Switch direction_switch = (Switch) findViewById(R.id.direction_switch);
        TextView direction_txtview = (TextView) findViewById(R.id.direction_txtView);
        EditText distance_editText = (EditText) findViewById(R.id.distance_editText);
        SeekBar distance_seekbar = (SeekBar) findViewById(R.id.distance_seekBar);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screen_width = size.x;
        int screen_height = size.y + getNavigationBarHeight();

        if(direction_switch.isChecked()){
            direction_txtview.setText("horizontal");
            distance_editText.setText(Integer.toString(screen_width - Integer.parseInt(X) - Integer.parseInt(width)*3/2));
            distance_seekbar.setMax(screen_width - Integer.parseInt(X) - Integer.parseInt(width));
        }
        else{
            direction_txtview.setText("vertical");
            distance_editText.setText(Integer.toString(screen_height - Integer.parseInt(Y) - Integer.parseInt(height)*3/2));
            distance_seekbar.setMax(screen_height - Integer.parseInt(Y) - Integer.parseInt(height)*3/2);
        }
    }

    //Method for checkbox movement
    public void onCheckedMovement(View view){
        CheckBox movement_checkBox = (CheckBox) findViewById(R.id.movement_checkBox);
        EditText distance_editText = (EditText) findViewById(R.id.distance_editText);
        SeekBar distance_seekBar = (SeekBar) findViewById(R.id.distance_seekBar);
        Switch direction_switch = (Switch) findViewById(R.id.direction_switch);

        if(movement_checkBox.isChecked()){
            distance_editText.setEnabled(true);
            distance_seekBar.setEnabled(true);
            distance_editText.setText("0");
            direction_switch.setEnabled(true);
        }
        else{
            distance_editText.setEnabled(false);
            distance_seekBar.setEnabled(false);
            distance_editText.setText("0");
            direction_switch.setEnabled(false);
        }
    }

    //Method checkbox small rectangle
    public void onCheckedRectangle(View view){
        CheckBox small_rectangle_checkbox = (CheckBox) findViewById(R.id.small_rectangle_checkBox);
        EditText small_rectangle_X_editText = (EditText) findViewById(R.id.smallrectX_editText);
        SeekBar small_rectagle_X_seekbar = (SeekBar) findViewById(R.id.smallrectX_seekBar);
        EditText small_rectangle_Y_editText = (EditText) findViewById(R.id.smallrectY_editText);
        SeekBar small_rectagle_Y_seekbar = (SeekBar) findViewById(R.id.smallrectY_seekBar);

        if(small_rectangle_checkbox.isChecked()){
            small_rectangle_X_editText.setEnabled(true);
            small_rectagle_X_seekbar.setEnabled(true);
            small_rectangle_Y_editText.setEnabled(true);
            small_rectagle_Y_seekbar.setEnabled(true);
        }
        else{
            small_rectangle_X_editText.setEnabled(false);
            small_rectagle_X_seekbar.setEnabled(false);
            small_rectangle_Y_editText.setEnabled(false);
            small_rectagle_Y_seekbar.setEnabled(false);
        }
    }


    //method to get image from gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            imageUri = data.getData();
            image_OK=1;
        }
        else{
            //Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
        }
    }

    //Method OK button
    public void onClickButton(View view) {
        Intent intent = new Intent(this, third_activity.class);

        EditText colors_editText = (EditText) findViewById(R.id.colors_editText);
        EditText repetition_editText = (EditText) findViewById(R.id.repetition_editText);
        EditText distance_editText = (EditText) findViewById(R.id.distance_editText);
        CheckBox movement_CheckBox = (CheckBox) findViewById(R.id.movement_checkBox);
        CheckBox small_rectangle_checkBox = (CheckBox) findViewById(R.id.small_rectangle_checkBox);

        EditText smallrectX_editText = (EditText) findViewById(R.id.smallrectX_editText);
        EditText smallrectY_editText = (EditText) findViewById(R.id.smallrectY_editText);

        if(shape.equals("Picture")){
            if(!TextUtils.isEmpty(repetition_editText.getText().toString()) ||  Integer.parseInt(repetition_editText.getText().toString()) != 0) {
                if(image_OK==1) {
                    intent.putExtra("shape", shape);
                    intent.putExtra("repetition", repetition_editText.getText().toString());
                    intent.putExtra("image_path", imageUri.toString());
                    startActivity(intent);
                }
            }
        }
        else {
            if (!TextUtils.isEmpty(colors_editText.getText().toString()) &&
                    !TextUtils.isEmpty(repetition_editText.getText().toString()) &&
                    !TextUtils.isEmpty(distance_editText.getText().toString())) {
                if (Integer.parseInt(colors_editText.getText().toString()) != 0 &&
                        Integer.parseInt(repetition_editText.getText().toString()) != 0) {

                    //Integer.parseInt(distance_editText.getText().toString())!=0 ) {
                    intent.putExtra("height", height);
                    intent.putExtra("width", width);
                    intent.putExtra("X", X);
                    intent.putExtra("Y", Y);
                    intent.putExtra("border", border);
                    intent.putExtra("fill", fill);
                    intent.putExtra("shape", shape);

                    intent.putExtra("colors", colors_editText.getText().toString());
                    intent.putExtra("repetition", repetition_editText.getText().toString());
                    intent.putExtra("distance", distance_editText.getText().toString());

                    int srX;
                    int srY;
                    if(!TextUtils.isEmpty(smallrectX_editText.getText().toString()) && !TextUtils.isEmpty(smallrectY_editText.getText().toString())) {
                        srX=Integer.parseInt(smallrectX_editText.getText().toString());
                        srY=Integer.parseInt(smallrectY_editText.getText().toString());
                    }
                    else{
                        srX=0;
                        srY=0;
                    }
                    intent.putExtra("small_rectangleX", Integer.toString(srX));
                    intent.putExtra("small_rectangleY", Integer.toString(srX));

                    if (small_rectangle_checkBox.isChecked()) {
                        intent.putExtra("small_rectangle", "true");
                        }
                    } else {
                        intent.putExtra("small_rectangle", "false");
                    }

                    if (movement_CheckBox.isChecked()) {
                        intent.putExtra("movement", "true");
                    } else {
                        intent.putExtra("movement", "false");
                    }
                    TextView direction_txtview = (TextView) findViewById(R.id.direction_txtView);
                    intent.putExtra("direction", direction_txtview.getText().toString());




                    startActivity(intent);

            }

        }
    }

        //Method to get the size of navigation bar to compute overall screen size
        private int getNavigationBarHeight() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int usableHeight = metrics.heightPixels;
                getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
                int realHeight = metrics.heightPixels;
                if (realHeight > usableHeight)
                    return realHeight - usableHeight;
                else
                    return 0;
            }
            return 0;
        }
}
