package c.sakshi.lab5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
// So it knows it has to update (onUpgrade)
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db"; //DB name to look up through android api.

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS notes" +
                "(id INTEGER PRIMARY KEY, username TEXT, date TEXT, content TEXT, src TEXT)");
    }

    //This will get called if the version number is bigger than the one on the saved DB.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

}
