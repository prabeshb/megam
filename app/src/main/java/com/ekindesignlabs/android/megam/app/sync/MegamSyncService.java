package com.ekindesignlabs.android.megam.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MegamSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static MegamSyncAdapter sMegamSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("MegamSyncService", "onCreate - MegamSyncService");
        synchronized (sSyncAdapterLock) {
            if (sMegamSyncAdapter == null) {
                sMegamSyncAdapter = new MegamSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sMegamSyncAdapter.getSyncAdapterBinder();
    }
}