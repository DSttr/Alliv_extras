package in.ds.alliv.control.fragment;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import in.ds.alliv.control.*;

import android.support.v4.app.Fragment;

public class AboutFragment extends Fragment
{
	private MainActivity mainActivity;
    private Toolbar toolbar;


    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);
		
        toolbar = (Toolbar)view.findViewById(R.id.about_toolbar);

        setupToolbar();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupDikiDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.about_fragment_title));
        mainActivity.setSupportActionBar(toolbar);
    }


}

