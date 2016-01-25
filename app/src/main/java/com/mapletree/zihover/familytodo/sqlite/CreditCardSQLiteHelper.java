package com.mapletree.zihover.familytodo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mapletree.zihover.familytodo.model.Book;
import com.mapletree.zihover.familytodo.model.CreditCard;

import java.util.ArrayList;

/**
 * Created by yonghak on 2016-01-25.
 */
public class CreditCardSQLiteHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ExpenseDB";

    public CreditCardSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_CREADIT_CARD_TABLE = "CREATE TABLE credit_cards ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "phone_number TEXT ";

        // create creadit card table
        db.execSQL(CREATE_CREADIT_CARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older credit_cards table if existed
        db.execSQL("DROP TABLE IF EXISTS credit_cards");

        // create fresh credit_cards table
        this.onCreate(db);
    }



    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_CREDIT_CARDS = "credit_cards";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phone_number";

    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_PHONE_NUMBER};

    public void addCreditCard(CreditCard creditCard){
        Log.d("addCreditCard", creditCard.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, creditCard.getName()); // get title
        values.put(KEY_PHONE_NUMBER, creditCard.getPhoneNumber()); // get author

        // 3. insert
        db.insert(TABLE_CREDIT_CARDS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public CreditCard getCreditCard(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_CREDIT_CARDS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        CreditCard creditCard = new CreditCard();
        creditCard.setId(Integer.parseInt(cursor.getString(0)));
        creditCard.setName(cursor.getString(1));
        creditCard.setPhoneNumber(cursor.getString(2));

        Log.d("getCreaditCard("+id+")", creditCard.toString());

        // 5. return creditCard
        return creditCard;
    }

    // Get All CreditCards
    public ArrayList<CreditCard> getAllCreditCards() {
        ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CREDIT_CARDS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build CreditCard and add it to list
        CreditCard creditCard = null;
        if (cursor.moveToFirst()) {
            do {
                creditCard = new CreditCard();
                creditCard.setId(Integer.parseInt(cursor.getString(0)));
                creditCard.setName(cursor.getString(1));
                creditCard.setPhoneNumber(cursor.getString(2));

                // Add CreditCard to CreditCards
                creditCards.add(creditCard);
            } while (cursor.moveToNext());
        }

        Log.d("getAllCreditCards()", creditCards.toString());

        // return CreditCards
        return creditCards;
    }

    // Updating single book
    public int updateCreditCard(CreditCard creditCard) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", creditCard.getName()); // get title
        values.put("author", creditCard.getPhoneNumber()); // get author

        // 3. updating row
        int i = db.update(TABLE_CREDIT_CARDS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(creditCard.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteBook(CreditCard creditCard) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CREDIT_CARDS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(creditCard.getId()) });

        // 3. close
        db.close();

        Log.d("deleteCreditCard", creditCard.toString());

    }
}
