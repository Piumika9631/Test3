package mobileview.com.android.bitzean.ui.home;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mobileview.com.android.bitzean.data.GroupItem;
import mobileview.com.android.bitzean.data.GroupRepository;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<GroupItem>> mGroupListItem = new MutableLiveData<>();
    private GroupRepository mGroupRepository;

    HomeViewModel(GroupRepository groupRepository) {
        this.mGroupRepository = groupRepository;
    }

    public MutableLiveData<List<GroupItem>> getGroupListItem() {
        return mGroupListItem;
    }

    public void fetchGroups(int limit, int offset) {
        mGroupListItem = mGroupRepository.fetchGroups(limit, offset);
    }

}
