package com.cd.jyf.studio.remote.parce;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 *  介绍下学序列化
 *
 *
 *
 *    android 自带序列化 为 parcelable 接口 表示序列化
 */
public class ParcelStudio  implements Parcelable {
    /**
     * 传入  载体
     * @param in
     */
    protected ParcelStudio(Parcel in) {
        //按顺序读取  注意 parcel  是按照声明顺序读取的
    }

    /**
     * 反序列化
     */
    public static final Creator<ParcelStudio> CREATOR = new Creator<ParcelStudio>() {
        @Override
        public ParcelStudio createFromParcel(Parcel in) {
            return new ParcelStudio(in);
        }

        @Override
        public ParcelStudio[] newArray(int size) {
            return new ParcelStudio[size];
        }
    };

    /**
     * 内容描述  通常不用管
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 写入到 parcel 中   可以理解为序列化的过程
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
    }
}
