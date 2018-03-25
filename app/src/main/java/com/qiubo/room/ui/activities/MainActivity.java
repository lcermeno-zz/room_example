package com.qiubo.room.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.qiubo.room.R;
import com.qiubo.room.entities.Developer;
import com.qiubo.room.presenters.IMainPresenter;
import com.qiubo.room.presenters.MainPresenter;
import com.qiubo.room.repositories.datasource.QiuboDatabase;
import com.qiubo.room.ui.adapters.QiuboAdapter;
import com.qiubo.room.views.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainView, QiuboAdapter.IOnClickListener {

    @BindView(R.id.rec_vw_main)
    RecyclerView mRecVwMain;
    @BindView(R.id.ed_txt_vw)
    EditText mEditText;

    private QiuboAdapter mAdapter;
    private IMainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupModule();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    private void setupModule() {
        mAdapter = new QiuboAdapter(this);
        mRecVwMain.setLayoutManager(new LinearLayoutManager(this));
        mRecVwMain.setAdapter(mAdapter);
        mPresenter = new MainPresenter(this);
        mPresenter.onCreate();
    }

    @OnClick(R.id.btn_add)
    public void saveDeveloper() {
        mPresenter.addDeveloper(mEditText.getText().toString());
        mRecVwMain.smoothScrollToPosition(QiuboAdapter.FIRST_POSITION);
    }

    @Override
    public void onGetDevelopers(List<Developer> developers) {
        runOnUiThread(() -> mAdapter.setItems(developers));
    }

    @Override
    public void onDeveloperAdded(Developer developer) {
        mAdapter.add(developer);
    }

    @Override
    public void onDeveloperDeleted(Developer developer) {
        runOnUiThread(() -> mAdapter.remove(developer));
    }

    @Override
    public void onDeveloperUpdated(Developer developer) {
        runOnUiThread(() -> mAdapter.update(developer));
    }

    @Override
    public void deleteDeveloper(Developer developer) {
        mPresenter.deleteDeveloper(developer);
    }

    @Override
    public void bookmarkDeveloper(Developer developer) {
        mPresenter.bookmarkDeveloper(developer);
    }
}
