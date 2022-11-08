package com.breakgasper.tragosappkottlin.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.breakgasper.tragosappkottlin.AppDataBase
import com.breakgasper.tragosappkottlin.R
import com.breakgasper.tragosappkottlin.data.model.DataSource
import com.breakgasper.tragosappkottlin.data.model.Drink
import com.breakgasper.tragosappkottlin.data.model.DrinkEntity
import com.breakgasper.tragosappkottlin.databinding.FragmentMainBinding
import com.breakgasper.tragosappkottlin.databinding.FragmentTragosDetallesBinding
import com.breakgasper.tragosappkottlin.domain.RepoImpl
import com.breakgasper.tragosappkottlin.ui.viewmodel.MainViewModel
import com.breakgasper.tragosappkottlin.ui.viewmodel.VMFactory
import com.bumptech.glide.Glide


class TragosDetallesFragment : Fragment() {

    private lateinit var drink : Drink
    private var _binding: FragmentTragosDetallesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel> { VMFactory(RepoImpl(DataSource(
        AppDataBase.getDatabase(requireContext().applicationContext)))) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            drink = it.getParcelable("drink")!!
            Log.d("DETALLES_FRAG","DRINK: ${drink}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTragosDetallesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(drink.imagen).centerCrop().into(binding.ivImageDetalle)
        binding.tvNombreDetalles.text = drink.nombre
        binding.tvDescripcionDetalle.text = drink.descrpcion

        if (drink.hasAlcohol.equals("Non_Alcoholic")){
            binding.tvAlcoholDetalle.text = "Bebida sin alcohol"

        }else{
            binding.tvAlcoholDetalle.setTextColor(Color.RED)
            binding.tvAlcoholDetalle.text = "Bebida con alcohol"
        }

        binding.fabAccion.setOnClickListener{

            viewModel.guardarTrago(DrinkEntity(drink.idTrago, drink.imagen,drink.nombre,drink.descrpcion,drink.hasAlcohol))
            Toast.makeText(requireContext(),drink.nombre+"se agrego a Favoritos", Toast.LENGTH_SHORT).show()

        }
    }

}