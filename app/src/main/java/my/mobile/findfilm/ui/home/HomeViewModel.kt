package my.mobile.findfilm.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.mobile.findfilm.api.FilmRepository
import my.mobile.findfilm.data.Film

class HomeViewModel : ViewModel() {
    private val _filmPlayNow = MutableLiveData<List<Film>>().apply {
        FilmRepository.getFilmPlayNow(page = 1,
            onSuccess = {
                value = it.hasil
            },
            onError = {
                Log.d("FilmPlayNow", "Error ketika menampilkan film")
            }
        )
    }

    val filmPlayNow: LiveData<List<Film>> = _filmPlayNow

    private val filmPopular: List<Film> = ArrayList()
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}