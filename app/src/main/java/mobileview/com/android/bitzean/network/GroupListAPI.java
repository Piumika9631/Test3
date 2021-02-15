package mobileview.com.android.bitzean.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface GroupListAPI {

    @FormUrlEncoded
    @POST("group_list")
    Call<GroupResponse> group_list(@Field("limit") int limit, @Field("offset") int offset);
}
