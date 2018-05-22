package dead.datastorage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends BaseAdapter {

    private List<NotesDetail>notesDetails;
    private  LayoutInflater inflater;

    NotesAdapter(Context context,List<NotesDetail>notesDetails){
        this.notesDetails=notesDetails;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return notesDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return notesDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return notesDetails.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null){
            view= inflater.inflate(R.layout.listadapter,viewGroup,false);
        }
        TextView textView=view.findViewById(R.id.TextAdapter);
        NotesDetail notesdetil= notesDetails.get(i);
        textView.setText(notesdetil.getTitle());

        return view;
    }
}
