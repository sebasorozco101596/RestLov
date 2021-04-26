package com.sebasorozcob.www.restlov.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sebasorozcob.www.restlov.R
import com.sebasorozcob.www.restlov.databinding.ActivityRestaurantBinding
import com.sebasorozcob.www.restlov.ui.adapters.PagerAdapter
import com.sebasorozcob.www.restlov.ui.fragments.restaurant.RestaurantChallengesFragment
import com.sebasorozcob.www.restlov.ui.fragments.restaurant.RestaurantMenuFragment
import androidx.navigation.navArgs
import com.sebasorozcob.www.restlov.util.Constants.Companion.RESTAURANT_RESULT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantBinding
    private val args by navArgs<RestaurantActivityArgs>() // For get the information of the restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        binding.toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = arrayListOf<Fragment>()
        fragments.add(RestaurantMenuFragment())
        fragments.add(RestaurantChallengesFragment())

        val titles = ArrayList<String>()
        titles.add("Menu")
        titles.add("Challenges")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RESTAURANT_RESULT_KEY, args.restaurant)

        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.viewPager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) {tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}