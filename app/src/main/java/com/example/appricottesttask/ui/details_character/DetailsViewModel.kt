package com.example.appricottesttask.ui.details_character

import androidx.lifecycle.*
import com.example.data.model.Character
import com.example.data.repository.details.DetailsRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class DetailsViewModel(
    private val repository: DetailsRepository
) : ViewModel() {

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    private val _character: MutableLiveData<Character?> = MutableLiveData()
    val character: LiveData<Character?> get() = _character

    fun getCharacter(id : Int){
        viewModelScope.launch {
            repository.getCharacter(id).suspendOnSuccess {
                _character.postValue(data)
            }
                .onError {
                    _error.postValue(message())
                }
                .onException {
                    _error.postValue(message())
                }
        }
    }


    class Factory @Inject constructor(
        private val
        repository: Provider<DetailsRepository>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(repository.get()) as T
        }

    }
}