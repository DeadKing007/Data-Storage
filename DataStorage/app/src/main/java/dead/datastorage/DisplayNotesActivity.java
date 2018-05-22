package dead.datastorage;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayNotesActivity extends AppCompatActivity {

    TextView  DisplayNotes;
    NotesDetail note;
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notes);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DisplayNotes=findViewById(R.id.DisplayNotes);

         note= (NotesDetail) getIntent().getSerializableExtra("key");
      if (note!=null) {
          DisplayNotes.setText(note.getDescription());
        getSupportActionBar().setTitle(note.getTitle());
      }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diplay_notes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuid=item.getItemId();
        if (R.id.Delete==menuid){
            new DeleteTask(this).execute(note.getId());
        }

        return super.onOptionsItemSelected(item);
    }

    class  DeleteTask extends AsyncTask<Long,Void,Long>{

        Context context;
        DeleteTask(Context context){
            this.context=context;
        }

        @Override
        protected Long doInBackground(Long... longs) {
            NotesDatabaseHelper helper=new NotesDatabaseHelper(context);
          boolean check=  helper.DeleteTask(longs[0]);
          if (check)
              return 1L;
          else
            return 0L;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

            if (aLong==1){
                Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}
