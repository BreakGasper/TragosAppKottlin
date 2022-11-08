package com.breakgasper.tragosappkottlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.breakgasper.tragosappkottlin.AppDataBase
import com.breakgasper.tragosappkottlin.R
import com.breakgasper.tragosappkottlin.data.model.DataSource
import com.breakgasper.tragosappkottlin.data.model.Drink
import com.breakgasper.tragosappkottlin.databinding.FragmentMainBinding
import com.breakgasper.tragosappkottlin.domain.RepoImpl
import com.breakgasper.tragosappkottlin.ui.viewmodel.MainViewModel
import com.breakgasper.tragosappkottlin.ui.viewmodel.VMFactory
import com.breakgasper.tragosappkottlin.vo.Resource


class MainFragment : Fragment(), MainAdapter.OnTragoClickListener {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource())) }
    private val viewModel by activityViewModels<MainViewModel> { VMFactory(RepoImpl(DataSource(
        AppDataBase.getDatabase(requireContext().applicationContext)))) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setUpSearchView()
        setUpObservers()
        binding.btnFavoritos.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
        }
        /* binding.btnIrDetalles.setOnClickListener{
             findNavController().navigate(R.id.tragosDetallesFragment)
         }*/
    }

    private fun setUpObservers() {
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvTragos.adapter = MainAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error al traer los datos ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setTrago(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
//                viewModel.setTrago(p0!!)
                return false
            }
        })
    }

    override fun onTragoClick(drink: Drink, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment_to_tragosDetallesFragment, bundle)
    }

    private fun setupRecyclerView() {
        binding.rvTragos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTragos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


    }


}