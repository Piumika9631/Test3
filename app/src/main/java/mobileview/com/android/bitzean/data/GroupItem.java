package mobileview.com.android.bitzean.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupItem implements Parcelable
{

    @SerializedName("group_title")
    @Expose
    private String groupTitle;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("category")
    @Expose
    private String category;
    public final static Parcelable.Creator<GroupItem> CREATOR = new Creator<GroupItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GroupItem createFromParcel(Parcel in) {
            return new GroupItem(in);
        }

        public GroupItem[] newArray(int size) {
            return (new GroupItem[size]);
        }

    }
            ;

    protected GroupItem(Parcel in) {
        this.groupTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.cover = ((String) in.readValue((String.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GroupItem() {
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(groupTitle);
        dest.writeValue(cover);
        dest.writeValue(category);
    }

    public int describeContents() {
        return 0;
    }

}