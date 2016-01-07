package com.mapletree.zihover.familytodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by DaveMacPro on 2015-10-19.
 */
public class RedirectActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.setClass(RedirectActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("test_test","test");

        startActivity(intent);
        finish();
    }
}

