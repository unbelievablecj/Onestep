package com.example.administrator.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.R;
import com.example.administrator.adapter.fenxiangkuangAdapter;
import com.example.administrator.util.fenxiangkuang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyshareActivity extends AppCompatActivity implements View.OnTouchListener {

    private EditText start_time;
    private EditText end_time;
    private List<fenxiangkuang> MyshareList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share);
        initMyshare();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.Myshareliebiao);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fenxiangkuangAdapter adapter = new fenxiangkuangAdapter(MyshareList);
        recyclerView.setAdapter(adapter);
        start_time=(EditText)findViewById(R.id.start_time);
        end_time=(EditText)findViewById(R.id.end_time);
        start_time.setOnTouchListener(this);
        end_time.setOnTouchListener(this);
    }
    private void initMyshare(){
        fenxiangkuang a=new fenxiangkuang("旅法师","福州大学","2018.11.27","104","26",R.drawable.fuzhoudaxue,R.drawable.touxianglvfa,R.drawable.dianzan1,R.drawable.pinglun,R.drawable.shoucan);
        MyshareList.add(a);
        fenxiangkuang c=new fenxiangkuang("血大板","师大学生街","2018.11.21","170","46",R.drawable.naicha,R.drawable.longnvpu,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
        MyshareList.add(c);
        fenxiangkuang b=new fenxiangkuang("利威尔兵短","闽江学院","2018.11.25","156","32",R.drawable.malatang,R.drawable.touxiangbinzhang,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
        MyshareList.add(b);

    }
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.timeset, null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
            builder.setView(view);

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);

            if (v.getId() == R.id.start_time) {
                final int inType = start_time.getInputType();
                start_time.setInputType(InputType.TYPE_NULL);
                start_time.onTouchEvent(event);
                start_time.setInputType(inType);
                start_time.setSelection(start_time.getText().length());

                builder.setTitle("选择起始时间");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        start_time.setText(sb);
                        end_time.requestFocus();
                        dialog.cancel();
                    }

                });
            } else if (v.getId() == R.id.end_time) {
                int inType = end_time.getInputType();
                end_time.setInputType(InputType.TYPE_NULL);
                end_time.onTouchEvent(event);
                end_time.setInputType(inType);
                end_time.setSelection(end_time.getText().length());
                builder.setTitle("选择结束时间");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        end_time.setText(sb);
                        dialog.cancel();
                    }
                });
            }
            Dialog dialog = builder.create();
            dialog.show();
        }
        return true;
    }
}
