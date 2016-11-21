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
        switch (v.getId()) {
            case R.id.btn_equal:
                //预先处理
                String realCal = preHandle(et_input_field.getText().toString());
                my_cal = new Calculator(et_input_field.getText().toString() + "#");
                MainActivity.LastInputStr = my_cal+"";
                et_input_field.setText(my_cal.getResult() + "");
                break;
            case R.id.btn_back:
                String s = et_input_field.getText().toString();
                if (s.charAt(s.length()-1) == 'N' || s.charAt(s.length()-1) == 'n' || s.charAt(s.length()-1) == 'S' || s.charAt(s.length()-1) == 's'){
                    et_input_field.setText(s.substring(0,s.length()-3));
                }else {
                    et_input_field.setText(s.substring(0,s.length()-1));
                }
                break;
            case R.id.btn_ce:
                MainActivity.LastInputStr = "";
                et_input_field.setText("");
                break;
            case R.id.btn_two:
                //转化成二进制，显示在ET中
                et_input_field.setText(Integer.toBinaryString(Integer.parseInt(MainActivity.LastInputStr)));
                break;
            case R.id.btn_eight:
                //转化成八进制，显示在ET中
                et_input_field.setText(Integer.toOctalString(Integer.parseInt(MainActivity.LastInputStr)));
                break;
            case R.id.btn_sixteen:
                //转化成十六进制，显示在ET中
                et_input_field.setText(Integer.toHexString(Integer.parseInt(MainActivity.LastInputStr)));
                break;
            default:
                et_input_field.setText(et_input_field.getText().toString() + ((Button) v).getText().toString());
                break;
        }
    }

    private String preHandle(String cal_equation){
        //判空处理
        if (cal_equation == null || cal_equation.equals(""))
            return null;
        //处理正弦余弦的计算，先计算出正弦、余弦，然后合并成一个普通的计算式子
        String real_equation = "";
        if (!cal_equation.contains("SIN") && !cal_equation.equals("COS"))
            return real_equation;
        return null;
    }
}
