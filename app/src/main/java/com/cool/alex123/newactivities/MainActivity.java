package com.cool.alex123.newactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_SETTER=123;
    private Button buttonRead;
    private EditText editTextCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        initToolBar();
        initFloatingActionButton();
        initButton();
        editTextCredentials = (EditText) findViewById(R.id.content_main_edit_text_credentials);
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initButton(){
        buttonRead = (Button) findViewById(R.id.content_main_button_read);
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SetterActivity.class);
                /*startActivityForResult предназнгачается для запуска нового активити
                с ожиданием от него результата, в данном случае ожидание логин пароль и дату рождения
                аргументы: intent - объект содержащий активити которое хотим запустить
                REQUEST_SETTER - заранее объявленная в классе MainActivity константа
                характерезующая индентификатор запуска активити.
                Предназначается для того, чтобы в дальнейшем когда активити будет давать нам результат
                мы смогли индентифицировать от какого запроса пришёл к нам результат
                 */
                startActivityForResult(intent,REQUEST_SETTER);
            }
        });
    }
    /*
    onActivityResult - метод который принимает ответы от всех активити для которых ожидается результат.
    Аргументы:
    requestCode - принимает код запроса активити от которого пришёл ответ
    resultCode -  принимает код результата (RESULT_OK - если пользователь удачно завершил активити,
    resultCancelled - если пользователь отклонил активити нажав кнопку назад )
        */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case REQUEST_SETTER:
                    String login = data.getStringExtra(SetterActivity.LOGIN);
                    String pass = data.getStringExtra(SetterActivity.PASS);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
                    Date date = (Date) data.getSerializableExtra(SetterActivity.BORN);
                    editTextCredentials.setText(login+" "+pass+" "+simpleDateFormat.format(date));
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
