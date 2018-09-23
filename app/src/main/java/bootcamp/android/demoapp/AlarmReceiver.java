package bootcamp.android.demoapp;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;
import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class AlarmReceiver extends BroadcastReceiver {
    LocationManager lm;
    Location location2=null;
    @Override
    public void onReceive(final Context context, Intent intent) {
        lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        Log.e("context2",""+context.toString());
       // Intent myIntent = new Intent(context, LocationUpdaterService.class);
       // context.startService(myIntent);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Log.e("sourav","the latitue "+location.getLatitude()+"the longitute is "+ location.getLongitude());
                Toast.makeText(context, "the latitue "+location.getLatitude()+"the longitute is "+ location.getLongitude(), Toast.LENGTH_SHORT).show();
                location2=location;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.e(  "ss","status changed");
            }

            public void onProviderEnabled(String provider) {
                Log.e("ss","provider enabled");
            }

            public void onProviderDisabled(String provider) {
                Log.e("ss","disabled");
            }

        };
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Log.e("he","notknown");
            return;
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        Toast.makeText(context, ""+lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude()+" "+lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude(), Toast.LENGTH_SHORT).show();
       // Log.e("The location is ","hello");

       Toast.makeText(context, "The latitute is", Toast.LENGTH_SHORT).show();
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
//        PendingIntent broadcast = PendingIntent.getBroadcast(context, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+600, broadcast);
    }
}
