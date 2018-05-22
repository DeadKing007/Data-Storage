package dead.datastorage;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class ReadAllNotesTask extends AsyncTask<Void,Void,List<NotesDetail>> {

    Context context;
    List<NotesDetail> noteList;
    NotesAdapter adapter;
    ReadAllNotesTask(Context context,NotesAdapter adapter,List<NotesDetail>noteList){
        this.context=context;
        this.adapter=adapter;
        this.noteList =noteList;
    }
    @Override
    protected List<NotesDetail> doInBackground(Void... voids) {
       NotesDatabaseHelper helper=new NotesDatabaseHelper(context);
      if (noteList.size() > 0){
          noteList.clear();
          noteList.addAll(helper.ReadAll());
      }else {
          noteList.addAll(helper.ReadAll());
      }
        return noteList;
    }

    @Override
    protected void onPostExecute(List<NotesDetail> notesDetails) {
        super.onPostExecute(notesDetails);
if (notesDetails.size()>0)
    adapter.notifyDataSetChanged();
    }
}
