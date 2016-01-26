package model;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by yonghak on 2016-01-26.
 */
public class AutoIncrementHelper {

    public static Realm realm;

    public static String INDEX_FIELD = "index";
    public static String CLASS_NAME_FIELD = "className";

    public static int getNextIndex(Context context,Class target){
        if(realm == null){
            realm = Realm.getInstance(context);
        }

        AutoIncrement ai = realm.where(AutoIncrement.class).equalTo(CLASS_NAME_FIELD, target.toString()).findFirst();;

        if(ai != null){

            realm.beginTransaction();
            ai.setIndex(ai.getIndex() + 1);
            realm.commitTransaction();

        }else{

            realm.beginTransaction();
            ai = realm.createObject(AutoIncrement.class);
            ai.setIndex(1);
            ai.setClassName(target.toString());

            realm.commitTransaction();


        }

        realm.close();

        return ai.getIndex();
    }
}
