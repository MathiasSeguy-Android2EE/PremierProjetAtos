/**<ul>
 * <li>PremierProjetAtos</li>
 * <li>com.android2ee.formation.mmxiv.atos.premierprojet.arrayadapter</li>
 * <li>3 févr. 2014</li>
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
package com.android2ee.formation.mmxiv.atos.premierprojet.arrayadapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.mmxiv.atos.premierprojet.R;
import com.android2ee.formation.mmxiv.atos.premierprojet.model.Human;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class HumanArrayAdapter extends ArrayAdapter<Human> {
	/******************************************************************************************/
	/** Factorize heavy objects creation **************************************************************************/
	/******************************************************************************************/

	Drawable humanOddPict;
	Drawable humanEvenPict;
	LayoutInflater inflater;
	boolean postJB;

	/**
	 * @param context
	 * @param resource
	 */
	public HumanArrayAdapter(Context context, ArrayList<Human> humans) {
		super(context, R.layout.human_odd_layout, humans);
		humanOddPict = context.getResources().getDrawable(R.drawable.ic_human_odd);
		humanEvenPict = context.getResources().getDrawable(R.drawable.ic_human_even);
		inflater = LayoutInflater.from(context);
		postJB=context.getResources().getBoolean(R.bool.postJellyBean);
	}


	/******************************************************************************************/
	/** Never use temp variable in getView **************************************************************************/
	/******************************************************************************************/
	private View rowView;
	private Human human;
	private ViewHolder viewHolder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// use the convert view
		rowView = convertView;
		if (rowView == null) {
			if(getItemViewType(position)==0) {
				rowView = inflater.inflate(R.layout.human_even_layout, null);
			}else {
				rowView = inflater.inflate(R.layout.human_odd_layout, null);
			}
			viewHolder = new ViewHolder(rowView);
			rowView.setTag(viewHolder);
		}
		// find your item
		human = getItem(position);
		//retireve the viewHolder
		viewHolder=(ViewHolder) rowView.getTag();
		// update your view
		viewHolder.getTxnName().setText(human.getName());
		viewHolder.getTxnMessage().setText(human.getMessage());
		if (human.getPictureId() == R.drawable.ic_human_even) {
			if(postJB) {
				viewHolder.getImvPicture().setBackground(humanEvenPict);
			}else {
				viewHolder.getImvPicture().setBackgroundDrawable(humanEvenPict);
			}
		} else {
			if(postJB) {
				viewHolder.getImvPicture().setBackground(humanOddPict);
			}else {
				viewHolder.getImvPicture().setBackgroundDrawable(humanOddPict);
			}
			
		}
		return rowView;
	}
	
	/******************************************************************************************/
	/** Managing even/odd objects **************************************************************************/
	/******************************************************************************************/

	/* (non-Javadoc)
	 * @see android.widget.BaseAdapter#getItemViewType(int)
	 */
	@Override
	public int getItemViewType(int position) {
		return position%2;
	}

	/* (non-Javadoc)
	 * @see android.widget.BaseAdapter#getViewTypeCount()
	 */
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	
	/******************************************************************************************/
	/** ViewHolder **************************************************************************/
	/******************************************************************************************/


	/**
	 * @author Mathias Seguy (Android2EE)
	 * @goals
	 *        This class aims to be the viewHolder (
	 */
	private class ViewHolder {
		TextView txnName;
		TextView txnMessage;
		ImageView imvPicture;
		View view;

		/**
		 * @param view
		 */
		public ViewHolder(View view) {
			super();
			this.view = view;
		}

		/**
		 * @return the txnName
		 */
		public final TextView getTxnName() {
			if (null == txnName) {
				txnName = (TextView) view.findViewById(R.id.txvName);
			}
			return txnName;
		}

		/**
		 * @return the txnMessage
		 */
		public final TextView getTxnMessage() {
			if (null == txnMessage) {
				txnMessage = (TextView) view.findViewById(R.id.txvMessage);
			}
			return txnMessage;
		}

		/**
		 * @return the imvPicture
		 */
		public final ImageView getImvPicture() {
			if (null == imvPicture) {
				imvPicture = (ImageView) view.findViewById(R.id.imvHuman);
			}
			return imvPicture;
		}

	}
}
