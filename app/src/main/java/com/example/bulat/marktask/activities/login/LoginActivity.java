package com.example.bulat.marktask.activities.login;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.bulat.marktask.R;
import com.example.bulat.marktask.utils.ConstantsManager;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

  @BindView(R.id.hello_text)
  TextView tvHelloText;
  @BindView(R.id.sign_in_button)
  View signInButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    setListeners();
  }

  private void setListeners() {
    signInButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
            false, null, null, null, null);
        startActivityForResult(intent, 123);

      }
    });
  }

  protected void onActivityResult(final int requestCode, final int resultCode,
      final Intent data) {
    if (requestCode == 123 && resultCode == RESULT_OK) {
      final String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
      @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, String> getToken = new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
          try {
            String token = GoogleAuthUtil.getToken(LoginActivity.this, accountName,
                ConstantsManager.SCOPES);
            return token;

          } catch (UserRecoverableAuthException userAuthEx) {
            startActivityForResult(userAuthEx.getIntent(), 123);
          }  catch (IOException ioEx) {
            Log.d("OAuth", "IOException");
          }  catch (GoogleAuthException fatalAuthEx)  {
            Log.d("OAuth", "Fatal Authorization Exception" + fatalAuthEx.getLocalizedMessage());
          }
          return null;
        }

        @Override
        protected void onPostExecute(String token) {
          //reg(token);
          //Intent i = new Intent(LoginActivity.this, MainActivity.class);
          //startActivity(i);
        }

      };
      getToken.execute(null, null, null);
    }
  }
}
