package com.gzq.lib_core.base.config;

import android.content.Context;

import androidx.room.RoomDatabase;

/**
 * created on 2018/10/19 17:03
 * created by: gzq
 * description: room数据库框架配置
 */
public interface RoomDatabaseConfig<DB extends RoomDatabase> {
    void room(Context context, RoomDatabase.Builder<DB> builder);
}
