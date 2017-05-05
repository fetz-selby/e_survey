package com.steve.housing.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.steve.housing.utils.ServerUtils;

import io.realm.Realm;

/**
 * Created by SOVAVY on 5/5/2017.
 */

public class AbstractHelper {
    public String TAG = "";
    public SharedPreferences prefs;
    protected ServerUtils server;
    public Context mContext;
    public Realm mRealm;

    public AbstractHelper(Context context, Realm realm) {
        this.mContext = context;
        this.mRealm = realm;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.server = new ServerUtils();

        String clsName = mContext.getClass().getSimpleName();
        if (clsName.length() > 20)
            clsName = clsName.substring(0, 20);
        this.TAG = clsName;
    }

    public String getLastSyncDate(String tableName){
        String strDate = "";

//		String query = "SELECT " + AbstractBaseColumns.COL_DATE_SYNCED +
//                       " FROM " + tableName +
//                       " WHERE " + AbstractBaseColumns.COL_DATE_SYNCED + " != ''" +
//                       " ORDER BY " + AbstractBaseColumns.COL_DATE_SYNCED  + " DESC";
//		Cursor cursor = contractAbstract.fetchRawSql(query);
//		if (cursor.getCount() > 1){
//			cursor.moveToFirst();
//			strDate = cursor.getString(cursor.getColumnIndex(AbstractBaseColumns.COL_DATE_SYNCED));
//		}
        strDate = strDate.replace(" ", "+");
        Log.d("getLastSyncDate", "DATE: " + strDate);
        return strDate;
    }

    public String getLatestUrl(String strTableName, String strUrl){
//		String strDate = this.getLastSyncDate(strTableName);
//		if (!strDate.contentEquals(""))
//			strUrl += "&date_modified__gte=" + strDate;
        return strUrl;
    }






}

