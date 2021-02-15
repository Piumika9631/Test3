package mobileview.com.android.bitzean.network;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mobileview.com.android.bitzean.data.GroupItem;

public class GroupResponse implements Parcelable
{

    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("data")
    @Expose
    private List<GroupItem> data = null;
    public final static Parcelable.Creator<GroupResponse> CREATOR = new Creator<GroupResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GroupResponse createFromParcel(Parcel in) {
            return new GroupResponse(in);
        }

        public GroupResponse[] newArray(int size) {
            return (new GroupResponse[size]);
        }

    }
            ;

    protected GroupResponse(Parcel in) {
        this.apiStatus = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (GroupItem.class.getClassLoader()));
    }

    public GroupResponse() {
    }

    public Integer getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(Integer apiStatus) {
        this.apiStatus = apiStatus;
    }

    public List<GroupItem> getData() {
        return data;
    }

    public void setData(List<GroupItem> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(apiStatus);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}