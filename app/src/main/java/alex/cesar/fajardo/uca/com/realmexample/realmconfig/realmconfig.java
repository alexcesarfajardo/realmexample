package alex.cesar.fajardo.uca.com.realmexample.realmconfig;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class realmconfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
