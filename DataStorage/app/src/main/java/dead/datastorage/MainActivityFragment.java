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

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;
    private ArrayAdapter<String> adapter;
    String months []={"January","February","March","April","May","June","July","August","September","October","November","December"};
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        listView=view.findViewById(R.id.list);


        adapter=new ArrayAdapter<String>(getActivity(),R.layout.listadapter,R.id.TextAdapter,months);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(),"tapped on "+months[i],Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(getActivity(),DisplayNotesActivity.class);
        startActivity(intent);
    }
}
