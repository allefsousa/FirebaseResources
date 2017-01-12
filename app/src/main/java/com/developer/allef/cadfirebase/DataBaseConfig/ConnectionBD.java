package com.developer.allef.cadfirebase.DataBaseConfig;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Allef on 11/01/2017.
 */

public class ConnectionBD {
    public void getConnection(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    }



}
