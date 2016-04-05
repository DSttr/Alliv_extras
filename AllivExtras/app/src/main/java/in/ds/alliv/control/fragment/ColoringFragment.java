package in.ds.alliv.control.fragment;

import android.app.Fragment;
import android.app.*;
import android.os.*;
import android.preference.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import in.ds.alliv.control.*;
import in.ds.alliv.control.settings.*;

import android.support.v4.app.FragmentManager;

public class ColoringFragment extends Fragment
{

	private MainActivity mainActivity;
    private Toolbar toolbar;


    public ColoringFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_status_bar, container, false);


        toolbar = (Toolbar)view.findViewById(R.id.fragment_status_bar_toolbar);

        setupToolbar();

		PreferenceFragment fragment = new ColoringSettings();
		android.app.FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
			.replace(R.id.fragment_status_bar_container, fragment).commit();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupDikiDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.status_bar_fragment_title));
        mainActivity.setSupportActionBar(toolbar);
    }


}

