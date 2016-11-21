package com.example.sunnny.calculator;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversion extends AppCompatActivity implements View.OnClickListener{

    private Button btn_length;
    private Button btn_weight;
    private Button btn_col;
    private EditText et_length_cm;
    private EditText et_length_m;
    private EditText et_length_km;
    private EditText et_weight_g;
    private EditText et_weight_kg;
    private EditText et_weight_t;
    private EditText et_col_cm;
    private EditText et_col_m;
    private EditText et_col_km;
    private Button btn_length_clear;
    private Button btn_weight_clear;
    private Button btn_col_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        btn_length = (Button) this.findViewById(R.id.trans_len);
        btn_weight = (Button) this.findViewById(R.id.trans_wei);
        btn_col = (Button) this.findViewById(R.id.trans_col);
        et_length_cm = (EditText) findViewById(R.id.cm_value);
        et_length_m = (EditText) findViewById(R.id.m_value);
        et_length_km = (EditText) findViewById(R.id.km_value);
        et_weight_g = (EditText) findViewById(R.id.g_value);
        et_weight_kg = (EditText) findViewById(R.id.kg_value);
        et_weight_t = (EditText) findViewById(R.id.t_value);
        et_col_cm = (EditText) findViewById(R.id.cm_3_value);
        et_col_m = (EditText) findViewById(R.id.m_3_value);
        et_col_km = (EditText) findViewById(R.id.km_3_value);
        btn_length_clear = (Button) findViewById(R.id.btn_length_clear);
        btn_weight_clear = (Button) findViewById(R.id.btn_weight_clear);
        btn_col_clear = (Button) findViewById(R.id.btn_col_clear);

        btn_length.setOnClickListener(this);
        btn_weight.setOnClickListener(this);
        btn_col.setOnClickListener(this);
        btn_length_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_length_cm.setText("");
                et_length_m.setText("");
                et_length_km.setText("");
            }
        });

        btn_weight_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_weight_g.setText("");
                et_weight_kg.setText("");
                et_weight_t.setText("");
            }
        });

        btn_col_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_col_cm.setText("");
                et_col_m.setText("");
                et_col_km.setText("");
            }
        });
    }
    /**
     * 为下拉菜单添加监听事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_transfer:
                Intent intent = new Intent(Conversion.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载OptionMenu需要重写该方法，指定加载的menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trans,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        String regex = "[0-9]+\\.?[0-9]+";
        AlertDialog.Builder builder_input = new AlertDialog.Builder(this);
        builder_input.setTitle("请进行正确格式的输入").setPositiveButton("确定",null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请仅输入其中的一个值").setPositiveButton("确定",null);
        switch (v.getId()){
            case R.id.trans_len:
                String cm = et_length_cm.getText().toString();
                String m = et_length_m.getText().toString();
                String km = et_length_km.getText().toString();
                if ((!cm.equals("") && !m.equals(""))||(!cm.equals("") && !km.equals(""))||(!m.equals("") && !km.equals(""))){
                    builder.show();
                }else if (!cm.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(cm);
                    if (matcher.matches()){
                        double value = Double.parseDouble(cm);
                        et_length_m.setText(value/100.0+"");
                        et_length_km.setText(value/100000.0+"");
                    }else {
                        builder_input.show();
                    }
                }else if (!m.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(m);
                    if (matcher.matches()){
                        double value = Double.parseDouble(m);
                        et_length_cm.setText(value*100.0+"");
                        et_length_km.setText(value/1000.0+"");
                    }else {
                        builder_input.show();
                    }
                }else if (!km.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(km);
                    if (matcher.matches()){
                        double value = Double.parseDouble(km);
                        et_length_cm.setText(value*100000.0+"");
                        et_length_m.setText(value*1000.0+"");
                    }else {
                        builder_input.show();
                    }
                }
                break;
            case R.id.trans_wei:
                String g = et_weight_g.getText().toString();
                String kg = et_weight_kg.getText().toString();
                String t = et_weight_t.getText().toString();
                if ((!g.equals("") && !kg.equals(""))||(!g.equals("") && !t.equals(""))||(!kg.equals("") && !t.equals(""))){
                    builder.show();
                }else if (!g.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(g);
                    if (matcher.matches()){
                        double value = Double.parseDouble(g);
                        et_weight_kg.setText(value/1000.0+"");
                        et_weight_t.setText(value/1000000.0+"");
                    }else {
                        builder_input.show();
                    }
                }else if (!kg.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(kg);
                    if (matcher.matches()){
                        double value = Double.parseDouble(kg);
                        et_weight_g.setText(value*1000.0+"");
                        et_weight_t.setText(value/1000.0+"");
                    }else {
                        builder_input.show();
                    }
                }else if (!t.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(t);
                    if (matcher.matches()){
                        double value = Double.parseDouble(t);
                        et_weight_g.setText(value*1000000.0+"");
                        et_weight_t.setText(value*1000.0+"");
                    }else {
                        builder_input.show();
                    }
                }
                break;
            case R.id.trans_col:
                String cm3 = et_col_cm.getText().toString();
                String m3 = et_col_m.getText().toString();
                String km3 = et_col_km.getText().toString();
                if ((!cm3.equals("") && !m3.equals(""))||(!cm3.equals("") && !km3.equals(""))||(!m3.equals("") && !km3.equals(""))){
                    builder.show();
                }else if (!cm3.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(cm3);
                    if (matcher.matches()){
                        double value = Double.parseDouble(cm3);
                        et_col_m.setText(value/1000000.0+"");
                        et_col_km.setText(value/1000000000.0+"");
                    }else {
                        builder_input.show();
                    }
                }else if (!m3.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(m3);
                    if (matcher.matches()){
                        double value = Double.parseDouble(m3);
                        et_col_cm.setText(value*1000000.0+"");
                        et_col_km.setText(value/1000000.0+"");
                    }else {
                        builder_input.show();
                    }
                }else if (!km3.equals("")){
                    Matcher matcher = Pattern.compile(regex).matcher(km3);
                    if (matcher.matches()){
                        double value = Double.parseDouble(km3);
                        et_col_cm.setText(value*1000000000.0+"");
                        et_col_m.setText(value*1000000.0+"");
                    }else {
                        builder_input.show();
                    }
                }
                break;
        }
    }
}
