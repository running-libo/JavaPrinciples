package com.example.javaprinciples.serializable;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.util.List;

public class ParcelableGroupBean implements Parcelable {
    private String name;
    private List<String> list;
    private User user;

    /**
     * 4.自动创建的的构造器，使用反序列化得到的 Parcel 构造对象
     * @param in
     */
    protected ParcelableGroupBean(Parcel in) {
        name = in.readString();
        list = in.createStringArrayList();
        user = in.readParcelable(User.class.getClassLoader());  //读取一个类对象是，需要传入该类的classloader
    }

    /**
     * 内容描述
     * @return
     */
    @Override
    public int describeContents() {
        //几乎都返回 0，除非当前对象中存在文件描述符时为 1
        return 0;
    }

    /**
     * 序列化
     * @param dest The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     * May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(list);
        dest.writeParcelable(user, flags);  //写入对象需要用 writeParcelable
    }

    /**
     * 反序列化
     */
    public static final Creator<ParcelableGroupBean> CREATOR = new Creator<ParcelableGroupBean>() {
        /**
         * 反序列创建对象
         * @param in
         * @return
         */
        @Override
        public ParcelableGroupBean createFromParcel(Parcel in) {
            return new ParcelableGroupBean(in);
        }

        /**
         * 反序列创建对象数组
         * @param size
         * @return
         */
        @Override
        public ParcelableGroupBean[] newArray(int size) {
            return new ParcelableGroupBean[size];
        }
    };
}
