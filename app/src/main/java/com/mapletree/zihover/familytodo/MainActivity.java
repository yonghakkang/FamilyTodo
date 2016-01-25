package com.mapletree.zihover.familytodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mapletree.zihover.familytodo.model.Book;
import com.mapletree.zihover.familytodo.model.BookAdapter;
import com.mapletree.zihover.familytodo.sqlite.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Expense;
import model.ExpenseAdapter;

public class MainActivity extends AppCompatActivity {
    private Realm realm;
    static  ArrayList<String> FRUITS = new ArrayList<String>();
    private String test2 = "";
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        test2 = intent.getStringExtra("test_test");

        Toast.makeText(getApplicationContext(),
                test2, Toast.LENGTH_SHORT).show();


        Book book = new Book();
        book.setId(1);
        book.setTitle(test2);
        book.setAuthor("test222");

        if(test2!=null){
           // adapter.add(book);
        }

    }
    //private BookAdapter adapter;
    private ExpenseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(realm == null){
            realm = Realm.getInstance(this);
        }

        //realm.beginTransaction();

        RealmResults<Expense> result = realm.where(Expense.class).findAll();

        // When the transaction is committed, all changes a synced to disk.
        //realm.commitTransaction();

       // MySQLiteHelper db = new MySQLiteHelper(this);

        // get all books
        //ArrayList<Book> list = db.getAllBooks();

        ListView listView = (ListView)findViewById(R.id.listView);
        //adapter = new BookAdapter(this,list);
        adapter = new ExpenseAdapter(this,0,result,true);

        listView.setAdapter(adapter);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(),
                        ((TextView)((LinearLayout) view).findViewById(R.id.title)).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
