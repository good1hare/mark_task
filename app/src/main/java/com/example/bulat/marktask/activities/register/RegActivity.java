package com.example.bulat.marktask.activities.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.bulat.marktask.R;
import com.example.bulat.marktask.models.User;
import com.example.bulat.marktask.rest.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegActivity extends AppCompatActivity {

  @BindView(R.id.tvMail)
  TextView tvMail;
  @BindView(R.id.tvPassword)
  TextView tvPassword;
  @BindView(R.id.tvSecondPassword)
  TextView tvSecondPassword;
  @BindView(R.id.etMail)
  EditText etMail;
  @BindView(R.id.etPassword)
  EditText etPassword;
  @BindView(R.id.etSecondPassword)
  EditText etSecondPassword;
  @BindView(R.id.reg)
  Button bReg;
  @BindView(R.id.cancel)
  Button bCancel;
  ApiService mApiService = new ApiService();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reg);
    ButterKnife.bind(this);
    setListeners();
  }

  public static void startActivityFromIntent(Context context) {
    Intent intent = new Intent(context, RegActivity.class);
    context.startActivity(intent);
  }


  private void setListeners() {
    bReg.setOnClickListener(view -> {
      //do reg
      if (etPassword.getText().toString().equals(etSecondPassword.getText().toString()) && !etPassword.getText().toString().isEmpty()) {
        mApiService
            .reg(etMail.getText().toString(), etPassword.getText().toString())
//              .map(ResponceUsers::getAllUsers)
//              .map(users -> users.get(0))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //.doOnTerminate(mLoginView::hideProgress)
            .subscribe(this::finishReg, throwable -> {
              throwable.printStackTrace();
              toastError();
            });
      }

    });
    bCancel.setOnClickListener(view -> finish());
  }

  private void finishReg(User user) {
    //start activity
  }
  
  private void toastError(){
    Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
  }

}
