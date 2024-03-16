package com.a211d4ky4355.bangkit2024.githubappuser.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.a211d4ky4355.bangkit2024.githubappuser.data.response.UserItem
import com.a211d4ky4355.bangkit2024.githubappuser.databinding.FragmentFollowBinding
import com.a211d4ky4355.bangkit2024.githubappuser.ui.main.UserAdapter

class FollowFragment : Fragment() {

    private var binding: FragmentFollowBinding? = null
    private lateinit var detailUserViewModel: DetailUserViewModel

    private var position: Int? = null
    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }

        detailUserViewModel = ViewModelProvider(this)[DetailUserViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireActivity())
        binding?.rvFollow?.layoutManager = layoutManager
        binding?.rvFollow?.setHasFixedSize(true)

        detailUserViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        if (position == 1) {
            username?.let { detailUserViewModel.setFollowers(it) }
            detailUserViewModel.listFollowers.observe(viewLifecycleOwner) { listFollowers ->
                setFollow(listFollowers)
            }
        } else {
            username?.let { detailUserViewModel.setFollowing(it) }
            detailUserViewModel.listFollowing.observe(viewLifecycleOwner) {listFollowing ->
                setFollow(listFollowing)
            }
        }

    }
    private fun setFollow(userItem: List<UserItem>) {
        val adapter = UserAdapter()
        adapter.submitList(userItem)
        binding?.rvFollow?.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        binding?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = ""
    }
}