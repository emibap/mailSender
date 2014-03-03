package org.haxe.extension;


import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import android.net.Uri;
import android.graphics.Bitmap;
import java.io.FileOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Environment;
import android.util.Base64;

/* 
	You can use the Android Extension class in order to hook
	into the Android activity lifecycle. This is not required
	for standard Java code, this is designed for when you need
	deeper integration.
	
	You can access additional references from the Extension class,
	depending on your needs:
	
	- Extension.assetManager (android.content.res.AssetManager)
	- Extension.callbackHandler (android.os.Handler)
	- Extension.mainActivity (android.app.Activity)
	- Extension.mainContext (android.content.Context)
	- Extension.mainView (android.view.View)
	
	You can also make references to static or instance methods
	and properties on Java classes. These classes can be included 
	as single files using <java path="to/File.java" /> within your
	project, or use the full Android Library Project format (such
	as this example) in order to include your own AndroidManifest
	data, additional dependencies, etc.
	
	These are also optional, though this example shows a static
	function for performing a single task, like returning a value
	back to Haxe from Java.
*/
public class MailSender extends Extension {
	
	
	public static void sendMail (String subject, String body, boolean isHTML, String strTo, String strCC, String strBCC, String base64Img) {
		
		
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		
		
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		
		if (isHTML) {
			emailIntent.setType("text/html");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, android.text.Html.fromHtml(body));
		} else {
			emailIntent.setType("text/plain");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
		}
		
		if (base64Img != null || base64Img != "") emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(storeImage(convertToImage(base64Img))));
		
		Extension.mainActivity.startActivity(Intent.createChooser(emailIntent, "Send an e-mail ..."));
		
	}
	
	public static Bitmap convertToImage(String image){
		try{
			InputStream stream = new ByteArrayInputStream(Base64.decode(image.getBytes(), Base64.DEFAULT));
			Bitmap bitmap = android.graphics.BitmapFactory.decodeStream(stream);
			return bitmap;  
		}
		catch (Exception e) {
			return null;            
		}
	}
	
	private static File storeImage(Bitmap image) {
		File pictureFile = getOutputMediaFile();
		if (pictureFile == null) {
			//Log.d(TAG, "Error creating media file, check storage permissions: ");// e.getMessage());
			return null;
		} 
		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			image.compress(Bitmap.CompressFormat.PNG, 90, fos);
			fos.close();
			
			return pictureFile;
		} catch (java.io.FileNotFoundException e) {
			//Log.d(TAG, "File not found: " + e.getMessage());
			return null;
		} catch (java.io.IOException e) {
			//Log.d(TAG, "Error accessing file: " + e.getMessage());
			return null;
		}  
	}
	
	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(){
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this. 
		File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
				+ "/Android/data/"
				+ Extension.mainContext.getApplicationContext().getPackageName()
				+ "/Files"); 

		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				return null;
			}
		} 
		// Create a media file name
		String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
		File mediaFile;
			String mImageName="MI_"+ timeStamp +".jpg";
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);  
		return mediaFile;
	} 
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		
		return true;
		
	}
	
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		
		
		
	}
	
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		
		
		
	}
	
	
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		
		
		
	}
	
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {
		
		
		
	}
	
	
}