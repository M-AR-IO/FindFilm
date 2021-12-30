package my.mobile.findfilm.ui.tv

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
import my.mobile.findfilm.DetailTvActivity
import my.mobile.findfilm.R
import my.mobile.findfilm.adapter.TvAdapter
import my.mobile.findfilm.adapter.TvHorizontalAdapter
import my.mobile.findfilm.api.Repository
import my.mobile.findfilm.data.Television
import my.mobile.findfilm.databinding.FragmentHomeBinding
import my.mobile.findfilm.databinding.FragmentTvBinding

class TelevisionFragment : Fragment(), TvAdapter.OnSelectData, TvHorizontalAdapter.OnSelectData {
    private var _binding: FragmentTvBinding? = null

    private lateinit var progressDialog: ProgressDialog

    private val tvPlayNow: MutableList<Television> = mutableListOf()
    private val tvs: MutableList<Television> = mutableListOf()

    private lateinit var tvAdapter: TvAdapter
    private lateinit var tvPlayNowAdapter: TvHorizontalAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        val root: View = binding.root

        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle(getString(R.string.tolong_tunggu))
        progressDialog.setCancelable(false)
        progressDialog.setMessage(getString(R.string.menampilkan_data))


        tvAdapter = TvAdapter(requireActivity(),tvs,this)
        binding.rvRekomendasi.adapter = tvAdapter

        tvPlayNowAdapter = TvHorizontalAdapter(requireActivity(),tvPlayNow,this)
        binding.rvNowPlaying.adapter = tvPlayNowAdapter

        binding.cariFilm.queryHint = getString(R.string.cari_film)
        binding.cariFilm.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?) = p0?.let { searchTv(it) } ?: run {false}

            override fun onQueryTextChange(p0: String?) = p0?.let {when(it.trim()){
                "" -> getTv()
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

        getTvPlayNow()
        getTv()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getTvPlayNow(){
        progressDialog.show()

        Repository.getTvPlayNow(page = 1,
            onSuccess = {
                progressDialog.dismiss()
                tvPlayNow.clear()
                it.hasil.forEach { film ->
                    tvPlayNow.add(film)
                    tvPlayNowAdapter.notifyDataSetChanged()
                }
            },
            onError = {
                progressDialog.dismiss()
                Toast.makeText(context,"Error menampilkan tv play now!", Toast.LENGTH_SHORT).show()
            }
        )
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun searchTv(query: String): Boolean{
        progressDialog.show()

        Repository.searchTv(query = query,page = 1,
            onSuccess = {
                progressDialog.dismiss()
                tvs.clear()
                it.hasil.forEach { tv ->
                    tvs.add(tv)
                    tvAdapter.notifyDataSetChanged()
                }
            },
            onError = {
                progressDialog.dismiss()
                Toast.makeText(context,"Error dalam mencari tv!", Toast.LENGTH_SHORT).show()
            }
        )
        return false
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getTv(): Boolean {
        progressDialog.show()

        Repository.getTv(page = 1,
            onSuccess = {
                progressDialog.dismiss()
                tvs.clear()
                it.hasil.forEach { tv ->
                    tvs.add(tv)
                    tvAdapter.notifyDataSetChanged()
                }
            },
            onError = {
                progressDialog.dismiss()
                Toast.makeText(context,"Error dalam menampilkan tv!", Toast.LENGTH_SHORT).show()
            }
        )
        return false
    }

    override fun onSelect(tv: Television) {
        val intent = Intent(context,DetailTvActivity::class.java)
        intent.putExtra(DetailTvActivity.INTENT_EXTRA_NAME,tv)
        startActivity(intent)
    }
}