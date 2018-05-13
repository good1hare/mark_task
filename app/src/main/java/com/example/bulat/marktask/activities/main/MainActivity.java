package com.example.bulat.marktask.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.bulat.marktask.R;
import com.example.bulat.marktask.activities.fragment.TasksFragment;
import com.example.bulat.marktask.models.Status;
import com.example.bulat.marktask.rest.ApiService;
import com.example.bulat.marktask.utils.PreferenceManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  NavigationView navigationView;

  private TextView tvUserName;
  private TextView tvUserEmail;

  private String mUserName;
  private String mUserEmail;

  private TasksFragment mTasksFragment;
  private FragmentManager mFragmentManager;

  FloatingActionButton plus;

  ApiService mApiService = new ApiService();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //ButterKnife.bind(this);



    navigationView = findViewById(R.id.nav_view);
    plus = findViewById(R.id.plus);
    navigationView.setNavigationItemSelectedListener(this);
    initComponents();
    tvUserName.setText(mUserName);
    tvUserEmail.setText(mUserEmail);

    mFragmentManager = getSupportFragmentManager();

    mTasksFragment = new TasksFragment();
    mFragmentManager.beginTransaction().replace(R.id.fragment_container, mTasksFragment).commit();
    plus.setOnClickListener(view -> {
      Snackbar.make(view, "Создание задания", Snackbar.LENGTH_LONG).setAction("Action", null).show();
      showAlertStepOne();
    });
  }

  public static void startActivityFromIntent(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    switch(item.getItemId()){
      case R.id.nav_main:
        break;
      case R.id.nav_exit:
        finish();
        break;
    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void initComponents() {
    navigationView.setNavigationItemSelectedListener(this);
    tvUserEmail = navigationView.getHeaderView(0)
        .findViewById(R.id.tvEmail);
    tvUserName = navigationView.getHeaderView(0)
        .findViewById(R.id.tvName);

    mUserName = PreferenceManager.getUserName(this);
    mUserEmail = PreferenceManager.getUserEmail(this);
  }

  public void showAlertStepOne() {
    final AlertDialog.Builder alert = new AlertDialog.Builder(this);
    final EditText editText = new EditText(this);
    editText.setInputType(InputType.TYPE_CLASS_TEXT);
    alert.setTitle("Введите название задания");

    alert.setView(editText);

    alert.setPositiveButton("Далее", (dialog, whichButton) -> {
      if (editText.getText().toString().isEmpty()){
        showToastError();
        dialog.dismiss();
      }else{
        showAlertStepSecond(editText.getText().toString());
      }
    });

    alert.setNegativeButton("Отмена", (dialog, whichButton) -> {
      dialog.dismiss();
    });

    alert.show();
  }

  public void showAlertStepSecond(String taskName) {
    final AlertDialog.Builder alert = new AlertDialog.Builder(this);
    final EditText editText = new EditText(this);
    editText.setInputType(InputType.TYPE_CLASS_TEXT);
    alert.setTitle("Введите описание задания");

    alert.setView(editText);

    alert.setPositiveButton("Далее", (dialog, whichButton) -> {
      showAlertStepThree(taskName, editText.getText().toString());
    });

    alert.setNegativeButton("Отмена", (dialog, whichButton) -> {
      dialog.dismiss();
    });

    alert.show();
  }

  public void showAlertStepThree(String taskName, String taskDesc) {
    final AlertDialog.Builder alert = new AlertDialog.Builder(this);
    final EditText editText = new EditText(this);
    editText.setInputType(InputType.TYPE_CLASS_TEXT);
    alert.setTitle("Введите фамилию и имя исполнителя");

    alert.setView(editText);

    alert.setPositiveButton("Создать", (dialog, whichButton) -> {
      //do
      createTask(taskName, taskDesc, editText.getText().toString());
    });

    alert.setNegativeButton("Отмена", (dialog, whichButton) -> {
      dialog.dismiss();
    });

    alert.show();
  }

  public void createTask(String taskName, String taskDesc, String taskExec){
    if (!taskName.isEmpty()) {
      mApiService
          .create_task(taskName, taskDesc, taskExec)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(this::showToast, throwable -> {
            throwable.printStackTrace();
            //b
            // showToastError();
          });
    }else {
      showToastError();
    }
  }

  private void showToast(Status status) {
    if (status.getError().equals("0")){
      Toast.makeText(this, "Задание успешно создано", Toast.LENGTH_SHORT).show();
    }else {
      Toast.makeText(this, "Ой, на облаке что то не так :(", Toast.LENGTH_SHORT).show();
    }
  }

  private void showToastError() {
    Toast.makeText(this, "Не удалось т.к. нет названия", Toast.LENGTH_SHORT).show();
  }
}
