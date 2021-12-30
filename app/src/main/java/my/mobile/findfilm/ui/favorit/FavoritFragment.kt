package my.mobile.findfilm.ui.favorit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import my.mobile.findfilm.R
import my.mobile.findfilm.adapter.FavoritTabAdapter
import my.mobile.findfilm.databinding.FragmentFavoritBinding

class FavoritFragment : Fragment() {

    private var _binding: FragmentFavoritBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.vpFavorit.adapter = FavoritTabAdapter(childFragmentManager)

        binding.tabFavorit.setupWithViewPager(binding.vpFavorit)

        binding.tabFavorit.getTabAt(0)?.text = getString(R.string.film)
        binding.tabFavorit.getTabAt(1)?.text = getString(R.string.tv_show)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}