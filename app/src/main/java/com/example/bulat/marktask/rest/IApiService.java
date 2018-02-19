package com.example.bulat.marktask.rest;

import com.example.bulat.marktask.models.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;


public interface IApiService {

  @POST("auth")
  Observable<User> auth(
      @Field("token") String appId
  );
}
