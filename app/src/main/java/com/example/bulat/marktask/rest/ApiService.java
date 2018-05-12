package com.example.bulat.marktask.rest;

import com.example.bulat.marktask.models.User;
import io.reactivex.Observable;

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
}
