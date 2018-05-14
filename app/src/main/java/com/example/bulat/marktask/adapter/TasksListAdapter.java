package com.example.bulat.marktask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.bulat.marktask.R;
import com.example.bulat.marktask.models.Task;
import java.util.List;

public class TasksListAdapter extends BaseAdapter {

  @BindView(R.id.tvName)
  TextView itemName;
  @BindView(R.id.tvExec)
  TextView itemExec;
  @BindView(R.id.tvDesc)
  TextView itemDesc;
  private List mListObjectTWO;

  public TasksListAdapter(List objectTWO, Context context) {
    this.mListObjectTWO = objectTWO;
  }

  @Override
  public int getCount() {
    return mListObjectTWO.size();
  }

  @Override
  public Object getItem(int position) {
    return mListObjectTWO.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    Task task = (Task) mListObjectTWO.get(position);
    if (convertView == null) {
      convertView = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_task, parent, false);
    }
    ButterKnife.bind(this, convertView);

    itemName.setText(task.getName());
    itemExec.setText("Исполнитель: " + task.getExec());
    itemDesc.setText("Описание: " + task.getDesc());
    return convertView;
  }
}
