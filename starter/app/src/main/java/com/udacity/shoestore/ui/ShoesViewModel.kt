package com.udacity.shoestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoesList

    private val _eventSaveShoe = MutableLiveData<Boolean>()
    val eventSaveShoe: LiveData<Boolean>
        get() = _eventSaveShoe

    private val _eventCancel = MutableLiveData<Boolean>()
    val eventCancel: LiveData<Boolean>
        get() = _eventCancel

    private val _eventAddShoe = MutableLiveData<Boolean>()
    val eventAddShoe: LiveData<Boolean>
        get() = _eventAddShoe


    val shoeName = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()


    init {
        _eventSaveShoe.value = false
        _eventCancel.value = false
        _eventAddShoe.value = false
        shoeName.value = ""
        shoeCompany.value = ""
        shoeSize.value = ""
        shoeDescription.value = ""
        _shoesList.value = ArrayList()
    }

    fun resetShoeFields() {
        shoeName.value = ""
        shoeCompany.value = ""
        shoeSize.value = ""
        shoeDescription.value = ""
    }

    fun addShoeToList() {
        _shoesList.value?.add(
            Shoe(
                shoeName.value.orEmpty(),
                shoeSize.value.orEmpty().toDoubleOrNull() ?: 0.0,
                shoeCompany.value.orEmpty(),
                shoeDescription.value.orEmpty()
            )
        )
    }

    fun saveShoe() {
        _eventSaveShoe.value = true
    }

    fun onSaveShoeComplete() {
        _eventSaveShoe.value = false
        resetShoeFields()
    }

    fun cancel() {
        _eventCancel.value = true
    }

    fun onCancelComplete() {
        _eventCancel.value = false
        resetShoeFields()
    }

    fun navigateShoeAdd() {
        _eventAddShoe.value = true
    }

    fun onShoeAddNavigated() {
        _eventAddShoe.value = false
    }

    fun clearData() {
        _eventSaveShoe.value = false
        _eventCancel.value = false
        _eventAddShoe.value = false
        shoeName.value = ""
        shoeCompany.value = ""
        shoeSize.value = ""
        shoeDescription.value = ""
        _shoesList.value = ArrayList()
    }

}