package com.sample.ecology.ui.index

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sample.ecology.LoadingDialogUtil
import com.sample.ecology.R
import com.sample.ecology.databinding.ActivityMainBinding
import com.sample.ecology.ui.animal.AnimalAreaListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = getString(R.string.taipei_city_Zoo)

        loadFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        LoadingDialogUtil.cancel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment() {
        val fragment: AnimalAreaListFragment = AnimalAreaListFragment.newInstance()

        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container,
            fragment
        ).addToBackStack(fragment.javaClass.simpleName).commit()

        currentFragment = fragment
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .hide(currentFragment)
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .add(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()

        currentFragment = fragment
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        val fragment: Fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)!!

        if (count == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack(
                fragment.javaClass.simpleName,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }
}