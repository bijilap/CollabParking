package edu.usc.bphilip.collabparking;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Sidebar extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, CrowdSourceMenu.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        //printKeyHash(this);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }



    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {

            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }

        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();

        if(position == 0){
            Resources res = getResources();
            String[] menu_list = res.getStringArray(R.array.menu_list);
            menu_list[0] = ((MainApplication) this.getApplication()).me.name;
            LoginFragment loginFragObj = new LoginFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, loginFragObj)
                    .commit();
            return;
            /*
            boolean isLoggedIn = ((MainApplication) this.getApplication()).getLoginStatus();
            if(!isLoggedIn){
                //startActivity(new Intent(this, LoginActivity.class));
                Log.d("MyApp","logging in now");
                LoginFragment loginFragObj = new LoginFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, loginFragObj)
                        .commit();
                return;
            }
            else{
                Log.d("MyApp", "login succeeded");
            }
            */
        }

        if(position == 1){
            ParkingMap parkingMapObj =  new ParkingMap();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, parkingMapObj)
                    .commit();
            /*fragmentManager.beginTransaction()
                    .replace(R.id.container, ParkingMap.newInstance())
                    .commit();*/
            return;
        }
        if(position == 3){
            CrowdSourceMenu csmObj = new CrowdSourceMenu();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, csmObj)
                    .commit();
            return;
        }
        if(position == 4){
            SettingsFragment sfObj = new SettingsFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, sfObj)
                    .commit();
            return;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                //mTitle = getString(R.string.title_section1);
                break;
            case 2:
                //mTitle = getString(R.string.title_section2);
                break;
            case 3:
                //mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.sidebar, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onFragmentInteraction(Uri uri){

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sidebar, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Sidebar) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    }

}
