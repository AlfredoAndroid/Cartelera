package mx.test.cartelera.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    var isVisibleOverview: MutableLiveData<Boolean> = MutableLiveData(true)

    fun setVisibleOverview(isVisible: Boolean) {
        Log.d("TAG1", "hola")
        isVisibleOverview.value = isVisible
    }
}