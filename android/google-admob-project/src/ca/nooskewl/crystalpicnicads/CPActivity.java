package ca.nooskewl.crystalpicnicads;

import org.liballeg.android.AllegroActivity;
import android.content.Intent;
import java.io.File;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.content.IntentFilter;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.games.Games;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdListener;
import android.content.IntentSender.SendIntentException;
import android.content.DialogInterface;
import java.net.InetAddress;
import java.util.Locale;

public class CPActivity extends AllegroActivity implements ConnectionCallbacks, OnConnectionFailedListener {

	/* load libs */
	static {
		System.loadLibrary("allegro_monolith");
		System.loadLibrary("bass");
		System.loadLibrary("bassmidi");
		System.loadLibrary("crystalpicnic");
	}

	MyBroadcastReceiver bcr;
	InterstitialAd mInterstitialAd;
	boolean connected = true;

	public CPActivity()
	{
		super("libcrystalpicnic.so");
	}

	public void logString(String s)
	{
		Log.d("CrystalPicnic", s);
	}

	public String getSDCardPrivateDir()
	{
		File f = getExternalFilesDir(null);
		if (f != null) {
			return f.getAbsolutePath();
		}
		else {
			return getFilesDir().getAbsolutePath();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	
		bcr = new MyBroadcastReceiver();
		
		mResolvingError = savedInstanceState != null && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);

		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-5564002345241286/6542550250");

		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				requestNewInterstitial();
			}
		});

		requestNewInterstitial();

		boolean thread_started = false;
		do {
			try {
				Thread t = new Thread(new Runnable() {
					public void run() {
						while (true) {
							try {
								connected = InetAddress.getByName("nooskewl.ca").isReachable(30000);
							}
							catch (Exception e) {
								connected = false;
							}
						}
					}
				});
				t.start();
				thread_started = true;
			}
			catch (Exception e) {
			}
		} while (thread_started == false);
	}

	public void onResume() {
		super.onResume();

		registerReceiver(bcr, new IntentFilter("android.intent.action.DREAMING_STARTED"));
		registerReceiver(bcr, new IntentFilter("android.intent.action.DREAMING_STOPPED"));

		play_services_available = check_play_services_status();
	}

	public void onPause() {
		super.onPause();

		unregisterReceiver(bcr);
	}
	
	public boolean gamepadAlwaysConnected()
	{
		return getPackageManager().hasSystemFeature("android.hardware.touchscreen") == false;
	}

	public void onStart() {
		super.onStart();

		if (play_services_available) {
			api_client.connect();
		}
	}

	public void onStop() {
		super.onStop();

		if (play_services_available) {
			api_client.disconnect();
		}
	}

	boolean play_services_available;
	GoogleApiClient api_client;
	// Request code to use when launching the resolution activity
	private static final int REQUEST_RESOLVE_ERROR = 1001;
	// Unique tag for the error dialog fragment
	private static final String DIALOG_ERROR = "dialog_error";
	// Bool to track whether the app is already resolving an error
	private boolean mResolvingError = false;

	public void onConnectionFailed(ConnectionResult result) {
		play_services_available = false;
		if (mResolvingError) {
			// Already attempting to resolve an error.
			return;
		}
		else if (result.hasResolution()) {
			try {
				mResolvingError = true;
				result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
			}
			catch (SendIntentException e) {
				// There was an error with the resolution intent. Try again.
				api_client.connect();
			}
		}
		else {
			// Show dialog using GooglePlayServicesUtil.getErrorDialog()
			showErrorDialog(result.getErrorCode());
			mResolvingError = true;
		}
	}

	/* Creates a dialog for an error message */
	private void showErrorDialog(int errorCode) {
		// Create a fragment for the error dialog
		ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
		// Pass the error that should be displayed
		Bundle args = new Bundle();
		args.putInt(DIALOG_ERROR, errorCode);
		dialogFragment.setArguments(args);
		dialogFragment.show(getSupportFragmentManager(), "errordialog");
	}
	
	/* Called from ErrorDialogFragment when the dialog is dismissed. */
	public void onDialogDismissed() {
		mResolvingError = false;
	}

	/* A fragment to display an error dialog */
	public static class ErrorDialogFragment extends DialogFragment {
		public ErrorDialogFragment() { }

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Get the error code and retrieve the appropriate dialog
			int errorCode = this.getArguments().getInt(DIALOG_ERROR);
			//return GoogleApiAvailability.getInstance().getErrorDialog(this.getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
			return GooglePlayServicesUtil.getErrorDialog(errorCode, this.getActivity(), REQUEST_RESOLVE_ERROR);
		}

		@Override
		public void onDismiss(DialogInterface dialog) {
			((CPActivity)getActivity()).onDialogDismissed();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_RESOLVE_ERROR) {
			mResolvingError = false;
			if (resultCode == RESULT_OK) {
				// Make sure the app is not already connected or attempting to connect
				if (!api_client.isConnecting() && !api_client.isConnected()) {
					api_client.connect();
				}
			}
		}
	}

	private static final String STATE_RESOLVING_ERROR = "resolving_error";

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
	}

	private boolean check_play_services_status() {
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

		if (status == ConnectionResult.SUCCESS) {
			return true;
		}
		else {
			Dialog d = GooglePlayServicesUtil.getErrorDialog(status, this, 0);
			d.show();
			return false;
		}
	}

	public void onConnected(Bundle connectionHint) {
		play_services_available = true;
		if (play_services_available && api_client.isConnected()) {
			Games.setViewForPopups(api_client, findViewById(android.R.id.content));
		}
	}

	public void onConnectionSuspended(int cause) {
	}

	public void init_play_services() {
		play_services_available = check_play_services_status();

		if (play_services_available) {
			api_client = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(Games.API)
				.addScope(Games.SCOPE_GAMES)
				.build();

			api_client.connect();
		}
	}
	
	public void unlock_achievement(String id) {
		if (play_services_available) {
			logString("Achieving " + id);
			try {
				Games.Achievements.unlock(api_client, id);
			}
			catch (Exception e) {
			}
		}
		else {
			logString("Play Services not available, can't achieve :(");
		}
	}

	public String get_android_language()
	{
		return Locale.getDefault().getLanguage();
	}

	public void requestNewInterstitial()
	{
		AdRequest adRequest = new AdRequest.Builder().addTestDevice("FIXME-FOR-TESTING").build();
		mInterstitialAd.loadAd(adRequest);
	}

	public void showAd()
	{
		runOnUiThread(new Runnable() {
			public void run() {
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				}
			}
		});
	}

	public boolean connected_to_internet()
	{
		return connected;
	}
}
