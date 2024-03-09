package com.myapplication.repository.users
class UserMusicalManager {
	var isConnected: Boolean = false
	var userID: Int =0

	companion object{
		private lateinit var instance: UserMusicalManager
		fun initUserManager(){
			instance = UserMusicalManager()
			instance.isConnected = false
		}

		fun getInstance(): UserMusicalManager{
			return instance
		}
	}
}