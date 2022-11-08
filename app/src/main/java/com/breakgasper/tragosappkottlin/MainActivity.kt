package com.breakgasper.tragosappkottlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.ui.NavigationUI
import com.breakgasper.tragosappkottlin.data.model.DataSource
import com.breakgasper.tragosappkottlin.domain.RepoImpl
import com.breakgasper.tragosappkottlin.ui.viewmodel.MainViewModel
import com.breakgasper.tragosappkottlin.ui.viewmodel.VMFactory

/*
* Parte 1: Crear una naviegacion y llamar componentes por Binding
* Parte 2: Arquitectura MVM
* parte 3: ReclerView
* parte 4: Retrofit
* */

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


/*paquete:
    ui: Contiene las navegaciones a distintos destinos (Fragmentass)
    domain: tener interfaz del repositorio
    data: informacion del servidor -step-1
    vo: value object
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        //View-Arrow-Back
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}