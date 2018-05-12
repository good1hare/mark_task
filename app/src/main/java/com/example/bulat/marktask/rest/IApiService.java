package com.example.bulat.marktask.rest;

import com.example.bulat.marktask.models.Status;
import com.example.bulat.marktask.models.User;
import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface IApiService {

  @FormUrlEncoded
  @POST("reg")
  Observable<User> reg(
      @Field("mail") String mail,
      @Field("password") String password,
      @Field("name") String name
  );

  @FormUrlEncoded
  @POST("auth")
  Observable<User> auth(
      @Field("login") String login,
      @Field("password") String password
  );

  @FormUrlEncoded
  @POST("create_task")
  Observable<Status> create_task(
      @Field("task_name") String name,
      @Field("task_desc") String desc,
      @Field("task_exec") String exec
  );
}
