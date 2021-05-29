package com.example.lawerapp.View

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.lawerapp.Adapters.ViewPagerAdapter
import com.example.lawerapp.R
import com.example.lawerapp.View.HomeFragments.CategoryFragment
import com.example.lawerapp.View.HomeFragments.FavouriteFragment
import com.example.lawerapp.View.HomeFragments.HomeFragment
import com.example.lawerapp.View.HomeFragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var adapter: ViewPagerAdapter
    var prevMenuItem: MenuItem?=null
    var homeFragment: HomeFragment = HomeFragment()
    var categoryFragment: CategoryFragment = CategoryFragment()
    var profileFragment: ProfileFragment = ProfileFragment()
    var favouriteFragment:FavouriteFragment=FavouriteFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        activityDesign()
        contentview()
    }

    fun contentview(){
        viewPager = findViewById<ViewPager>(R.id.viewpager)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigationView)
        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(homeFragment, "Home")
        adapter.addFragment(categoryFragment, "Category")
        adapter.addFragment(favouriteFragment, "Favourite")
        adapter.addFragment(profileFragment, "Profile")
        viewPager.setAdapter(adapter)
        viewPager.setOffscreenPageLimit(4)
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.setChecked(false)
                } else {
                    bottomNavigationView.menu.getItem(0).isChecked = false
                }
                Log.d("page", "onPageSelected: $position")
                bottomNavigationView.menu.getItem(position).isChecked = true
                prevMenuItem = bottomNavigationView.menu.getItem(position)
                val window = window
                if (position == 3) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//  set status text dark
                        window.statusBarColor = resources.getColor(R.color.major_back)
                    }
                } else {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.decorView.systemUiVisibility =
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
                        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//  set status text dark
                        window.statusBarColor = resources.getColor(R.color.major_back)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        //////////////////////////////////////////////////////////
        ///////////////////Bottom Navigation Bar/////////////////////////////
        //////////////////////////////////////////////////////////
        ///////////////////Bottom Navigation Bar/////////////////////////////
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val window = window
            when (item.itemId) {
                R.id.navigation_home -> {
                    viewPager.setCurrentItem(0)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//  set status text dark
                        window.statusBarColor = resources.getColor(R.color.major_back)
                    }
                }
                R.id.navigation_catg -> {
                    viewPager.setCurrentItem(1)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.decorView.systemUiVisibility =
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
                        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//  set status text dark
                        window.statusBarColor = resources.getColor(R.color.major_back)
                    }
                }
                R.id.navigation_favourite -> {
                    viewPager.setCurrentItem(2)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.decorView.systemUiVisibility =
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
                        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//  set status text dark
                        window.statusBarColor = resources.getColor(R.color.major_back)
                    }
                }
                R.id.navigation_profile -> {
                    viewPager.setCurrentItem(3)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//  set status text dark
                        window.statusBarColor = resources.getColor(R.color.major_back)
                    }
                }
                else -> {
                    viewPager.setCurrentItem(0)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.decorView.systemUiVisibility =
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
                        //window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//  set status text dark
                        window.statusBarColor = resources.getColor(R.color.major_back)
                    }
                }
            }
            false
        }
    }
    fun activityDesign() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
            window.navigationBarColor = resources.getColor(R.color.white)
        }
    }
}