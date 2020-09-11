package com.example.optogenetics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;


//Initial class (first screen)
public class MainActivity extends AppCompatActivity {

    //OnCreate method (setup listener ...)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        //layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        final Spinner shape_selection = findViewById(R.id.shape_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.shape_spinner,
                R.layout.spinner
        );
        shape_selection.setAdapter(adapter);



        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int screen_width = size.x;
        final int screen_height = size.y + getNavigationBarHeight();


        final EditText height_editText = findViewById(R.id.height_editText);
        final EditText width_editText = findViewById(R.id.width_editText);
        final EditText X_editText = findViewById(R.id.X_editText);
        final EditText Y_editText = findViewById(R.id.Y_editText);
        final EditText border_editText = findViewById(R.id.border_editText);

        final SeekBar height_seekBar = findViewById(R.id.height_seekBar);
        final SeekBar width_seekBar = findViewById(R.id.width_seekBar);
        final SeekBar X_seekBar = findViewById(R.id.X_seekBar);
        final SeekBar Y_seekBar = findViewById(R.id.Y_seekBar);
        final SeekBar border_seekBar = findViewById(R.id.border_seekBar);
        height_seekBar.setProgress(400);
        height_editText.setText("400");
        height_seekBar.setMax(screen_height);
        width_seekBar.setProgress(400);
        width_editText.setText("400");
        width_seekBar.setMax(screen_width);
        X_seekBar.setProgress(0);
        X_seekBar.setMax(screen_width-400);
        Y_seekBar.setProgress(0);
        Y_seekBar.setMax(screen_height-400);
        border_seekBar.setProgress(1);
        border_editText.setText("1");

        final CheckBox fill_checkBox = findViewById(R.id.fill_checkBox);
        final CheckBox centre_checkBox = findViewById(R.id.centre_checkBox);

        fill_checkBox.setChecked(true);
        border_seekBar.setProgress(1);
        border_editText.setEnabled(false);
        border_seekBar.setEnabled(false);

    //Listener for edittext, seekbar..
        shape_selection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(shape_selection.getSelectedItem().toString().equals("Picture")){
                    height_editText.setEnabled(false);
                    height_seekBar.setEnabled(false);
                    width_editText.setEnabled(false);
                    width_seekBar.setEnabled(false);
                    X_editText.setEnabled(false);
                    X_seekBar.setEnabled(false);
                    Y_editText.setEnabled(false);
                    Y_seekBar.setEnabled(false);
                    centre_checkBox.setEnabled(false);
                    fill_checkBox.setEnabled(false);
                    border_editText.setEnabled(false);
                    border_seekBar.setEnabled(false);
                }
                else{
                    height_editText.setEnabled(true);
                    height_seekBar.setEnabled(true);
                    width_editText.setEnabled(true);
                    width_seekBar.setEnabled(true);
                    X_editText.setEnabled(true);
                    X_seekBar.setEnabled(true);
                    Y_editText.setEnabled(true);
                    Y_seekBar.setEnabled(true);
                    centre_checkBox.setEnabled(true);
                    centre_checkBox.setChecked(false);
                    fill_checkBox.setEnabled(true);
                    fill_checkBox.setChecked(true);
                    border_editText.setEnabled(false);
                    border_seekBar.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //dimensions
        height_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(height_editText.getText().toString())){
                    //height_seekBar.setProgress(0);
                    //height_editText.setText("");
                }
                else {
                    //if height is larger than screen height
                    if(Integer.parseInt(height_editText.getText().toString())>screen_height ){
                        height_seekBar.setProgress(screen_height);
                        height_editText.setText(Integer.toString(screen_height));
                        Y_seekBar.setMax(0);
                        Y_editText.setText("0");
                    }
                    else{
                        height_seekBar.setProgress(Integer.parseInt(height_editText.getText().toString()));
                    }
                    Y_seekBar.setMax(screen_height-Integer.parseInt(height_editText.getText().toString()));
                    Y_editText.setText("0");//Y_editText.setText(Integer.toString(screen_height-Integer.parseInt(height_editText.getText().toString())));
                }



                height_editText.setSelection(height_editText.getText().length());
                int min_dim=0;
                if((!TextUtils.isEmpty(height_editText.getText().toString()) &&
                        !TextUtils.isEmpty(width_editText.getText().toString()))) {


                    if (Integer.parseInt(height_editText.getText().toString()) > Integer.parseInt(width_editText.getText().toString())) {
                        min_dim = Integer.parseInt(width_editText.getText().toString());
                    } else {
                        min_dim = Integer.parseInt(height_editText.getText().toString());
                    }

                    border_seekBar.setMax(min_dim / 2);
                }


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        height_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                height_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        width_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(width_editText.getText().toString())){
                    //width_seekBar.setProgress(0);
                    //width_editText.setText("0");
                }
                else {
                    if(Integer.parseInt(width_editText.getText().toString())>screen_width ){
                        width_seekBar.setProgress(screen_width);
                        width_editText.setText(Integer.toString(screen_width));
                        X_seekBar.setMax(0);
                        X_editText.setText("0");
                    }
                    else{
                        width_seekBar.setProgress(Integer.parseInt(width_editText.getText().toString()));
                    }
                    X_seekBar.setMax(screen_width-Integer.parseInt(width_editText.getText().toString()));
                    X_editText.setText("0");//X_editText.setText(Integer.toString(screen_width-Integer.parseInt(width_editText.getText().toString())));
                }

                width_editText.setSelection(width_editText.getText().length());
                int min_dim=0;
                if((!TextUtils.isEmpty(height_editText.getText().toString()) &&
                        !TextUtils.isEmpty(width_editText.getText().toString()))) {


                    if (Integer.parseInt(height_editText.getText().toString()) > Integer.parseInt(width_editText.getText().toString())) {
                        min_dim = Integer.parseInt(width_editText.getText().toString());
                    } else {
                        min_dim = Integer.parseInt(height_editText.getText().toString());
                    }

                    border_seekBar.setMax(min_dim / 2);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        width_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                width_editText.setText(Integer.toString(seekBar.getProgress()));//String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //positions Listener
        X_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(X_editText.getText().toString())){
                    //X_seekBar.setProgress(0);
                    //X_editText.setText("0");
                }
                else {
                    if(Integer.parseInt(X_editText.getText().toString())>screen_width-Integer.parseInt(width_editText.getText().toString())){
                        X_seekBar.setMax(screen_width-Integer.parseInt(width_editText.getText().toString()));
                        X_seekBar.setProgress(screen_width-Integer.parseInt(width_editText.getText().toString()));
                        X_editText.setText(Integer.toString(screen_height));
                    }
                    else{
                        X_seekBar.setProgress(Integer.parseInt(X_editText.getText().toString()));
                    }
                }
                X_editText.setSelection(X_editText.getText().length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        X_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                X_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Y_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(Y_editText.getText().toString())){
                    //Y_seekBar.setProgress(0);
                    //Y_editText.setText("0");
                }
                else {
                    if(Integer.parseInt(Y_editText.getText().toString())>screen_height-Integer.parseInt(height_editText.getText().toString())){
                        Y_seekBar.setMax(screen_height-Integer.parseInt(height_editText.getText().toString()));
                        Y_seekBar.setProgress(screen_height-Integer.parseInt(height_editText.getText().toString()));
                        Y_editText.setText(Integer.toString(screen_height));
                    }
                    else{
                        Y_seekBar.setProgress(Integer.parseInt(Y_editText.getText().toString()));
                    }
                }
                Y_editText.setSelection(Y_editText.getText().length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });

        Y_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Y_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //border Listener
        border_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(border_editText.getText().toString())){
                    //border_seekBar.setProgress(1);
                    //border_editText.setText("1");
                }
                else {
                    if(Integer.parseInt(border_editText.getText().toString())>Integer.parseInt(width_editText.getText().toString())/2){
                        border_seekBar.setMax(Integer.parseInt(width_editText.getText().toString())/2);
                        border_seekBar.setProgress(Integer.parseInt(width_editText.getText().toString())/2);
                        border_editText.setText(Integer.toString(Integer.parseInt(width_editText.getText().toString())/2));
                    }
                    else{
                        border_seekBar.setMax(Integer.parseInt(width_editText.getText().toString())/2);
                        border_seekBar.setProgress(Integer.parseInt(border_editText.getText().toString()));
                    }
                    //if(Integer.parseInt(border_editText.getText().toString())==0){
                       // border_editText.setText("0");
                       // border_seekBar.setMax(0);
                       // border_seekBar.setProgress(0);
                    //}
                }
                border_editText.setSelection(border_editText.getText().length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });


        border_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                border_editText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }//end OnCreate

    //Method to get size navigation bar to compute overall screen size
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

    //Method for checkbox fill
    public void onCheckedFill(View view){
        CheckBox fill_checkBox = (CheckBox) findViewById(R.id.fill_checkBox);
        EditText border_editText = (EditText) findViewById(R.id.border_editText);
        SeekBar border_seekBar = (SeekBar) findViewById(R.id.border_seekBar);

        if(fill_checkBox.isChecked()){
            border_editText.setEnabled(false);
            border_seekBar.setEnabled(false);
            border_editText.setText("0");
        }
        else{
            border_editText.setEnabled(true);
            border_seekBar.setEnabled(true);
            border_editText.setText("1");
        }
    }

    //Method for checked box Centre
    public void onCheckedCentre(View view){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int screen_width = size.x;
        final int screen_height = size.y + getNavigationBarHeight();

        CheckBox centre_checkBox = (CheckBox) findViewById(R.id.centre_checkBox);
        EditText X_editText = (EditText) findViewById(R.id.X_editText);
        EditText Y_editText = (EditText) findViewById(R.id.Y_editText);
        EditText height_editText = (EditText) findViewById(R.id.height_editText);
        EditText width_editText = (EditText) findViewById(R.id.width_editText);
        SeekBar X_seekBar = (SeekBar) findViewById(R.id.X_seekBar);
        SeekBar Y_seekBar = (SeekBar) findViewById(R.id.Y_seekBar);

        if(TextUtils.isEmpty(height_editText.getText().toString())){
            height_editText.setText("400");
        }

        if(TextUtils.isEmpty(width_editText.getText().toString())){
            width_editText.setText("400");
        }

        X_editText.setText(Integer.toString(X_seekBar.getMax()/2));
        Y_editText.setText(Integer.toString(Y_seekBar.getMax()/2));

        if(centre_checkBox.isChecked()){
            X_editText.setEnabled(false);
            Y_editText.setEnabled(false);
            X_seekBar.setEnabled(false);
            Y_seekBar.setEnabled(false);
        }
        else{
            X_editText.setEnabled(true);
            Y_editText.setEnabled(true);
            X_seekBar.setEnabled(true);
            Y_seekBar.setEnabled(true);
        }
    }

    //Method OK button
    public void onClickButton(View view) {
        Intent intent = new Intent(this, second_activity.class);

        EditText height_editText = (EditText) findViewById(R.id.height_editText);
        EditText width_editText = (EditText) findViewById(R.id.width_editText);
        EditText X_editText = (EditText) findViewById(R.id.X_editText);
        EditText Y_editText = (EditText) findViewById(R.id.Y_editText);
        EditText border_editText = (EditText) findViewById(R.id.border_editText);

        Spinner shape_spinner = (Spinner) findViewById(R.id.shape_spinner);
        if (shape_spinner.getSelectedItem().toString().equals("Picture")) {
            intent.putExtra("shape", shape_spinner.getSelectedItem().toString());
            startActivity(intent);

        } else {
            if (!TextUtils.isEmpty(height_editText.getText().toString()) &&
                    !TextUtils.isEmpty(width_editText.getText().toString()) &&
                    !TextUtils.isEmpty(X_editText.getText().toString()) &&
                    !TextUtils.isEmpty(Y_editText.getText().toString()) &&
                    !TextUtils.isEmpty(border_editText.getText().toString())) {

                if (Integer.parseInt(height_editText.getText().toString()) != 0 &&
                        Integer.parseInt(width_editText.getText().toString()) != 0) {
                    //Integer.parseInt(border_editText.getText().toString())!=0){

                    intent.putExtra("height", height_editText.getText().toString());
                    intent.putExtra("width", width_editText.getText().toString());
                    intent.putExtra("X", X_editText.getText().toString());
                    intent.putExtra("Y", Y_editText.getText().toString());
                    intent.putExtra("border", border_editText.getText().toString());


                    CheckBox fill_checkBox = (CheckBox) findViewById(R.id.fill_checkBox);
                    if (fill_checkBox.isChecked()) {
                        intent.putExtra("fill", "true");
                    } else {
                        intent.putExtra("fill", "false");
                    }

                    Spinner spinner = (Spinner) findViewById(R.id.shape_spinner);
                    intent.putExtra("shape", spinner.getSelectedItem().toString());

                    startActivity(intent);

                }
            }
        }
    }

}
