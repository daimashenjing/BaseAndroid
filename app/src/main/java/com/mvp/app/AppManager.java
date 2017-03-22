package com.mvp.app;

import android.app.Activity;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 史章华 on 2017/3/13.
 */
public class AppManager {
    private static List<SoftReference<Activity>> activityList;
    private static AppManager mInstance;
    // Activity栈的MaxSize，为0表示不限制
    private int mActivityListMaxSize = 0;

    private AppManager() {
        if (activityList == null) {
            activityList = new ArrayList<>(20);
        }
    }

    public synchronized static AppManager getInstance() {
        if (mInstance == null) {
            mInstance = new AppManager();
        }
        return mInstance;
    }

    /**
     * 得到当前Activity栈里面的管理的Activity的数量
     *
     * @return Size
     */
    public int getSize() {
        return activityList == null ? 0 : activityList.size();
    }

    /**
     * 向list中添加一个Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(new SoftReference<Activity>(activity));
            checkAndMaintainActivityList(mActivityListMaxSize);
        }
    }

    /**
     * 从list中删除某个指定的Activity
     *
     * @param activity
     */
    public void delActivity(Activity activity) {
        if (activity != null) {
            int size = getSize();
            if (size == 0) {
                return;
            }
            for (int i = size - 1; i >= 0; i--) {
                SoftReference<Activity> reference = activityList.get(i);
                if (reference == null) {
                    activityList.remove(i);
                    continue;
                }
                Activity item = reference.get();
                if (activity.equals(item)) {
                    if (!item.isFinishing()) {
                        item.finish();
                    }
                    reference.clear();
                    activityList.remove(i);
                    return;
                }
            }
        }
    }

    /**
     * @return 当前处于栈顶的Activity
     */
    public Activity topActivity() {
        int size = getSize();
        if (size == 0) {
            return null;
        }
        SoftReference<Activity> reference = activityList.get(size - 1);
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    /**
     * 设置List最大数量
     *
     * @param size
     */
    public void setActivityListMaxSize(int size) {
        // 不允许小于10 不允许等于0
        if (size < 10 && size != 0) {
            return;
        }
        mActivityListMaxSize = size;
    }

    /**
     * @return List的最大数量
     */
    public int getActivityStackMaxSize() {
        return mActivityListMaxSize;
    }

    /**
     * 检查List是否超过activityListMaxSize 并清除多余部分
     *
     * @param activityListMaxSize
     */
    private void checkAndMaintainActivityList(int activityListMaxSize) {
        if (activityListMaxSize == 0) {
            return;
        }
        int currentSize = getSize();
        while (currentSize > activityListMaxSize) {
            currentSize--;
            SoftReference<Activity> reference = activityList.get(0);
            if (reference != null) {
                Activity item = reference.get();
                if (item != null) {
                    AppManager.getInstance().delActivity(item);
                }
            }
        }
    }
}
