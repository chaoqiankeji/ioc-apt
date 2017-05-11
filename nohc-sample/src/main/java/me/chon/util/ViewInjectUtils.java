package me.chon.util;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.nohc.Bind;

/**
 * Created by chon on 2017/5/11.
 * What? How? Why?
 */

public class ViewInjectUtils {

    public static void inject(Activity activity) {

        Field[] fields = activity.getClass().getDeclaredFields();
        Log.i("nohc","field length = " + fields.length);
        for (Field field : fields) {

            if (field.isAnnotationPresent(Bind.class)) {
                Bind annotation = field.getAnnotation(Bind.class);
                int id = annotation.value();

                try {
                    field.setAccessible(true);
                    field.set(activity,activity.findViewById(id));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
