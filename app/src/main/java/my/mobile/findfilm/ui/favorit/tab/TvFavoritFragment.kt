package my.mobile.findfilm.ui.favorit.tab

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import my.mobile.findfilm.DetailTvActivity
import my.mobile.findfilm.adapter.TvAdapter
import my.mobile.findfilm.data.Television
import my.mobile.findfilm.databinding.FragmentFavoritTabBinding
import my.mobile.findfilm.realm.RealmHelper

class TvFavoritFragment : Fragment(), TvAdapter.OnSelectData {

    private var _binding: FragmentFavoritTabBinding? = null

    private val binding get() = _binding!!

    private lateinit var repo: RealmHelper

    private val list: MutableList<Television> = mutableListOf()
    private lateinit var adapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritTabBinding.inflate(inflater,container,false)

        repo = RealmHelper(requireContext())

        adapter = TvAdapter(requireActivity(),list,this)
        binding.rvFilmFavorit.layoutManager = LinearLayoutManager(activity)
        binding.rvFilmFavorit.adapter = adapter
        binding.rvFilmFavorit.setHasFixedSize(true)

        setData()

        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setData() {
        list.clear()
        repo.getAllTvFav().forEach {
            list.add(it)
        }
        if (list.isEmpty()){
            binding.tvNotFound.visibility = View.VISIBLE
            binding.rvFilmFavorit.visibility = View.GONE
        } else {
            binding.tvNotFound.visibility = View.GONE
            binding.rvFilmFavorit.visibility = View.VISIBLE
            adapter.notifyDataSetChanged()
        }
    }

    override fun onSelect(tv: Television) {
        val intent = Intent(context, DetailTvActivity::class.java)
        intent.putExtra(DetailTvActivity.INTENT_EXTRA_NAME,tv)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        setData()
    }
}