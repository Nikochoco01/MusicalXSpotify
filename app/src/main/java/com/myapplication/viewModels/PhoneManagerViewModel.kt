package com.myapplication.viewModels

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.repository.phone.PhoneRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PhoneManagerViewModel: ViewModel(){

	private var _initPermission: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
	val initPermission: LiveData<Boolean> = _initPermission

	private var _phoneFileRecovered: MutableLiveData<Uri> = MutableLiveData<Uri>()
	val phoneFileRecovered: LiveData<Uri> = _phoneFileRecovered

	fun initContext(context: ComponentActivity){
		viewModelScope.launch {
			PhoneRepository.initContext(context)
		}
	}
	fun takePermission(){
		viewModelScope.launch {
				PhoneRepository.takePermission()
					.catch {
						Log.e("permission error", it.toString())
					}
					.collect{
						Log.e("error", "permission collect $it")
						_initPermission.postValue(it)
					}
		}
	}
	fun takePhoneFile(){
		viewModelScope.launch {
			PhoneRepository.takePhoneFile()
				.catch {
					Log.e("phone file error", it.toString())
				}
				.collect{
					Log.e("error", "file collect $it")
					_phoneFileRecovered.postValue(it)
				}
		}
	}
	@RequiresApi(Build.VERSION_CODES.TIRAMISU)
	fun getPermission(){
		viewModelScope.launch {
			PhoneRepository.getPermission()
		}
	}
	fun getPhoneFile(){
		viewModelScope.launch {
			PhoneRepository.getPhoneFile()
		}
	}
}