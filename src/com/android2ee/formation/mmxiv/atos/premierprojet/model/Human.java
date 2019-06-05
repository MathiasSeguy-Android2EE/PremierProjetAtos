/**<ul>
 * <li>PremierProjetAtos</li>
 * <li>com.android2ee.formation.mmxiv.atos.premierprojet.model</li>
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
package com.android2ee.formation.mmxiv.atos.premierprojet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.android2ee.formation.mmxiv.atos.premierprojet.R;
import com.google.gson.Gson;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class Human implements Parcelable {
	String name;
	String message;
	int pictureId;

	/**
	 * 
	 */
	public Human() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param message
	 * @param pictureId
	 */
	public Human(String name, String message, int pictureId) {
		super();
		this.name = name;
		this.message = message;
		this.pictureId = pictureId;
	}

	/**
	 * @param message
	 */
	public Human(String message) {
		this(message, 0);

	}

	/**
	 * @param message
	 */
	public Human(String message, int position) {
		super();
		this.name="toto";
		this.message = message;
		if (position % 2 == 0) {
			pictureId = R.drawable.ic_human_even;
		} else {
			pictureId = R.drawable.ic_human_odd;
		}
	}

	/**
	 * @return the name
	 */
	public final String getName() {

		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the message
	 */
	public final String getMessage() {

		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public final void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the pictureId
	 */
	public final int getPictureId() {

		return pictureId;
	}

	/**
	 * @param pictureId
	 *            the pictureId to set
	 */
	public final void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	/******************************************************************************************/
	/** Json IO **************************************************************************/
	/******************************************************************************************/


	/**
	 * The string reprsentation in json of this
	 * 
	 * @return
	 */
	public String toJSon() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @param humanMarshalToString
	 * @return
	 */
	public static Human fromJson(String humanMarshalToString) {
		Gson gson = new Gson();
		return gson.fromJson(humanMarshalToString, Human.class);
	}
	/******************************************************************************************/
	/** Parcelabelabilite du truc **************************************************************************/
	/******************************************************************************************/


    protected Human(Parcel in) {
        name = in.readString();
        message = in.readString();
        pictureId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(message);
        dest.writeInt(pictureId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Human> CREATOR = new Parcelable.Creator<Human>() {
        @Override
        public Human createFromParcel(Parcel in) {
            return new Human(in);
        }

        @Override
        public Human[] newArray(int size) {
            return new Human[size];
        }
    };
}