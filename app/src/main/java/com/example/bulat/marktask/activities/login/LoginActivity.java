package com.example.bulat.marktask.activities.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.example.bulat.marktask.R;

public class LoginActivity extends AppCompatActivity {

  @BindView(R.id.hello_text)
  TextView tvHelloText;
  @BindView(R.id.sign_in_button)
  View signInButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }
}
