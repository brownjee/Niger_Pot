package app.com.brownjee.nigerpot;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import app.com.brownjee.nigerpot.data.CustomerDetails;


public class ViewOrder extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;
    LoaderManager loadermanager;
    CursorLoader cursorLoader;
    private static String TAG="CursorLoader";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadermanager=getLoaderManager();

        String[] uiBindFrom = {  CustomerDetails.FOOD_NAME, CustomerDetails.QUANTITY};
        int[] uiBindTo = {R.id.foodname, R.id.foodquantity};

        /*Empty adapter that is used to display the loaded data*/
        mAdapter = new SimpleCursorAdapter(this,R.layout.txtview_vieworder, null, uiBindFrom, uiBindTo,0);
        setListAdapter(mAdapter);

/**
 * This initializes the loader and makes it active. If the loader
 * specified by the ID already exists, the last created loader is reused.
 * If the loader specified by the ID does not exist, initLoader() triggers
 * the LoaderManager.LoaderCallbacks method onCreateLoader().
 * This is where you implement the code to instantiate and return a new loader.
 * Use restartLoader() instead of this, to discard the old data and restart the Loader.
 * Hence, here the given LoaderManager.LoaderCallbacks implementation are associated with the loader.
 */
        loadermanager.initLoader(1, null, this);
    }

    /**
     * This creates and return a new Loader (CursorLoader or custom Loader) for the given ID.
     * This method returns the Loader that is created, but you don't need to capture a reference to it.
     */
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {

        String[] projection = { CustomerDetails.ID, CustomerDetails.FOOD_NAME, CustomerDetails.QUANTITY };

/**
 * This requires the URI of the Content Provider
 * projection is the list of columns of the database to return. Null will return all the columns
 * selection is the filter which declares which rows to return. Null will return all the rows for the given URI.
 * selectionArgs:  You may include ?s in the selection, which will be replaced
 * by the values from selectionArgs, in the order that they appear in the selection.
 * The values will be bound as Strings.
 * sortOrder determines the order of rows. Passing null will use the default sort order, which may be unordered.
 * To back a ListView with a Cursor, the cursor must contain a column named _ID.
 */

        cursorLoader = new CursorLoader(this, CustomerDetails.CONTENT_URI, projection, null, null, null);
        return cursorLoader;

    }

    /**
     * Called when a previously created loader has finished its load. This assigns the new Cursor but does not close the previous one.
     * This allows the system to keep track of the Cursor and manage it for us, optimizing where appropriate. This method is guaranteed
     * to be called prior to the release of the last data that was supplied for this loader. At this point you should remove all use of
     * the old data (since it will be released soon), but should not
     * do your own release of the data since its loader owns it and will take care of that.
     * The framework would take of closing of old cursor once we return.
     */

    public void onLoadFinished(Loader<Cursor> loader,Cursor cursor) {
        if(mAdapter!=null && cursor!=null)
            mAdapter.swapCursor(cursor); //swap the new cursor in.
        else
            Log.v(TAG, "OnLoadFinished: mAdapter is null");
    }

    /**
     * This method is triggered when the loader is being reset and the loader  data is no longer available.
     * This is called when the last Cursor provided to onLoadFinished() above
     * is about to be closed. We need to make sure we are no longer using it.
     */

    public void onLoaderReset(Loader<Cursor> arg0) {
        if(mAdapter!=null)
            mAdapter.swapCursor(null);
        else
            Log.v(TAG,"OnLoadFinished: mAdapter is null");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
