package com.example.alphacols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.alphacols.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityMainBinding.editTextMaxNumber.getText().toString().isEmpty() || activityMainBinding.editTextMinNumber.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter Values!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    activityMainBinding.numberPicker.setVisibility(View.VISIBLE);
                    setupNumberPicker(Integer.parseInt(activityMainBinding.editTextMaxNumber.getText().toString().trim()),
                            Integer.parseInt(activityMainBinding.editTextMinNumber.getText().toString().trim()));}
            }
        });
        
    }

    private void setupNumberPicker(final int maxValue, final int minValue) {
        activityMainBinding.numberPicker.setMaxValue(maxValue);
        activityMainBinding.numberPicker.setMinValue(minValue);
        activityMainBinding.numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {

                if (activityMainBinding.checkBoxShowColumnNumber.isChecked()){
                    if (activityMainBinding.radioButtonReverseView.isChecked()){
                        return value+" "+getColName(maxValue+minValue-value);}

                    else { return value+" "+getColName(value); }
                }else{
                    if (activityMainBinding.radioButtonReverseView.isChecked()){
                        return getColName(maxValue+minValue-value);}

                    else { return getColName(value); }
                }
            }
        });
    }

    private String getColName(int col) {
        StringBuilder cv = new StringBuilder();
        while (col>0){
            int rem = col%26;
            if (rem==0){
                cv.append('Z');
                col = (col/26)-1;
            }
            else{
                cv.append((char) (64+rem));
                col = col/26;
            }
        }
        return cv.reverse().toString();
    }
}