package com.example.bulat.marktask.activities.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.example.bulat.marktask.R;
import com.example.bulat.marktask.adapter.TasksListAdapter;
import com.example.bulat.marktask.models.Task;
import com.example.bulat.marktask.rest.ApiService;
import com.example.bulat.marktask.utils.PreferenceManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class TasksFragment extends Fragment {

  private SwipeRefreshLayout mSwipeView;
  TextView tvNoTasks;
  private List<Task> mTasksList;
  private Context mContext;
  private ListView mListViewWRInWork;
  private View mViewTasks;
  ApiService mApiService = new ApiService();

  private Handler mHandler = new Handler();
  private Runnable getAllTasks = new Runnable() {
    @Override
    public void run() {
      mHandler.postDelayed(this, 1000 * 5);
    }
  };

  public TasksFragment() {
    //Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    mContext = context;
    super.onAttach(context);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    setRetainInstance(true);
    mViewTasks = inflater.inflate(R.layout.fragment_tasks, container, false);
    tvNoTasks = mViewTasks.findViewById(R.id.text_no_tasks);
    initComponents();
    clickListener();
    return mViewTasks;
  }

  private void initComponents() {
    mListViewWRInWork = (ListView) mViewTasks.findViewById(R.id.lv_tasks);
    mSwipeView = (SwipeRefreshLayout) mViewTasks.findViewById(R.id.swipe_container);
    mSwipeView.setColorSchemeColors(Color.GRAY, Color.GREEN, Color.BLUE,
        Color.RED, Color.CYAN);
    mSwipeView.setDistanceToTriggerSync(20);// in dips
    mSwipeView.setSize(SwipeRefreshLayout.DEFAULT);// LARGE also can be used

    getAllTasks();
  }

  private void clickListener() {

    //mListViewWRInWork.setOnItemClickListener((parent, view, position, id) ->
     //   mPresenter.listViewItemClick(mListObjectTWO.get(position), mListObjectTWO));

    mSwipeView.setOnRefreshListener(() -> moveSwipe());
  }

  private void moveSwipe() {
    mSwipeView.postDelayed(() -> {
      mSwipeView.setRefreshing(true);
      //do get tasks
      getAllTasks();
    }, 500);
  }

  private void getAllTasks() {
    mApiService.get_tasks(PreferenceManager.getUserName(getContext()))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(tasks -> {
          populateAdapter(tasks);
          mSwipeView.postDelayed(() -> mSwipeView.setRefreshing(false), 500);
        }, throwable ->
        {
          throwable.printStackTrace();
        });
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
  }


  public void populateAdapter(List<Task> tasks) {
    if (getActivity() != null) {
      if (mContext != null) {
//        Type collectionType = new TypeToken<List<Task>>(){}.getType();
//        List<Task> mTasks = (List<channelSearchEnum>) new Gson()
//            .fromJson( jstring , collectionType);
        //mTasksList = tasks.getAllTask();
        mTasksList = tasks;
        tvNoTasks.setVisibility(View.GONE);
        mListViewWRInWork.setAdapter(null);
        TasksListAdapter wrTasksListAdapter = new TasksListAdapter(mTasksList, getActivity());
        mListViewWRInWork.setAdapter(wrTasksListAdapter);
        mListViewWRInWork.requestLayout();
      }
    }
  }

}
