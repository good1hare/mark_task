package com.example.bulat.marktask.rest;

import com.example.bulat.marktask.models.User;
import io.reactivex.Observable;

public class ApiService implements IApiService {
  @Override
  public Observable<User> auth(String token) {
    return ApiClient
        .getRestClient()
        .auth(token);
  }
}
