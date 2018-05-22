package dead.datastorage;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;
   // private ArrayAdapter<String> adapter;
    private List<NotesDetail> notesList;
    NotesAdapter adapter;
  //  String months []={"January","February","March","April","May","June","July","August","September","October","November","December"};
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        listView=view.findViewById(R.id.list);
        notesList=new ArrayList<NotesDetail>();
    adapter=new NotesAdapter(getActivity(),notesList);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
/*
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.listadapter,R.id.TextAdapter,months);
*/
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        new ReadAllNotesTask(getActivity(),adapter,notesList).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       Toast.makeText(getActivity(),"tapped on ",Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(getActivity(),DisplayNotesActivity.class);
        startActivity(intent);
    }
}
