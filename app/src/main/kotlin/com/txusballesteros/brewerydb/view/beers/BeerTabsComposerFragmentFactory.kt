/*
 * Copyright Txus Ballesteros 2017 (@txusballesteros)
 *
 * This file is part of Foobar.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.brewerydb.view.beers

import android.support.v4.app.FragmentManager
import javax.inject.Inject

class BeerTabsComposerFragmentFactory @Inject constructor() {
  fun getBeerDetailFragment(fragmentManager: FragmentManager, beerId: String ): BeerDetailFragment {
    val tag = BeerDetailFragment::class.java.name
    val fragment = fragmentManager.findFragmentByTag(tag) ?: BeerDetailFragment.newInstance(beerId)
    return fragment as BeerDetailFragment
  }

  fun getBeerIngredientsFragment(fragmentManager: FragmentManager, beerId: String ): BeerIngredientsFragment {
    val tag = BeerIngredientsFragment::class.java.name
    val fragment = fragmentManager.findFragmentByTag(tag) ?: BeerIngredientsFragment.newInstance(beerId)
    return fragment as BeerIngredientsFragment
  }

  fun getBeerBreweriesFragment(fragmentManager: FragmentManager, beerId: String ): BeerBreweriesFragment {
    val tag = BeerBreweriesFragment::class.java.name
    val fragment = fragmentManager.findFragmentByTag(tag) ?: BeerBreweriesFragment.newInstance(beerId)
    return fragment as BeerBreweriesFragment
  }
}