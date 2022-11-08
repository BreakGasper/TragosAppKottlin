package com.breakgasper.tragosappkottlin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.breakgasper.tragosappkottlin.AppDataBase
import com.breakgasper.tragosappkottlin.R
import com.breakgasper.tragosappkottlin.data.model.DataSource
import com.breakgasper.tragosappkottlin.data.model.Drink
import com.breakgasper.tragosappkottlin.databinding.FragmentFavoritosBinding
import com.breakgasper.tragosappkottlin.databinding.FragmentMainBinding
import com.breakgasper.tragosappkottlin.databinding.FragmentTragosDetallesBinding
import com.breakgasper.tragosappkottlin.domain.RepoImpl
import com.breakgasper.tragosappkottlin.ui.viewmodel.MainViewModel
import com.breakgasper.tragosappkottlin.ui.viewmodel.VMFactory
import com.breakgasper.tragosappkottlin.vo.Resource


class FavoritosFragment : Fragment(), MainAdapter.OnTragoClickListener {

    private var _binding: FragmentFavoritosBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel> { VMFactory(
        RepoImpl(
            DataSource(
        AppDataBase.getDatabase((requireContext().applicationContext)))
        )
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()
        setupObservers()

    }

    private fun setupObservers(){
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading ->{

                }
                is Resource.Success ->{
                    Log.d("LISTA FAVORITOS", "${result.data}")
                    val lista = result.data.map {
                        Drink(it.idTrago,it.imagen,it.nombre,it.descrpcion,it.hasAlcohol)
                    }

                    binding.rvTragosFavoritos.adapter= MainAdapter(requireContext(),lista, this)
                }

                is Resource.Failure ->{

                }
            }
        })
    }
    private  fun setupRecycleView(){
        binding.rvTragosFavoritos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTragosFavoritos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

     override fun onTragoClick(drink: Drink, position: Int) {
      viewModel.deleteDrink(drink)
//        binding.rvTragosFavoritos.adapter?.notifyItemRemoved(position)
//        binding.rvTragosFavoritos.adapter?.notifyItemRangeRemoved(position, binding.rvTragosFavoritos.adapter?.itemCount!!)
   }
}