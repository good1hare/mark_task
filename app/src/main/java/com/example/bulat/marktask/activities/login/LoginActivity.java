package com.example.bulat.marktask.activities.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.bulat.marktask.R;
import com.example.bulat.marktask.activities.main.MainActivity;
import com.example.bulat.marktask.activities.register.RegActivity;
import com.example.bulat.marktask.models.User;
import com.example.bulat.marktask.rest.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

  @BindView(R.id.tvLogin)
  TextView tvLogin;
  @BindView(R.id.tvPassword)
  TextView tvPassword;
  @BindView(R.id.etLogin)
  EditText etLogin;
  @BindView(R.id.etPassword)
  EditText etPassword;

  @BindView(R.id.bLogin)
  Button bLogin;

  @BindView(R.id.bReg)
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
      if (!etLogin.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
        mApiService
            .auth(etLogin.getText().toString(), etPassword.getText().toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::startMain, throwable -> {
              throwable.printStackTrace();
              toastError();
            });
      }else {
        toastError();
      }
    });

    bReg.setOnClickListener(view -> {
      //do reg
     startActivityForReg();
    });
  }

  private void startMain(User user) {
    MainActivity.startActivityFromIntent(this);
  }

  private void startActivityForReg(){
    RegActivity.startActivityFromIntent(this);
  }

  private void toastError(){
    Toast.makeText(this, R.string.reg_error, Toast.LENGTH_SHORT).show();
  }
}
