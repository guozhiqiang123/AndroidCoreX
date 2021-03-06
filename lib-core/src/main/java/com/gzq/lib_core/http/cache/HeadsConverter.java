package com.gzq.lib_core.http.cache;

import com.gzq.lib_core.base.Box;

import androidx.room.TypeConverter;
import okhttp3.Headers;

public class HeadsConverter {
    @TypeConverter
    public static String Headers2String(Headers headers) {
        return Box.getGson().toJson(headers);
    }
    @TypeConverter
    public static Headers String2Headers(String headers) {
        return Box.getGson().fromJson(headers, Headers.class);
    }
}
