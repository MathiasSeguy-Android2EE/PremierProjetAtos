/**<ul>
 * <li>PremierProjetAtos</li>
 * <li>com.android2ee.formation.mmxiv.atos.premierprojet</li>
 * <li>7 févr. 2014</li>
 * 
 * <li>======================================================</li>
 *
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 *
 /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br> 
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * 
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br> 
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */
package com.android2ee.formation.mmxiv.atos.premierprojet;

import java.util.ArrayList;

import com.android2ee.formation.mmxiv.atos.premierprojet.arrayadapter.HumanArrayAdapter;
import com.android2ee.formation.mmxiv.atos.premierprojet.model.Human;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class MyFragment extends Fragment {
	/******************************************************************************************/
	/** Constants for the bundle to save/restore context when rotating device **************************************************************************/
	/******************************************************************************************/

	/**
	 * Key for the LsvResult in the bundle
	 */
	private static final String LSV_RESULT_KEY_FOR_BUNDLE = "txvResultKeyForBundle";
	/**
	 * Key for the LsvResult in the bundle
	 */
	private static final String LSV_RESULT_SIZE_KEY_FOR_BUNDLE = "txvResultSizeKeyForBundle";
	/**
	 * Key for the edtMessage in the bundle
	 */
	private static final String EDT_MESSAGE_KEY_FOR_BUNDLE = "edtMessageKeyForBundle";

	/******************************************************************************************/
	/** Attribute **************************************************************************/
	/******************************************************************************************/
	/**
	 * The EditText
	 */
	EditText edtMessage;
	/**
	 * The textView to disaply all added message
	 */
	ListView lsvResult;
	/**
	 * The list of messages displayed by the listview
	 */
	ArrayList<Human> humans;
	/**
	 * The arrayAdapter of the list view lsvResult
	 */
	HumanArrayAdapter arrayAdapter;
	/**
	 * The button add
	 */
	Button btnAdd;
	/**
	 * As named
	 */
	String messageToPasteFromTheListView=null;
	/******************************************************************************************/
	/** Temp Variables **************************************************************************/
	/******************************************************************************************/
	/**
	 * The temp human used by the selectedItem method
	 */
	private Human selectedHuman;
	/******************************************************************************************/
	/** Constructors **************************************************************************/
	/******************************************************************************************/

	/**
	 * 
	 */
	public MyFragment() {
		// TODO Auto-generated constructor stub
	}

	/******************************************************************************************/
	/** Managing life cycle **************************************************************************/
	/******************************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);

		// instantiate the graphical components
		edtMessage = (EditText) view.findViewById(R.id.edtMessage);
		btnAdd = (Button) view.findViewById(R.id.btnAdd);
		// Instantiate the listView
		lsvResult = (ListView) view.findViewById(R.id.lsvResult);
		humans = new ArrayList<Human>();
		// Human tempH;
		// for (int i = 0; i < 800; i++) {
		// tempH = new Human("toto " + i, "No message, noFuture", i);
		// humans.add(tempH);
		// }
		
		return view;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		arrayAdapter = new HumanArrayAdapter(getActivity(), humans);
		lsvResult.setAdapter(arrayAdapter);
		// Add clickListeners
		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addItem();
			}
		});
		lsvResult.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				itemSelected(position);
			}
		});
		
		//Restore the user context if a creation/destruction has been done
		if(savedInstanceState!=null) {
			// edtMessage.setText(savedInstanceState.getString(EDT_MESSAGE_KEY_FOR_BUNDLE));
			// messages.clear();
			// messages.addAll(savedInstanceState.getStringArrayList(LSV_RESULT_KEY_FOR_BUNDLE));
			// humans.clear();

			// Using Gson
			// t0ReadGson = System.nanoTime();
			//
			// for (int i = 0; i < savedInstanceState.getInt(LSV_RESULT_SIZE_KEY_FOR_BUNDLE); i++) {
			// humans.add(Human.fromJson(savedInstanceState.getString(LSV_RESULT_KEY_FOR_BUNDLE + i)));
			// }
			// t1ReadGson = System.nanoTime();
			// dReadGson=t1ReadGson - t0ReadGson;
			// Log.e("MainActivity", "Gson spent " + (dReadGson) +
			// " nanos to extract the data from bundle");

			// Using Parcelable
			t0ReadParcel = System.nanoTime();
			for (Parcelable parcel : savedInstanceState.getParcelableArrayList(LSV_RESULT_KEY_FOR_BUNDLE + "Parcel")) {
				humans.add((Human) parcel);
			}
			t1ReadParcel = System.nanoTime();
			dReadParcel = t1ReadParcel - t0ReadParcel;
			Log.w("MainActivity", "Parcel spent " + (dReadParcel) + " nanos to extract the data from bundle");
			// Log.e("MainActivity", "Parcel is faster with a ratio of 1 for "+(dReadGson/dReadParcel));
		}
		
		super.onActivityCreated(savedInstanceState);
	}
	/******************************************************************************************/
	/** Managing Destroy/Immediate create **************************************************************************/
	/******************************************************************************************/
		/**
	 * Time used to determine the Parcelabilisation time spent to do the action
	 */
	long t0WriteParcel, t1WriteParcel, t0ReadParcel, t1ReadParcel, dReadParcel, dWriteParcel;
	/**
	 * Time used to determine the Gsonabilisation time spent to do the action
	 */
	long t0WriteGson, t1WriteGson, t0ReadGson, t1ReadGson, dReadGson, dWriteGson;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// save edtMessage and lsvResult
		// outState.putString(EDT_MESSAGE_KEY_FOR_BUNDLE, edtMessage.getText().toString());

		// Using Gson
		// t0WriteGson = System.nanoTime();
		// int i = 0;
		// outState.putInt(LSV_RESULT_SIZE_KEY_FOR_BUNDLE, humans.size());
		// for (Human hum : humans) {
		// outState.putString(LSV_RESULT_KEY_FOR_BUNDLE + i, hum.toJSon());
		// i++;
		// }
		// t1WriteGson = System.nanoTime();
		// dWriteGson=t1WriteGson - t0WriteGson;
		// Log.e("MainActivity", "Gson spent " + (dWriteGson) +
		// " nanoseconds to store the data from bundle");

		// using Parcealable
		t0WriteParcel = System.nanoTime();
		outState.putParcelableArrayList(LSV_RESULT_KEY_FOR_BUNDLE + "Parcel", humans);
		t1WriteParcel = System.nanoTime();
		dWriteParcel = t1WriteParcel - t0WriteParcel;
		Log.w("MainActivity", "Parcel spent " + (dWriteParcel) + " nanoSecond to store the data from bundle");
		// Log.e("MainActivity",
		// "Parcel is faster with a ratio of 1 for "+(dWriteGson/dWriteParcel));
		// outState.putStringArrayList(LSV_RESULT_KEY_FOR_BUNDLE, messages);
	}

	
	
	/******************************************************************************************/
	/** Functional Methods **************************************************************************/
	/******************************************************************************************/

	/**
	 * Called by the btnAdd.onClick
	 * Copy the content of the editText within the TxvResult
	 */
	private void addItem() {
		String message = edtMessage.getText().toString();
		// build the human
		Human hum = new Human(message, humans.size());
		// 1° method
		arrayAdapter.add(hum);
		// 2° method
		// messages.add(message);
		// arrayAdapter.notifyDataSetChanged();
		// and flush
		edtMessage.setText("");
	}

	/**
	 * Called when an item of the ListView is clicked
	 * 
	 * @param position
	 */
	private void itemSelected(int position) {
		messageToPasteFromTheListView=arrayAdapter.getItem(position).getMessage();
		getActivity().showDialog(((MainActivity)getActivity()).dialogID);
	}

	/**
	 * Copy the selected human's message within the edtMessage
	 */
	 void copyItemInEdt() {
		edtMessage.setText(messageToPasteFromTheListView);
	}

	/**
	 * @return the messageToPasteFromTheListView
	 */
	public final String getMessageToPasteFromTheListView() {
	
	return messageToPasteFromTheListView;}
	

}
