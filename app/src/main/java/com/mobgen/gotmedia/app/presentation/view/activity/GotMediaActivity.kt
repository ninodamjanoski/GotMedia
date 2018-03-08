package com.mobgen.gotmedia.presentation

import android.os.Bundle
import com.mobgen.gotmedia.core.activity.base.ActivityBase
import com.mobgen.gotmedia.R
import com.mobgen.gotmedia.app.presentation.view.fragment.GotCategoriesParentFragment


class GotMediaActivity : ActivityBase() {

    private var gotCategoriesParentFragment: GotCategoriesParentFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gotCategoriesParentFragment = supportFragmentManager.findFragmentByTag(GotCategoriesParentFragment.TAG)
                as? GotCategoriesParentFragment
        if(gotCategoriesParentFragment == null){
            gotCategoriesParentFragment = GotCategoriesParentFragment.newInstance()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.activityContent, gotCategoriesParentFragment, GotCategoriesParentFragment.TAG)
            ft.commit()
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onBackPressed() {
        if(!gotCategoriesParentFragment!!.onBackNavigation()){
            super.onBackPressed()
        }
    }
}
