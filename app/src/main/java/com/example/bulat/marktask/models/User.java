package com.example.bulat.marktask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

  @SerializedName("user_id")
  @Expose
  private String mId;

  @SerializedName("user_name")
  @Expose
  private String mName;

  @SerializedName("email")
  @Expose
  private String mEmail;

  @SerializedName("error")
  @Expose
  private String mError;

  public String getId() {
    return mId;
  }

  public void setId(String id) {
    mId = id;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public String getEmail() {
    return mEmail;
  }

  public void setEmail(String email) {
    mEmail = email;
  }

  public String getError() {
    return mError;
  }
}
