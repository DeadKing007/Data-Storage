package dead.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public List<NotesDetail> ReadAll(){

        List<NotesDetail> list=new ArrayList<NotesDetail>();

        SQLiteDatabase database=this.getReadableDatabase();
       Cursor cursor= database.query(
                Notes.NotesContract.Table_name,
                null,
                null,
                null,
                null
                ,null,
                null
        );

       if (cursor.moveToFirst()){
           do {

               long id=  cursor.getLong(cursor.getColumnIndexOrThrow(Notes.NotesContract._ID));
               String title=cursor.getString(cursor.getColumnIndexOrThrow(Notes.NotesContract.title));
               String description=cursor.getString(cursor.getColumnIndexOrThrow(Notes.NotesContract.Description));

               NotesDetail note=new NotesDetail();
               note.setTitle(title);
               note.setDescription(description);
               note.setId(id);
               list.add(note);



           }while (cursor.moveToNext());
       }

       database.close();
        return list;
    }
    public boolean DeleteTask(Long id){

        SQLiteDatabase database=this.getReadableDatabase();

        String where=Notes.NotesContract._ID +"="+id;
        int check=database.delete(Notes.NotesContract.Table_name,where,null);
        if (check>0)
        return true;
        else
            return false;
    }
}
