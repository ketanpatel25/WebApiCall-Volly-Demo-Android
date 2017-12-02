package com.ketan.webapicallvollydemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.ketan.webapicallvollydemo.webservice.WSApiCall;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new GetCountriesTask(this).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public class GetCountriesTask extends AsyncTask<Void, Void, Integer> {

         private ProgressDialog mDialog = null;
        private Activity mActivity;
        private WSApiCall wsApiCall;

        public GetCountriesTask(Activity activity) {
            mActivity = activity;
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            mDialog = ProgressDialog.show(mActivity, null, "Please wait", true, true);
            mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            mDialog.setCanceledOnTouchOutside(false);

            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            cancel(true);
            }
            });
        }

        @Override
        protected void onCancelled() {
        super.onCancelled();
        mActivity.finish();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            // TODO Auto-generated method stub
            wsApiCall = new WSApiCall(mActivity);
            return wsApiCall.executeWebservice();
        }

        @Override
        protected void onPostExecute(Integer resul) {
            // TODO Auto-generated method stub
            super.onPostExecute(resul);


            if(mDialog.isShowing())
                mDialog.dismiss();


            if (resul == 1) {
                Toast.makeText(mActivity,"get result",Toast.LENGTH_SHORT).show();

            }
        }

    }





}
