package de.tubs.ibr.dtn.p2p;

import android.content.*;
import android.content.SharedPreferences.Editor;
import android.preference.*;
import de.tubs.ibr.dtn.*;
import de.tubs.ibr.dtn.p2p.scheduler.*;

public final class SettingsUtil {

    private SettingsUtil() {
    }

    private static final String KEY_NEXT_SCHEDULED_CHECK = "nextScheduledCheck";
    private static final String KEY_SCHEDULER_INFO = "schedulerInfo";
    private static final String KEY_SCHEDULER_STOPPED = "schedulerStopped";

    public static Strategy getStrategy(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String type = prefs.getString("p2p_strategy", "on");
        Strategy s = null;
        if ("on".equals(type)) {
            s = new StategyAlwaysOn();
        } else if ("tentwenty".equals(type)) {
            s = new TenOnTwentyOfStrategy();
        }
        return s;
    }

    public static long getNextScheduledCheck(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getLong(KEY_NEXT_SCHEDULED_CHECK, -1L);
    }

    public static void setNextScheduledCheck(Context context, long value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor edit = prefs.edit();
        edit.putLong(KEY_NEXT_SCHEDULED_CHECK, value);
        edit.apply();
    }

    public static String getSchedulerInfo(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getString(KEY_SCHEDULER_INFO, "");
    }

    public static void setSchedulerInfo(Context context, String value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor edit = prefs.edit();
        edit.putString(KEY_SCHEDULER_INFO, value);
        edit.apply();
    }

    public static boolean isSchedulerStopped(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getBoolean(KEY_SCHEDULER_STOPPED, false);
    }

    public static void setSchedulerStopped(Context context, boolean value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor edit = prefs.edit();
        edit.putBoolean(KEY_SCHEDULER_STOPPED, value);
        edit.apply();
    }

    public static void setDefaultValues(Context context) {
        PreferenceManager.setDefaultValues(context, R.xml.prefs_p2p, false);
    }

    public static boolean isPeerDiscoveryEnabled(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getBoolean("p2p_enabled", false);
    }

    /**
     * @param tag
     * @return
     * @deprecated
     */
    public static String pTag(String tag) {
        return "de.hendrikfreytag.wifip2p4ibrdtn";
    }

    public static String getEid(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getString("endpoint_id", "error");
    }

}
