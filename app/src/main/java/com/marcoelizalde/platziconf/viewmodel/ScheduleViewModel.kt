package com.marcoelizalde.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcoelizalde.platziconf.model.Conference
import com.marcoelizalde.platziconf.network.Callback
import com.marcoelizalde.platziconf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel : ViewModel() {
    val firestoreService= FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase();
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object: Callback<List<Conference>>{
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }

        })
    }

    fun processFinished(){
        isLoading.value = true
    }

}