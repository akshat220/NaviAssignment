package com.example.assignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _submitDetailLiveData = MutableLiveData<ViewState>()
    val submitDetailLiveData: LiveData<ViewState> = _submitDetailLiveData

    fun submitDetails() {
        _submitDetailLiveData.value = ViewState.Loading
        viewModelScope.launch {
            val submitText = MainRepository.submitData()
            _submitDetailLiveData.value = ViewState.SubmitSuccessful(submitText)
        }
    }

    fun validateData(pan: String?, day: String?, month: String?, year: String?) {
        if (pan.isNullOrEmpty() || day.isNullOrEmpty() || month.isNullOrEmpty() || year.isNullOrEmpty()) {
            _submitDetailLiveData.value = ViewState.ActivateButton(false)
            return
        }
        val boolean = (pan.length == 10) && (day.toInt() <= 31) && (month.toInt() <= 12) && (year.toInt() <= 2022)
        _submitDetailLiveData.value = ViewState.ActivateButton(boolean)
    }
}

sealed class ViewState {
    object Loading: ViewState()
    data class SubmitSuccessful(val submitText: String): ViewState()
    data class ActivateButton(val boolean: Boolean): ViewState()
}