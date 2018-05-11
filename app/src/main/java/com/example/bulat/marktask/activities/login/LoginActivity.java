package com.example.bulat.marktask.activities.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.bulat.marktask.R;

public class LoginActivity extends AppCompatActivity {

  @BindView(R.id.hello_text)
  TextView tvHelloText;
  @BindView(R.id.login)
  Button bLogin;
  @BindView(R.id.reg)
  Button bReg;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    setListeners();
  }


  private void setListeners() {
    bLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //do login
      }
    });
    bReg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //do reg
      }
    });
    tvHelloText.setOnClickListener((new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //doAuth
      }
    }));
  }

}
