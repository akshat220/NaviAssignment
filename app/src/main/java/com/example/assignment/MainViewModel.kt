package com.example.assignment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _submitDetailLiveData = MutableLiveData<ViewState>()
    val submitDetailLiveData: LiveData<ViewState> = _submitDetailLiveData

    fun getPrDetails() {
        _submitDetailLiveData.value = ViewState.Loading
        viewModelScope.launch {
            val result = MainRepository.getPrDetails()
            Log.d("DataResponse", result.toString())
            when {
                result.isNullOrEmpty() -> {
                    _submitDetailLiveData.value = ViewState.Error("Something Went Wrong")
                }
                else -> {
                    _submitDetailLiveData.value = ViewState.Success(result)
                }
            }
        }
    }
}

sealed class ViewState {
    object Loading: ViewState()
    data class Error(val error: String): ViewState()
    data class Success(val data: List<PrDataResponse>): ViewState()
}