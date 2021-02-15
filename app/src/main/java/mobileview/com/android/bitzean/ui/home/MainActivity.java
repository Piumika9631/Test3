package mobileview.com.android.bitzean.ui.home;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mobileview.com.android.bitzean.R;
import mobileview.com.android.bitzean.data.GroupItem;

public class MainActivity extends AppCompatActivity {

    private HomeViewModel mHomeViewModel;
    private int mLimit = 5;
    private int mOffset = 0;
    private boolean mLoading = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.a_main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GroupsAdaptor groupsAdaptor = new GroupsAdaptor(MainActivity.this, new ArrayList<>());
        mHomeViewModel = new ViewModelProvider(this, new HomeViewModelFactory())
                .get(HomeViewModel.class);
        mHomeViewModel.fetchGroups(mLimit, mOffset);
        mHomeViewModel.getGroupListItem().observe(this, new Observer<List<GroupItem>>() {
            @Override
            public void onChanged(List<GroupItem> groupItems) {
                groupsAdaptor.addGroups(groupItems);
                groupsAdaptor.notifyDataSetChanged();
                mLoading = false;
            }
        });
        recyclerView.setAdapter(groupsAdaptor);

        groupsAdaptor.setOnBottomReachedListener(new GroupsAdaptor.OnBottomReachedListener() {
            @Override
            public void onBottomReached() {
                if (mLoading) {
                    return;
                }
                mOffset += mLimit;
                mLoading = true;
                mHomeViewModel.fetchGroups(mLimit, mOffset);
            }
        });
    }

}
