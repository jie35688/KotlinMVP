package com.kanfang123.vrhouse.kanfang.utils

import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanfang123.vrhouse.kanfang.base.VrHouseApp

import java.util.ArrayList

/**
 * SharedPreferences工具类
 */

object SPUtil {


    private var preferneces: SharedPreferences? = null
        @Synchronized get() {
            if (preferneces == null) {
                preferneces = PreferenceManager.getDefaultSharedPreferences(VrHouseApp.getInstance())
            }
            return preferneces
        }

    /**
     * 打印所有
     */
    fun print() {
        println(preferneces!!.all)
    }

    /**
     * 清空保存在默认SharePreference下的所有数据
     */
    fun clear() {
        preferneces!!.edit().clear().apply()
    }

    /**
     * 保存字符串
     */
    fun putString(key: String, value: String) {
        preferneces!!.edit().putString(key, value).apply()
    }

    /**
     * 保存数组
     */
    fun putIntArray(key: String, values: IntArray) {
        preferneces!!.edit().putString(key, Gson().toJson(values)).apply()
    }

    /**
     * 获取数组
     */
    fun getIntArray(key: String): IntArray? {
        val value = preferneces!!.getString(key, null) ?: return null
        val gson = Gson()
        val list = gson.fromJson<List<Int>>(value, object : TypeToken<ArrayList<Int>>() {

        }.type)
        val d = IntArray(list.size)
        for (i in list.indices) {
            d[i] = list[i]
        }
        return d
        // return   Arrays.stream(values).mapToInt(Integer::valueOf).toArray();
    }


    /**
     * 读取字符串
     */
    fun getString(key: String): String? {
        return preferneces!!.getString(key, null)
    }

    /**
     * 读取字符串
     */
    fun getString(key: String, defaultvalue: String): String? {
        return preferneces!!.getString(key, defaultvalue)
    }


    /**
     * 保存整型值
     */
    fun putInt(key: String, value: Int) {
        preferneces!!.edit().putInt(key, value).apply()
    }

    /**
     * 读取整型值
     */
    fun getInt(key: String): Int {
        return preferneces!!.getInt(key, 0)
    }

    /**
     * 保存布尔值
     */
    fun putBoolean(key: String, value: Boolean?) {
        preferneces!!.edit().putBoolean(key, value!!).apply()
    }

    fun putLong(key: String, value: Long) {
        preferneces!!.edit().putLong(key, value).apply()
    }

    fun getLong(key: String): Long {
        return preferneces!!.getLong(key, 0)
    }

    /**
     * 读取布尔值
     *
     */
    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preferneces!!.getBoolean(key, defValue)

    }

    /**
     * 移除字段
     */
    fun removeString(key: String) {
        preferneces!!.edit().remove(key).apply()
    }

    /*
     * 判断用户有没有登录
     * @return
     *//*
    public static  boolean isLogin(){
       return getBoolean(USER_LOGIN,false);
    }*/

}
