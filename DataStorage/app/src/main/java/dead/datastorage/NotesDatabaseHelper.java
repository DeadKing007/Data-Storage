package dead.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDatabaseHelper extends SQLiteOpenHelper {

    private final static int version=1;
    private final static String name="notes.sqlite";

    public NotesDatabaseHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CreateNotesTable=" CREATE TABLE "+Notes.NotesContract.Table_name+" ( " +
             Notes.NotesContract._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Notes.NotesContract.title+  " TEXT NOT NULL, " +
                Notes.NotesContract.Description +" TEXT NOT NULL "  +");";
        sqLiteDatabase.execSQL(CreateNotesTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //CRUD

    }
    public long InserintoTable(String title,String description){

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();//key-value pair

        values.put(Notes.NotesContract.title,title);
        values.put(Notes.NotesContract.Description,description);

        long id= database.insert(Notes.NotesContract.Table_name,null,values);
        database.close();


        return id;
    }
}
