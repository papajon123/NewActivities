package com.cool.alex123.newactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class SetterActivity extends AppCompatActivity {
    public static final String LOGIN = "com.cool.alex123.login";
    public static final String PASS = "com.cool.alex123.pass";
    public static final String BORN = "com.cool.alex123.born";
    private EditText login;
    private EditText pass;
    private DatePicker dateBorn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setter);
        initComponents();
    }

    private void initComponents() {
        initToolBar();
        initFloatingActionButton();
        login = (EditText) findViewById(R.id.content_setter_edit_text_login);
        pass = (EditText) findViewById(R.id.content_setter_edit_text_pass);
        dateBorn = (DatePicker) findViewById(R.id.content_setter_date_picker_born);
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(LOGIN,login.getText().toString());
                intent.putExtra(PASS,pass.getText().toString());
                Date date = new Date(dateBorn.getYear(),dateBorn.getMonth(),dateBorn.getDayOfMonth());
                intent.putExtra(BORN,date);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
