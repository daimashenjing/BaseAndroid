package com.mvp.io;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * 一个简单的SharedPreferences 保存封装
 *
 * @author 史章华
 * @version 1.0
 */
public class SP {
    private static SP _instance = null;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private final String MAK = "123"; // AES加密的密钥
    private final int MODE = Context.MODE_PRIVATE;//存储模式
    private static final String FILE_NAME = "config";//存储名字
    private static String mName = "";

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    private SP(Context c, String name) {
        mName = name;
        sp = c.getSharedPreferences(name, MODE);
        editor = sp.edit();
    }

    // 单例静态工厂方法,同步防止多线程环境同时执行
    public synchronized static SP getInstance(Context c) {
        return getInstance(c, FILE_NAME);
    }

    /**
     * @param c    Context
     * @param name 文件名
     * @return
     */
    public synchronized static SP getInstance(Context c, String name) {
        if (_instance == null && !mName.equals(name)) {
            mName = name;
            if (_instance != null) {
                _instance = null;
            }
            _instance = new SP(c, name);
        }
        return _instance;
    }

    public SharedPreferences getSp() {
        return sp;
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键的值
     * @param defaultObject 默认值
     * @return
     */
    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {//对文本内容解密
            String str = null;
            try {
                str = sp.getString(key, null);
                if (str != null && !"".equals(str)) {
                    str = AESEncryptor.decrypt(MAK, str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str == null ? defaultObject : str;
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else {
            return sp.getString(key, null);
        }
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public void put(String key, Object object) {//对文本内容加密
        if (object instanceof String) {
            try {
                editor.putString(key, AESEncryptor.encrypt(MAK, (String) object));
            } catch (Exception e) {
                editor.putString(key, (String) object);
                e.printStackTrace();
            }
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有的数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }
}
