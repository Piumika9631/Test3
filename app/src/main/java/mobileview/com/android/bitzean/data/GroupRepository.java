package mobileview.com.android.bitzean.data;

import android.util.Log;

import java.security.acl.Group;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import mobileview.com.android.bitzean.network.GroupListAPI;
import mobileview.com.android.bitzean.network.GroupResponse;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GroupRepository {

    private static volatile GroupRepository instance;
    private GroupListAPI mGroupListAPI;
    private MutableLiveData<List<GroupItem>> listMutableLiveData = new MutableLiveData<>();

    private GroupRepository() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://bitzeanfreelance.com/test/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGroupListAPI = retrofit.create(GroupListAPI.class);
    }

    public static GroupRepository getInstance() {
        if (instance == null) {
            instance = new GroupRepository();
        }
        return instance;
    }

    public MutableLiveData<List<GroupItem>> fetchGroups(int limit, int offset) {
        Call<GroupResponse> call = mGroupListAPI.group_list(limit, offset);
        call.enqueue(new Callback<GroupResponse>() {

            @Override
            public void onResponse(Call<GroupResponse> call, retrofit2.Response<GroupResponse> response) {
                listMutableLiveData.setValue(response.body().getData());
            }

            public void onFailure(Call<GroupResponse> call, Throwable t) {
                Log.e("fetchGroups", "onFailure: ", t);
            }
        });

        return listMutableLiveData;
    }
}
