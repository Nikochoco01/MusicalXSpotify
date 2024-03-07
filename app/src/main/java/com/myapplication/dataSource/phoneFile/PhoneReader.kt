package com.myapplication.dataSource.phoneFile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi

class PhoneReader {
	private lateinit var _activityContext: ComponentActivity
	private lateinit var _takePermission: ActivityResultLauncher<String>
	private lateinit var _takeResultLauncher: ActivityResultLauncher<Intent>

	fun initContext(context: ComponentActivity){
		_activityContext = context
	}

	fun takePermission(): Boolean{
		var result = false
		_takePermission = _activityContext.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isPermissionGranted ->
			result = handlePermissionResult(isPermissionGranted, _activityContext)
		}
		return result
	}

	fun takePhoneFile(): Uri?{
		var result: Uri? = null
		_takeResultLauncher = _activityContext.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
			result =  handleFilesResult(activityResult, _activityContext)
		}
		return result
	}

	fun getPhoneFile(){
		val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
		_takeResultLauncher.launch(intent)
	}
	@RequiresApi(Build.VERSION_CODES.TIRAMISU)
	fun getPermission(){
	  _takePermission.launch(android.Manifest.permission.READ_MEDIA_AUDIO)
	}

	private fun getToast(context: Context, message: String, length: Int){
		Toast.makeText(context, message, length).show()
	}

	private fun handlePermissionResult(isPermissionGranted: Boolean, context: Context): Boolean{
		if (isPermissionGranted) {
			getToast(context, "Access to local files: Allowed", Toast.LENGTH_LONG)
			return true
		} else {
			getToast(context, "If you want use your local music. You need to allow app to access your local storage", Toast.LENGTH_LONG)
			return false
		}
	}

	private fun handleFilesResult(result: ActivityResult, context: Context): Uri? {
		if (result.resultCode == Activity.RESULT_OK) {
			getToast(context, "Recovery in progress", Toast.LENGTH_LONG)
			return result.data?.data
		}
		else{
			getToast(context, "The directory cannot be read", Toast.LENGTH_LONG)
			return null
		}
	}

	companion object{
		private lateinit var instance: PhoneReader
		fun initPhoneReader(){
			instance = PhoneReader()
		}

		fun getInstance(): PhoneReader {
			return instance
		}
	}
}