package com.example.sunnny.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.sunnny.calculator.R.id.btn_0;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private OperatorListener operator_listener;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.txt);
        initButton();

    }

    private void initButton() {
        operator_listener = new OperatorListener(editText);

        Button bu_0 = (Button) findViewById(R.id.btn_0);
        bu_0.setOnClickListener(this);
        Button bu_1 = (Button) findViewById(R.id.btn_1);
        bu_1.setOnClickListener(this);
        Button bu_2 = (Button) findViewById(R.id.btn_2);
        bu_2.setOnClickListener(this);
        Button bu_3 = (Button) findViewById(R.id.btn_3);
        bu_3.setOnClickListener(this);
        Button bu_4 = (Button) findViewById(R.id.btn_4);
        bu_4.setOnClickListener(this);
        Button bu_5 = (Button) findViewById(R.id.btn_5);
        bu_5.setOnClickListener(this);
        Button bu_6 = (Button) findViewById(R.id.btn_6);
        bu_6.setOnClickListener(this);
        Button bu_7 = (Button) findViewById(R.id.btn_7);
        bu_7.setOnClickListener(this);
        Button bu_8 = (Button) findViewById(R.id.btn_8);
        bu_8.setOnClickListener(this);
        Button bu_9 = (Button) findViewById(R.id.btn_9);
        bu_9.setOnClickListener(this);
        Button bu_add = (Button) findViewById(R.id.btn_add);
        bu_add.setOnClickListener(operator_listener);
        Button bu_minus = (Button) findViewById(R.id.btn_minus);
        bu_minus.setOnClickListener(operator_listener);
        Button bu_multiply = (Button) findViewById(R.id.btn_multiply);
        bu_multiply.setOnClickListener(operator_listener);
        Button bu_devide = (Button) findViewById(R.id.btn_devide);
        bu_devide.setOnClickListener(operator_listener);
        Button bu_equal = (Button) findViewById(R.id.btn_equal);
        bu_equal.setOnClickListener(operator_listener);
        Button bu_nut = (Button) findViewById(R.id.btn_nut);
        bu_nut.setOnClickListener(operator_listener);
        Button bu_sin = (Button) findViewById(R.id.btn_sin);
        bu_sin.setOnClickListener(operator_listener);
        Button bu_cos = (Button) findViewById(R.id.btn_cos);
        bu_cos.setOnClickListener(operator_listener);
        Button bu_left = (Button) findViewById(R.id.btn_left);
        bu_left.setOnClickListener(operator_listener);
        Button bu_right = (Button) findViewById(R.id.btn_right);
        bu_right.setOnClickListener(operator_listener);
        Button bu_ce = (Button) findViewById(R.id.btn_ce);
        bu_ce.setOnClickListener(operator_listener);
        Button bu_power = (Button) findViewById(R.id.btn_power);
        bu_power.setOnClickListener(operator_listener);
        Button bu_two = (Button) findViewById(R.id.btn_two);
        bu_two.setOnClickListener(operator_listener);
        Button bu_eight = (Button) findViewById(R.id.btn_eight);
        bu_eight.setOnClickListener(operator_listener);
        Button bu_sixteen = (Button) findViewById(R.id.btn_sixteen);
        bu_sixteen.setOnClickListener(operator_listener);
        Button bu_back = (Button) findViewById(R.id.btn_back);
        bu_back.setOnClickListener(operator_listener);
    }

    @Override
    public void onClick(View v) {
        String current_str = editText.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
                if (current_str.equals("") || current_str.charAt(current_str.length() - 1) != 0) {
                    editText.setText(current_str + ((Button) v).getText().toString());
                }
                break;
            default:
                editText.setText(current_str + ((Button) v).getText().toString());
                break;
        }

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
                Intent intent = new Intent(MainActivity.this,Conversion.class);
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
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
