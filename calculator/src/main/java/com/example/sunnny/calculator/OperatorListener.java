package com.example.sunnny.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import util.Calculator;

/**
 * Created by sunnny on 2016/9/1.
 */
public class OperatorListener implements View.OnClickListener {
    private Calculator my_cal;
    private EditText et_input_field;

    public OperatorListener(EditText editText) {
        et_input_field = editText;
    }

    @Override
    public void onClick(View v) {
        String current_str = "";
        switch (v.getId()) {
            case R.id.btn_equal:
                my_cal = new Calculator(et_input_field.getText().toString() + "#");
                et_input_field.setText(my_cal.getResult() + "");
                break;
            case R.id.btn_ce:
                et_input_field.setText("");
                break;
            case R.id.btn_two:
                current_str = et_input_field.getText().toString();
                //转化成二进制，显示在ET中
                et_input_field.setText(Integer.toBinaryString(Integer.parseInt(current_str)));
                break;
            case R.id.btn_eight:
                current_str = et_input_field.getText().toString();
                //转化成八进制，显示在ET中
                et_input_field.setText(Integer.toOctalString(Integer.parseInt(current_str)));
                break;
            case R.id.btn_sixteen:
                current_str = et_input_field.getText().toString();
                //转化成十六进制，显示在ET中
                et_input_field.setText(Integer.toHexString(Integer.parseInt(current_str)));
                break;
            default:
                et_input_field.setText(et_input_field.getText().toString() + ((Button) v).getText().toString());
                break;
        }
    }
}
