package com.codelytical.flybuy.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.codelytical.flybuy.R
import com.codelytical.flybuy.data.util.Resource
import com.codelytical.flybuy.databinding.FragmentHomeBinding
import com.codelytical.flybuy.presentation.adapter.HomeAdapter
import com.codelytical.flybuy.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    @Inject
    lateinit var homeAdapter: HomeAdapter

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding = FragmentHomeBinding.bind(view)

        homeViewModel.getAllProducts(homeViewModel.loggedIn)

        homeViewModel.products.observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Success -> {
                    homeAdapter.differ.submitList(response.data)
                    homeBinding.homeRecyclerView.visibility = View.VISIBLE
                    Log.i("HomeFragment","${response.data}")
                    homeBinding.loginProgress.loadingProgress.visibility = View.GONE
                }
                is Resource.Loading -> {
                    //binding.homeRecyclerView.visibility = View.INVISIBLE
                    homeBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
                    Log.i("HomeFragment","Loading...")
                }
                is Resource.Error -> {
                    Log.i("HomeFragment","${response.message}")
                    homeBinding.loginProgress.loadingProgress.visibility = View.GONE
                }
            }
        }

        homeBinding.swipeRefresh.setOnRefreshListener {
            homeBinding.swipeRefresh.isRefreshing = false
            homeViewModel.getAllProducts(homeViewModel.loggedIn)
        }

        homeBinding.homeRecyclerView.adapter = homeAdapter
    }
}















