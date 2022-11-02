package com.viewstreetvr.net.net.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.viewstreetvr.net.net.BaseDto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by shanlin on 2017/10/12.
 */

public class PublicUtil {

    public static String metadata(Context context, String key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            String value = appInfo.metaData.getString(key);
            if (!TextUtils.isEmpty(value)) {
                if (value.startsWith("QQ"))
                    return value.replace("QQ", "");
            } else {
                int anInt = appInfo.metaData.getInt(key);
                if (anInt != 0)
                    value = anInt + "";
            }
            return value;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Return whether <em>you</em> have been granted the permissions.
     *
     * @param permissions The permissions.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isGranted(Context context, final String... permissions) {
        for (String permission : permissions) {
            if (!isAccredit(context, permission)) {
                return false;
            }
        }
        return true;
    }


    public static int getVersionCode(Context context) {
        return getAppInfo(context) == null ? 1 : getAppInfo(context).versionCode;
    }

    public static int getIconDrawable(Context context) {
        if (getAppInfo(context) != null && getAppInfo(context).applicationInfo != null)
            return getAppInfo(context).applicationInfo.icon;
        else
            return 0;
    }

    public static String getVersionName(Context context) {
        return getAppInfo(context) == null ? "1.0" : getAppInfo(context).versionName;
    }

    public static boolean isImageFile(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        if (options.outWidth == -1) {
            return false;
        }
        return true;
    }

    /**
     * 调整图片大小
     *
     * @param bitmap
     *            源
     * @param dst_w
     *            输出宽度
     * @param dst_h
     *            输出高度
     * @return
     */
    public static Bitmap changeBitmapSize(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,true);
        return dstbmp;
    }

    /**
     * 检查权限
     * @param context
     * @param perm
     * @return
     */
    public static boolean isAccredit(Context context, String perm){
        Context ctx = context.getApplicationContext();
        if (ContextCompat.checkSelfPermission(ctx, perm) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static String getAppName(Context context) {
        PackageManager packageManagers = context.getPackageManager();
        try {
            String appName = (String) packageManagers.getApplicationLabel(packageManagers.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA));
            return appName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getAppPackage(Context context) {
        return context.getPackageName();
    }

    public static Activity getActivityFromView(View view) {
        if (view.getContext() instanceof Activity) {
            return (Activity) view.getContext();
        }
        if (((ContextWrapper) view.getContext()).getBaseContext() instanceof Activity) {
            return (Activity) ((ContextWrapper) view.getContext()).getBaseContext();
        }
        return null;
    }


    public static String readAssets(Context context, String filePath) {
        StringBuilder buf = new StringBuilder();
        try {
            InputStream json = context.getAssets().open(filePath);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return buf.toString();
    }

    public static PackageInfo getAppInfo(Context context) {
        try {
            PackageManager packageManagers = context.getPackageManager();
            return packageManagers.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setDoze(Context ctx) {
        // 在Android 6.0及以上系统，若定制手机使用到doze模式，请求将应用添加到白名单。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String packageName = ctx.getPackageName();
            boolean isIgnoring = ((PowerManager) ctx.getSystemService(Context.POWER_SERVICE)).isIgnoringBatteryOptimizations(packageName);
            if (!isIgnoring) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                try {
                    ctx.startActivity(intent);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static boolean isOnlineDevice(String str) {
        if (!TextUtils.isEmpty(str)) {
//            long startTime = System.currentTimeMillis();
            String[] split = str.split("#", -1);
            if (split.length >= 2) {
                for (int i = 1; i < split.length - 1; i++) {
                    if (BaseDto.getUniqueId().equals(split[i])) {
                        return true;
                    }
                }
            } else if (split.length == 1) {
                return true;
            }
        }
        return false;
    }

    public static String getPhoneJsonStr(String response) {
        if (!TextUtils.isEmpty(response)) {
            return response.substring(response.lastIndexOf("#") + 1);
        }
        return "";
    }


    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 四舍五入并保留?位小数
     *
     * @param v
     * @param scale
     * @return
     */
    public static double round(Double v, int scale) {
        if (scale < 0) {
            return v;
//            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = null == v ? new BigDecimal("0.0") : new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 经纬度转换度、分、秒
     *
     * @param iDouble
     * @return
     */
    public static String latLongitudeTransition(double iDouble) {
        String str = "";
        try {
            int i = (int) iDouble;
            str += i + "°";
            //计算textfield3需要显示的数字
            double j1 = iDouble - i;
            Double j2 = j1 * 60;
            int j3 = j2.intValue();
            str += j3 + "′";
            //计算textField4
            double k1 = j2 - j3;
            Double k2 = k1 * 60;
            str += Math.round(k2) + "″";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取经度名字（东、西经）
     * @return
     */
    public static String getLongitudeName(double latitude){
        if (latitude > 0) {
            return "东经 ";
        } else if (latitude < 0) {
            return "西经 ";
        }
        return "";
    }

    /**
     * 获取纬度名字（南、北纬）
     * @return
     */
    public static String getLatitudeName(double longitude){
        if (longitude > 0) {
            return "北纬 ";
        } else if (longitude < 0) {
            return "南纬 ";
        }
        return "";
    }

    /**
     * dip转换成px
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dip2Px(Context context, float dip) {
        if (null != context && null != context.getResources()) {
            return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
        } else {
            return (int) dip;
        }
    }

    /**
     * 关闭软键盘
     *
     * @param editText 输入框
     * @param context  上下文
     */
    public static void closeKeyboard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 打开软键盘
     *
     * @param editText 输入框
     * @param context  上下文
     */
    public static void openKeyboard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void setStatusBarTranslucent(ViewGroup vg, Activity activity) {
        // 如果版本在4.4以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏高度
            int height = getStatusBarHeight(activity);
            if (height <= 0) {
                return;
            }
            // 设置距离顶部状态栏垂直距离
            vg.setPadding(0, height, 0, 0);
            // 状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 导航栏透明
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        int height = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            height = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }
}
