package com.youngkim.ls_challenge.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TheListFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class TheListFragment extends Fragment {

        private RequestQueue requestQueue;
        public ArrayList<ListItem> list_items = new ArrayList<ListItem>();
        private final String THE_FEED = "http://sheltered-bastion-2512.herokuapp.com/feed.json";
        TheListAdapter la;
        ListView lv;



        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            lv = (ListView) rootView.findViewById(R.id.the_list);
            la = new TheListAdapter(this.getActivity(), list_items);
            lv.setAdapter(la);

            requestQueue = Volley.newRequestQueue(this.getActivity());
            loadData();

            return rootView;
        }

        public void loadData() {
            requestQueue.add(
                    new JsonArrayRequest(THE_FEED,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    for (int x = 0; x < response.length(); x++) {
                                        try {
                                            JSONObject item = response.getJSONObject(x);
                                            Log.d("response", item.toString());

                                            JSONObject user = item.getJSONObject("user");
                                            Log.d("user", user.toString());

                                            JSONObject avatar = user.getJSONObject("avatar");
                                            Log.d("avatar", avatar.toString());
                                            list_items.add(
                                                    new ListItem(item.getString("attrib"), item.getString("desc"), item.getString("href"), item.getString("src"),
                                                            new User(user.getString("username"), user.getString("name"),
                                                                    new Avatar(avatar.getInt("height"), avatar.getInt("width"), avatar.getString("src")))
                                                    )
                                            );
                                        } catch (JSONException e) {
                                            Log.e("JSONException", e.toString());
                                        }
                                    }

                                    la.notifyDataSetChanged();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("VolleyError", volleyError.toString());
                        }
                    }
                    )
            );

        }
    }
}
