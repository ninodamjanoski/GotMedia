package com.mobgen.gotmedia.presentation

import android.os.Bundle
import com.mobgen.gotmedia.core.activity.base.ActivityBase
import com.mobgen.gotmedia.R
import com.mobgen.gotmedia.app.presentation.view.fragment.GotCategoriesParentFragment


class GotMediaActivity : ActivityBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var carAttributesParentFragment = supportFragmentManager.findFragmentByTag(GotCategoriesParentFragment.TAG)
                as? GotCategoriesParentFragment
        if(carAttributesParentFragment == null){
            carAttributesParentFragment = GotCategoriesParentFragment.newInstance()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.activityContent, carAttributesParentFragment, GotCategoriesParentFragment.TAG)
            ft.commit()
            supportFragmentManager.executePendingTransactions()
        }
    }
}
