package com.myapplication.repository.phone

import android.net.Uri
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.myapplication.dataSource.phoneFile.PhoneReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object PhoneRepository {

	fun initContext(context: ComponentActivity){
		PhoneReader.getInstance().initContext(context)
	}
	suspend fun takePermission(): Flow<Boolean> = flow{
		emit(PhoneReader.getInstance().takePermission())
	}
	suspend fun takePhoneFile(): Flow<Uri?> = flow{
		emit(PhoneReader.getInstance().takePhoneFile())
	}
	@RequiresApi(Build.VERSION_CODES.TIRAMISU)
	fun getPermission(){
		PhoneReader.getInstance().getPermission()
	}

	fun getPhoneFile(){
		PhoneReader.getInstance().getPhoneFile()
	}
}