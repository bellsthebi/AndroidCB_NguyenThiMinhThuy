package com.example.baithuchanhlap1_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextResult;
    private double operand1 = Double.NaN;
    private double operand2;
    private String currentOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextResult = findViewById(R.id.editTextResult);

        Button[] numberButtons = new Button[10];
        for (int i = 0; i < numberButtons.length; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            numberButtons[i] = findViewById(id);
            numberButtons[i].setOnClickListener(numberClickListener);
        }

        findViewById(R.id.buttonPlus).setOnClickListener(operationClickListener);
        findViewById(R.id.buttonMinus).setOnClickListener(operationClickListener);
        findViewById(R.id.buttonMultiply).setOnClickListener(operationClickListener);
        findViewById(R.id.buttonDivide).setOnClickListener(operationClickListener);
        findViewById(R.id.buttonMod).setOnClickListener(operationClickListener);
        findViewById(R.id.buttonClear).setOnClickListener(v -> clear());
        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculate());
    }

    private final View.OnClickListener numberClickListener = v -> {
        Button button = (Button) v;
        editTextResult.append(button.getText().toString());
    };

    private final View.OnClickListener operationClickListener = v -> {
        Button button = (Button) v;
        if (!Double.isNaN(operand1)) {
            operand2 = Double.parseDouble(editTextResult.getText().toString());
            calculate();
        } else {
            operand1 = Double.parseDouble(editTextResult.getText().toString());
        }
        currentOperation = button.getText().toString();
        editTextResult.setText("");
    };

    private void calculate() {
        if (!Double.isNaN(operand1)) {
            operand2 = Double.parseDouble(editTextResult.getText().toString());
            double result = 0;
            switch (currentOperation) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
                case "%":
                    result = operand1 % operand2;
                    break;
            }
            editTextResult.setText(String.valueOf(result));
            operand1 = result;
            currentOperation = "";
        }
    }

    private void clear() {
        editTextResult.setText("");
        operand1 = Double.NaN;
        operand2 = 0;
        currentOperation = "";
    }
}
