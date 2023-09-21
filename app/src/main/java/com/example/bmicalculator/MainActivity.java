package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private Button calculateButton;
    private TextView bmiResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightEditText.getText().toString();
        String heightStr = heightEditText.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            bmiResultTextView.setText("Please enter weight and height.");
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr) / 100; // Convert height to meters

        if (height <= 0 || weight <= 0) {
            bmiResultTextView.setText("Please enter valid weight and height.");
            return;
        }

        double bmi = weight / (height * height);
        displayResult(bmi);
    }

    private void displayResult(double bmi) {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = "Your BMI is " + df.format(bmi);

        if (bmi < 18.5) {
            result += "\nUnderweight";
        } else if (bmi < 24.9) {
            result += "\nNormal Weight";
        } else if (bmi < 29.9) {
            result += "\nOverweight";
        } else {
            result += "\nObese";
        }

        bmiResultTextView.setText(result);
    }
}
