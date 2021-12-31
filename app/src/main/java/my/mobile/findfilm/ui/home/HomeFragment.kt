package my.mobile.findfilm.ui.home

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import my.mobile.findfilm.DetailFilmActivity
import my.mobile.findfilm.R
import my.mobile.findfilm.adapter.FilmAdapter
import my.mobile.findfilm.adapter.FilmHorizontalAdapter
import my.mobile.findfilm.api.Repository
import my.mobile.findfilm.data.Film
import my.mobile.findfilm.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), FilmAdapter.OnSelectData, FilmHorizontalAdapter.OnSelectData {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var progressDialog: ProgressDialog

    private val filmPlayNow: MutableList<Film> = mutableListOf()
    private val films: MutableList<Film> = mutableListOf()

    private lateinit var filmAdapter: FilmAdapter
    private lateinit var filmPlayNowAdapter: FilmHorizontalAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle(getString(R.string.tolong_tunggu))
        progressDialog.setCancelable(false)
        progressDialog.setMessage(getString(R.string.menampilkan_data))


        filmAdapter = FilmAdapter(requireActivity(),films,this)
        binding.rvRekomendasi.adapter = filmAdapter

        filmPlayNowAdapter = FilmHorizontalAdapter(requireActivity(),filmPlayNow,this)
        binding.rvNowPlaying.adapter = filmPlayNowAdapter

        binding.cariFilm.queryHint = getString(R.string.cari_film)
        binding.cariFilm.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?) = p0?.let { searchFilm(it) } ?: run {false}

            override fun onQueryTextChange(p0: String?) = p0?.let {when(it.trim()){
                "" -> getFilm()
                else -> false
            } } ?: run {false}
        })

        val searchPlateID: Int = binding.cariFilm.context.resources.getIdentifier("android:id/search_plate",null,null)
        val searchPlate: View? = binding.cariFilm.findViewById(searchPlateID)
        searchPlate?.setBackgroundColor(Color.TRANSPARENT)

        binding.rvNowPlaying.setHasFixedSize(true)
        binding.rvNowPlaying.layoutManager = CardSliderLayoutManager(requireActivity())
        CardSnapHelper().attachToRecyclerView(binding.rvNowPlaying)

        binding.rvRekomendasi.setHasFixedSize(true)
        binding.rvRekomendasi.layoutManager = LinearLayoutManager(activity)

        getFilmPlayNow()
        getFilm()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getFilmPlayNow(){
        progressDialog.show()

        Repository.getFilmPlayNow(page = 1,
            onSuccess = {
                progressDialog.dismiss()
                filmPlayNow.clear()
                it.hasil.forEach { film ->
                    filmPlayNow.add(film)
                    filmPlayNowAdapter.notifyDataSetChanged()
                }
            },
            onError = {
                progressDialog.dismiss()
                Toast.makeText(context,"Error menampilkan film play now!",Toast.LENGTH_SHORT).show()
            }
        )
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun searchFilm(query: String): Boolean{
        progressDialog.show()

        Repository.searchFilm(query = query,page = 1,
            onSuccess = {
                progressDialog.dismiss()
                films.clear()
                it.hasil.forEach { film ->
                    films.add(film)
                    filmAdapter.notifyDataSetChanged()
                }
            },
            onError = {
                progressDialog.dismiss()
                Toast.makeText(context,"Error dalam mencari film!",Toast.LENGTH_SHORT).show()
            }
        )
        return false
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getFilm(): Boolean {
        progressDialog.show()

        Repository.getFilm(page = 1,
            onSuccess = {
                progressDialog.dismiss()
                films.clear()
                it.hasil.forEach { film ->
                    films.add(film)
                    filmAdapter.notifyDataSetChanged()
                }
            },
            onError = {
                progressDialog.dismiss()
                Toast.makeText(context,"Error dalam menampilkan film!",Toast.LENGTH_SHORT).show()
            }
        )
        return false
    }

    override fun onSelect(film: Film) {
        val intent = Intent(context, DetailFilmActivity::class.java)
        intent.putExtra(DetailFilmActivity.INTENT_EXTRA_NAME,film)
        startActivity(intent)
    }
}