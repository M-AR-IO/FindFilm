package my.mobile.findfilm.ui.favorit.tab

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import my.mobile.findfilm.adapter.FilmAdapter
import my.mobile.findfilm.data.Film
import my.mobile.findfilm.databinding.FragmentFavoritTabBinding
import my.mobile.findfilm.realm.RealmHelper

class FilmFavoritFragment : Fragment(), FilmAdapter.OnSelectData {

    private var _binding: FragmentFavoritTabBinding? = null

    private val binding get() = _binding!!

    private lateinit var repo: RealmHelper

    private val list: MutableList<Film> = mutableListOf()
    private lateinit var adapter: FilmAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritTabBinding.inflate(inflater,container,false)

        repo = RealmHelper(requireContext())

        adapter = FilmAdapter(requireActivity(),list,this)
        binding.rvFilmFavorit.layoutManager = LinearLayoutManager(activity)
        binding.rvFilmFavorit.adapter = adapter
        binding.rvFilmFavorit.setHasFixedSize(true)

        setData()

        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setData() {
        list.clear()
        repo.getAllFilmFav().forEach {
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

    override fun onSelect(film: Film) {
        // buka film
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