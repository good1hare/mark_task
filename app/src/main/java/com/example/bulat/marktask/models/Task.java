package com.example.bulat.marktask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

  @SerializedName("task_id")
  @Expose
  private String mId;

  @SerializedName("task_name")
  @Expose
  private String mName;

  @SerializedName("task_desc")
  @Expose
  private String mDesc;

  @SerializedName("task_exec")
  @Expose
  private String mExec;

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

  public String getDesc() {
    return mDesc;
  }

  public void setDesc(String desc) {
    mDesc = desc;
  }

  public String getExec() {
    return mExec;
  }
}
