/* Copyright 2014 -onwards ziaagikian <ziaagikian@gmail.com>
*  This program is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.

*  You should have received a copy of the GNU General Public License
*  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.gcm.client;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {

	private TextView mDisplay;
	private String mDeviceId = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		checkNullCondition(Utils.SENDER_ID, "SENDER_ID");
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		mDisplay = (TextView) findViewById(R.id.textView1);
		mDeviceId = GCMRegistrar.getRegistrationId(this);
		if (mDeviceId.equals("")) {
			GCMRegistrar.register(this, Utils.SENDER_ID);
		}
		Log.d("Regd Id", "Regd Id " + mDeviceId);
		mDisplay.setText("RegId= " + mDeviceId);
	}

	private void checkNullCondition(Object reference, String name) {
		if (reference == null) {
			throw new NullPointerException("Config Error " + name);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		GCMRegistrar.unregister(this);
	}
}
