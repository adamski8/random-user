package pl.adamchodera.randomuser.network.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkAvailabilityUtil {

    private NetworkAvailabilityUtil() {
        throw new AssertionError("No instances.");
    }

    public static boolean isInternetConnectionAvailable(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
