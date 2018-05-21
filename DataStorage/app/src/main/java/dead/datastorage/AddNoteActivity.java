package dead.datastorage;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    EditText title,description;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title=findViewById(R.id.Title);
        description=findViewById(R.id.Description);
        submit=findViewById(R.id.SubmitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText=title.getText().toString();
                String descriptionText=description.getText().toString();

                if (titleText.isEmpty() || descriptionText.isEmpty()){
                    Toast.makeText(AddNoteActivity.this,"Title or decription field is empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    NotesDetail notesDetail=new NotesDetail();
                    notesDetail.setDescription(descriptionText);
                    notesDetail.setTitle(titleText);
                new DatabaseTask(AddNoteActivity.this).execute(notesDetail);
                }
            }
        });

    }
    class DatabaseTask extends AsyncTask<NotesDetail,Void,Long>{

        private  Context context;
        DatabaseTask(Context context){
            this.context=context;

        }
        @Override
        protected Long doInBackground(NotesDetail... notesDetails) {
            NotesDatabaseHelper databaseHelper=   new NotesDatabaseHelper(context);
            NotesDetail notes=notesDetails[0];
            return databaseHelper.InserintoTable(notes.getTitle(),notes.getDescription());
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if (aLong>0){
                Toast.makeText(context,"Task Added",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(context,"Task not Added",Toast.LENGTH_SHORT).show();

            title.setText("");
            description.setText("");
        }
    }

}
