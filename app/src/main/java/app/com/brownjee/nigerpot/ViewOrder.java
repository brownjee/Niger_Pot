package app.com.brownjee.nigerpot;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;

import app.com.brownjee.nigerpot.data.CustomerDetails;


public class ViewOrder extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        // MAKE QUERY TO CONTACT CONTENTPROVIDER
        String[] projections = new String[] {CustomerDetails.ID,
                CustomerDetails.FOOD_NAME, CustomerDetails.QUANTITY };
        Cursor c = getContentResolver().query(CustomerDetails.CONTENT_URI,
                projections, null, null, null);
        startManagingCursor(c);

        // THE DESIRED COLUMNS TO BE BOUND
        String[] columns = new String[] { CustomerDetails.FOOD_NAME,
                CustomerDetails.QUANTITY,};

    // THE XML DEFINED VIEWS FOR EACH FIELD TO BE BOUND TO
        int[] to = new int[] { R.id.foodname, R.id.foodquantity,
                };

        // CREATE ADAPTER WITH CURSOR POINTING TO DESIRED DATA
        SimpleCursorAdapter cAdapter = new SimpleCursorAdapter(this,
                R.layout.txtview_vieworder, c, columns, to);
        // SET THIS ADAPTER AS YOUR LIST ACTIVITY'S ADAPTER
        this.setListAdapter(cAdapter);
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
