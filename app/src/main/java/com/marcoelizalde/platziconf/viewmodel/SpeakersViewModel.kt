package com.marcoelizalde.platziconf.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcoelizalde.platziconf.model.Speaker
import com.marcoelizalde.platziconf.network.Callback
import com.marcoelizalde.platziconf.network.FirestoreService

class SpeakersViewModel : ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeakers : MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()


    fun refresh() {
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase()  {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }

}