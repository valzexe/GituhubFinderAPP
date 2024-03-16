package com.a211d4ky4355.bangkit2024.githubappuser.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.a211d4ky4355.bangkit2024.githubappuser.data.response.UserItem
import com.a211d4ky4355.bangkit2024.githubappuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { v, actionId, event ->
                    val searchUser: String
                    searchUser = searchView.text.toString()
                    mainViewModel.getUser(searchUser)
                    searchView.hide()
                    false
                }
        }

        mainViewModel.listUser.observe(this) { listUser ->
            setUser(listUser)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
    }

    private fun setUser(userItem: List<UserItem>) {
        val adapter = UserAdapter()
        adapter.submitList(userItem)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

}