
package com.jakocrew.tabula.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Locale;


public class Util {


    public static String PACKAGE_VERSION = "";
    public static int VERSION_CODE = 0;


    public final static String PACKAGE_NAME = "com.kt.aljjapackplus";// ollehmusic
    // 아디


    public static boolean DEBUG_MODE = false;
    public static boolean FLAG_SHOWLOG = false;

    public static String GENIE_DOMAIN_NAME = "";
    public static String HUGAPI_DOMAIN_NAME = "";
    public static String HUGENGINE_DOMAIN_NAME = "";


    public final static int TOAST = 10;
    private static boolean VIBRATION = false;
    private static Context mAppContext = null;
    public static String audioURL = "";
    public static String audioResponse = "";
    public static String audioStmURL = "";
    public static String songTYPE = "";
    public static String errorType = "";





    public static boolean isNullofEmpty(String strData) {
        boolean bRet = false;
        if ( null == strData ) {
            bRet = true;
        } else if ( strData == null ) {
            bRet = true;
        } else if ( strData.equals("") ) {
            bRet = true;
        } else if ( strData.equals("null") ) {
            bRet = true;
        } else if ( strData.equals("Null") ) {
            bRet = true;
        }

        return bRet;
    }

    /**
     * @comment 현재지역의 년월일을 가져오는 함수
     */
    public static String getDay() {
        return getDay("");
    }

    public static String getDay(String seperator) {
        String deviceDate = "";
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(System.currentTimeMillis());

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if ( isNullofEmpty(seperator) ) {
            deviceDate = String.format("%04d%02d%02d", year, month, day);
        } else {
            deviceDate = String.format("%04d%s%02d%s%02d", year, seperator, month, seperator, day);
        }
        return deviceDate;
    }

    public static String getDate() {
        String deviceTime = "";
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(System.currentTimeMillis());

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        deviceTime = String.format("%04d.%02d.%02d", year, month, day);
        return deviceTime;
    }



    public static void vLog(String tag, String msg) {
        if ( Util.DEBUG_MODE ) {
            Log.v("mediapack", "[" + tag + "] " + "[" + getUseHeapMem() + "] " + msg);

        }
    }

    public static void dLog(String tag, String msg) {
        if ( Util.DEBUG_MODE ) {
            Log.d("mediapack", "[" + tag + "] " + "[" + getUseHeapMem() + "] " + msg);

        }
    }

    public static void iLog(String tag, String msg) {
        if ( Util.DEBUG_MODE ) {
            Log.i("mediapack", "[" + tag + "] " + "[" + getUseHeapMem() + "] " + msg);

        }
    }

    public static void wLog(String tag, String msg) {
        if ( Util.DEBUG_MODE ) {
            Log.w("mediapack", "[" + tag + "] " + "[" + getUseHeapMem() + "] " + msg);

        }
    }

    public static void eLog(String tag, String msg) {
        if ( Util.DEBUG_MODE ) {
            Log.e("mediapack", "[" + tag + "] " + "[" + getUseHeapMem() + "] " + msg);

        }
    }

    public static void aLog(String tag, String msg) {
        if ( Util.DEBUG_MODE ) {
            Log.e("mediapack", "[" + tag + "] " + "[" + getUseHeapMem() + "] " + msg);

        }
    }

    public static boolean bGetLogInfo(String tag) {
        boolean bgetloginfo = false;

        if( mAppContext != null && Util.FLAG_SHOWLOG
                && (tag.equals("AudioPlayerService") || tag.equals("CashHttpRequest") || tag.equals("")) ) {
            bgetloginfo = true;
        }

        return bgetloginfo;
    }


    /**
     * @comment 사용한 heap메모리 / 총 heap메모리
     */
    public static String getUseHeapMem() {
        String strRet = null;
        Double dMaxM = new Double(Runtime.getRuntime().maxMemory() / new Double(1204 * 1024));
        Double dUseM = new Double((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / new Double(1024 * 1024));

        strRet = String.format("%,.4fM/ %,.4fM", dUseM, dMaxM);

        return strRet;
    }






    public static void setAppContext(Context context) {
        mAppContext = context;
    }




    public static String getDeviceTime(Context context) {
        String deviceTime = "";
        // long lServerTimeDiff = 0;
        // SystemConfig poSystemConfig = SystemConfig.getInstance();
        // if ( poSystemConfig.getServerTimeDiff() != null ) {
        // lServerTimeDiff = Long.parseLong(poSystemConfig.getServerTimeDiff());
        // }
        // calendar.setTimeInMillis(System.currentTimeMillis() +
        // lServerTimeDiff);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        deviceTime = String.format("%04d%02d%02d%02d%02d%02d", year, month, day, hour, minute, second);

        return deviceTime;
    }

    public static int convertToPixcel(Context mContext, int dip) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        float dip_scale = outMetrics.density;
        float iPx = dip * dip_scale;
        return (int) iPx;
    }





    public static String getHostCheckUrl(Context context, String astrChangeUrl) {

        String strChangeUrl = astrChangeUrl;
        String ROOT_DOMAIN_MH = "https://mh-api.genie.co.kr/";
        String ROOT_DOMAIN_MH_CHAT_ENGINE = "https://mh-engine.genie.co.kr/";
        String ROOT_DOMAIN_GENIE = "https://app.genie.co.kr/";//https용
        String ROOT_DOMAIN_GENIE_NONESSL = "http://app.genie.co.kr/";//SSL 미적용  페이지

        if ( Util.GENIE_DOMAIN_NAME != null && Util.GENIE_DOMAIN_NAME.length() > 0 ) {
            strChangeUrl = strChangeUrl.replaceAll(ROOT_DOMAIN_GENIE, "http://" + Util.GENIE_DOMAIN_NAME + "/");
            strChangeUrl = strChangeUrl.replaceAll(ROOT_DOMAIN_GENIE_NONESSL, "http://" + Util.GENIE_DOMAIN_NAME + "/");
        }
        if ( Util.HUGAPI_DOMAIN_NAME != null && Util.HUGAPI_DOMAIN_NAME.length() > 0 ) {
            strChangeUrl = strChangeUrl.replaceAll(ROOT_DOMAIN_MH, "http://" + Util.HUGAPI_DOMAIN_NAME + "/");
        }
        if ( Util.HUGENGINE_DOMAIN_NAME != null && Util.HUGENGINE_DOMAIN_NAME.length() > 0 ) {
            strChangeUrl = strChangeUrl.replaceAll(ROOT_DOMAIN_MH_CHAT_ENGINE, "http://" + Util.HUGENGINE_DOMAIN_NAME + "/");
        }
        Util.dLog("UTIL", "[DBG] strDomainUrl: " + strChangeUrl);
        return strChangeUrl;
    }



    //재생쪽 에러 발생시 값 전송 위해 추가 : 재생 URL
    public static void setForErrorAudioURL(String URL) {
        audioURL = URL;
    }

    public static String getForErrorAudioURL() {
        if ( null != songTYPE && songTYPE.equalsIgnoreCase("mp3") ) {
            audioURL = "";
        }
        return audioURL;
    }

    //재생쪽 에러 발생시 값 전송 위해 추가 : 재생 URL 호출 결과값
    public static void setForErrorURLRespnse(String content) {
        audioResponse = content;
    }

    public static String getForErrorURLRespnse() {
        if ( null != songTYPE && songTYPE.equalsIgnoreCase("mp3") ) {
            audioResponse = "";
        }
        return audioResponse;
    }



    public static String getForErrorSTMURL() {
        if ( null != songTYPE && songTYPE.equalsIgnoreCase("mp3") ) {
            audioStmURL = "";
        }
        return audioStmURL;
    }

    //재생쪽 에러 발생시 값 전송 위해 추가 : songTYpe
    public static void setForErrorSongTYPE(String Type) {
        songTYPE = Type;
    }

    public static String getForErrorSongTYPE() {
        return songTYPE;
    }

    //재생 미디어 오류 값
    public static void setErrorType(String errorTYPE) {
        errorType = errorTYPE;
    }

    public static String getErrorType() {
        return errorType;
    }



    public static int parseInt(String string){
        try {
            return Integer.parseInt(string);
        }catch (Exception e){
            dLog("Util", " parseInt error ");
            return 0;
        }
    }

}
