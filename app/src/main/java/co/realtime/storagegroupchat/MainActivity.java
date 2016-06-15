package co.realtime.storagegroupchat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import domains.Message;
import handlers.StorageHandler;

import interfaces.InterfaceRefresher;
import preferences.PreferencesManager;

public class MainActivity extends ActionBarActivity implements InterfaceRefresher {

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        EditText inputUser = (EditText) this.findViewById(R.id.nickNameInput);
		
		final Button bt = (Button) findViewById(R.id.chatRooms);
		bt.setEnabled(false);
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, ChatRoomActivity.class);
				MainActivity.this.startActivity(myIntent);				
			}
		});

		String user = PreferencesManager.getInstance(this).loadUser();

		if(user != null){
            String welcomeLabel = String.format(getString(R.string.welcomeLabel), user);
			inputUser.setText(welcomeLabel);
			inputUser.setEnabled(false);
		}else{
			inputUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		        @Override
		        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		            if (actionId == EditorInfo.IME_ACTION_DONE) {
		            	PreferencesManager.getInstance(MainActivity.this).saveUser(v.getText().toString());
		            	v.setEnabled(false);
		            	
		            	if(StorageHandler.selfHandler.isConnected){
		            		bt.setEnabled(true);
		            		bt.setBackgroundColor(Color.WHITE);
		            		return true;
		            	}
		            }
		            return false;
		        }

		    });
		}

        StorageHandler.prepareClient(getApplicationContext(),this);
	}

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void refreshData(Message msg) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				TextView connectionLabel = (TextView)findViewById(R.id.connectionLabel);
				connectionLabel.setText(getString(R.string.storageConnected));
				Button chatRooms = (Button)findViewById(R.id.chatRooms);
				if(PreferencesManager.getInstance(MainActivity.this).loadUser() != null){
					chatRooms.setEnabled(true);
					chatRooms.setBackgroundColor(Color.WHITE);
				}				
			}
		});
		
	}
}
