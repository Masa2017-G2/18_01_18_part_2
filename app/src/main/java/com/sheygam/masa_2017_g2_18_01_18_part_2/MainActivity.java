package com.sheygam.masa_2017_g2_18_01_18_part_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {
    private ILoginPresenter presenter;
    private Button regBtn;
    private EditText inputEmail, inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        LoginWebRepository webRepository = new LoginWebRepository(gson);
        presenter = new LoginPresenter(this,webRepository,null);
        regBtn = findViewById(R.id.reg_btn);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        regBtn.setOnClickListener(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.reg_btn){
            presenter.onRegistration(inputEmail.getText().toString(),inputPassword.getText().toString());
        }
    }
}
