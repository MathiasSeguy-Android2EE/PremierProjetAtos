package com.android2ee.formation.mmxiv.atos.premierprojet;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android2ee.formation.mmxiv.atos.premierprojet.arrayadapter.HumanArrayAdapter;
import com.android2ee.formation.mmxiv.atos.premierprojet.model.Human;

public class MainActivity extends FragmentActivity {
	
	/******************************************************************************************/
	/** Attribute **************************************************************************/
	/******************************************************************************************/
	MyFragment myFragment;

	/******************************************************************************************/
	/** Managing LifeCycle **************************************************************************/
	/******************************************************************************************/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// build the view
		setContentView(R.layout.activity_main);
		FragmentManager fm=getSupportFragmentManager();
		myFragment=(MyFragment)fm.findFragmentById(R.id.my_fragment);
	}

	
	/******************************************************************************************/
	/** Managing AlertDialog and Toast **************************************************************************/
	/******************************************************************************************/
	/**
	 * The Dialog constant for the AlertDialog
	 */
	public final int dialogID = 4112008;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog=null;
		switch (id) {
		case dialogID:
			dialog = buildMyFirstAlertDialog();
			break;
		}
		return dialog;
	}

	/**
	 * Build a casual alert dialog
	 * @return
	 */
	private AlertDialog buildMyFirstAlertDialog() {
		// Creation of the AlertDialog Builder
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// No cancel button
		builder.setMessage(getString(R.string.dialog_message,"88")).setCancelable(false);
		// Define the OK button, it's message and its listener
		builder.setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				myFragment.copyItemInEdt();
			}
		});
		// Define the KO button, it's message and its listener
		builder.setNegativeButton(getString(R.string.dialog_no), null);
		// Then create the Dialog
		return builder.create();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPrepareDialog(int, android.app.Dialog)
	 */
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		//The method to use to have dynamic content in AlertDialog
		((AlertDialog)dialog).setMessage(getString(R.string.dialog_message,myFragment.getMessageToPasteFromTheListView()));
		super.onPrepareDialog(id, dialog);
	}

	/******************************************************************************************/
	/** Managing Menu **************************************************************************/
	/******************************************************************************************/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
