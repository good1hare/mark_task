package com.example.bulat.marktask.activities.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.bulat.marktask.R;
import com.example.bulat.marktask.activities.register.RegActivity;
import com.example.bulat.marktask.rest.ApiService;

public class LoginActivity extends AppCompatActivity {

  @BindView(R.id.hello_text)
  TextView tvHelloText;
  @BindView(R.id.login)
  Button bLogin;
  @BindView(R.id.reg)
  Button bReg;
  ApiService mApiService = new ApiService();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    setListeners();
  }


  private void setListeners() {
    bLogin.setOnClickListener(view -> {
      //do login
    });
    bReg.setOnClickListener(view -> {
      //do reg
     startActivityForReg();
    });
  }

  private void startActivityForReg(){
    RegActivity.startActivityFromIntent(this);
  }

}
