package com.example.bulat.marktask.rest;

import com.example.bulat.marktask.models.Status;
import com.example.bulat.marktask.models.Task;
import com.example.bulat.marktask.models.User;
import io.reactivex.Observable;
import java.util.List;

public class ApiService implements IApiService {
  @Override
  public Observable<User> reg(String mail, String password, String name) {
    return ApiClient
        .getRestClient()
        .reg(mail, password, name);
  }

  @Override
  public Observable<User> auth(String login, String password) {
    return ApiClient
        .getRestClient()
        .auth(login, password);
  }

  @Override
  public Observable<Status> create_task(String name, String desc, String exec) {
    return ApiClient
        .getRestClient()
        .create_task(name, desc, exec);
  }

  @Override
  public Observable<List<Task>> get_tasks(String exec) {
    return ApiClient
        .getRestClient()
        .get_tasks(exec);
  }
}
