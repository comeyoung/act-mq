package com.fiidee.artlongs.mq.serializer;

import com.fiidee.artlongs.mq.MsgEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * GOOGLE GSON SERIALIZER
 * Created by leeton on 9/4/17.
 */
public class GsonSerializer implements ISerializer {

    Gson gson = new GsonBuilder().create();

    @Override
    public byte[] getByte(Object obj) {
        String jsonStr = gson.toJson(obj);
        return jsonStr.getBytes();

    }

    @Override
    public <T extends Serializable> T getObj(byte[] bytes) {
        String jsonStr = new String(bytes);
        try {
            T obj = gson.fromJson(jsonStr, (Type) MsgEntity.class);
            return obj;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T extends Serializable> T getObj(byte[] bytes, Class<T> clzz) {
        String jsonStr = new String(bytes);
        try {
            T obj = gson.fromJson(jsonStr, clzz);
            return obj;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
