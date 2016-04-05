package in.ds.alliv.control;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import in.ds.alliv.control.DSAdapter.MainHeader.*;
import in.ds.alliv.control.fragment.*;
import in.ds.alliv.control.utils.*;
import android.app.Activity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements
NavigationView.OnNavigationItemSelectedListener,View.OnClickListener, Constants
{
	
	private final static String STATUS_BAR_SETTINGS = "status_bar";
    private final static String DRAWER_SETTINGS_FRAGMENT = "drawer_settings";
    private final static String BUTTON_SETTINGS_FRAGMENT = "button_settings";
    private final static String SYSTEM_SETTINGS_FRAGMENT = "system_settings";
	private final static String MISC_SETTINGS_FRAGMENT = "misc_settings";
	private final static String COLORING_SETTINGS_FRAGMENT = "COLORING_settings";
	private final static String ABOUT_FRAGMENT = "about";
    private final static String SELECTED_TAG = "selected_index";
	private final static int ABOUT = 0;
    private final static int STATUS_BAR = 1;
    private final static int DRAWER_SETTINGS = 2;
    private final static int BUTTON = 3;
    private final static int SYSTEM = 4;
	private final static int MISC = 5;
    private final static int COLORING = 6;
	
    private static int selectedIndex;

	private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	
	private ImageView image;

	private boolean pressAgain = true;

    /**
     * Current Fragment position
     */
    private int cur_position;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		
		mToolbar = (Toolbar)findViewById(R.id.toolbar);
		setupDikiToolbar(mToolbar);

		

		mNavigationView = (NavigationView)findViewById(R.id.navigation_view);
		mNavigationView.setNavigationItemSelectedListener(this);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
		setupDikiDrawer(mToolbar);
		
		if(savedInstanceState!=null){
            mNavigationView.getMenu().getItem(savedInstanceState.getInt(SELECTED_TAG)).setChecked(true);
            return;
        }
		selectedIndex = ABOUT;

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
		new AboutFragment(),ABOUT_FRAGMENT).commit();
    }

	
	@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_TAG, selectedIndex);
    }
	
	@Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

		
        switch(menuItem.getItemId()){
            case R.id.item_status_bar:
                if(!menuItem.isChecked()){
                    selectedIndex = STATUS_BAR;
                    menuItem.setChecked(true);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
					new StatusBarFragment(), STATUS_BAR_SETTINGS).commit();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.item_notification_drawer:
                if(!menuItem.isChecked()){
                    selectedIndex = DRAWER_SETTINGS;
                    menuItem.setChecked(true);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
					new DrawerSettingsFragment(),DRAWER_SETTINGS_FRAGMENT).commit();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.item_system:
                if(!menuItem.isChecked()){
                    selectedIndex = SYSTEM;
                    menuItem.setChecked(true);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
					new SystemSettingsFragment(),SYSTEM_SETTINGS_FRAGMENT).commit();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
			case R.id.item_button:
                if(!menuItem.isChecked()){
                    selectedIndex = BUTTON;
                    menuItem.setChecked(true);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
				    new ButtonSettingsFragment(),BUTTON_SETTINGS_FRAGMENT).commit();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
			case R.id.item_misc:
                if(!menuItem.isChecked()){
                    selectedIndex = MISC;
                    menuItem.setChecked(true);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
					new MiscSettingsFragment(),MISC_SETTINGS_FRAGMENT).commit();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
			case R.id.item_coloring:
                if(!menuItem.isChecked()){
                    selectedIndex = COLORING;
                    menuItem.setChecked(true);
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,
					new ColoringFragment(),COLORING_SETTINGS_FRAGMENT).commit();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.item_about:
                if(!menuItem.isChecked()){
                    selectedIndex = ABOUT;
                    menuItem.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
					new AboutFragment(),ABOUT_FRAGMENT).commit();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }
	

	public void setupDikiDrawer(Toolbar mToolbar){
        mDrawerToggle = new ActionBarDrawerToggle(
			this,
			mDrawerLayout,
			mToolbar,
			R.string.drawer_open,
			R.string.drawer_close
        ) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
		
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		//MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu.ds_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		/*switch (item.getItemId()) {
			case R.id.action_settings:
				OnClickListener();
				return true;
		}*/
		return super.onOptionsItemSelected(item);
	}

	private void OnClickListener()
	{
		// TODO: Implement this method
		Intent is = new Intent(MainActivity.this, MainHeaderActivity.class);
		startActivity(is);
	}
	
	@Override
	public void onClick(final View v) {
	}
	
	private void setupDikiToolbar(Toolbar mToolbar)
	{
		// TODO: Implement this method
		setSupportActionBar(mToolbar);
		mToolbar.setTitle("");

	}
	
	@Override
    public void onBackPressed() {
        try {
                    if (pressAgain) {
                        Utils.toast("press again", this);
                        pressAgain = false;
                        new Thread(new Runnable() {
								@Override
								public void run() {
									try {
										Thread.sleep(2000);
										pressAgain = true;
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}).start();
                    } else super.onBackPressed();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
	public interface OnBackButtonListener {
        boolean onBackPressed();
    }
	
}

