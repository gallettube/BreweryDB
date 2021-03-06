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
package com.txusballesteros.brewerydb.navigation

import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.txusballesteros.brewerydb.navigation.commands.*
import com.txusballesteros.brewerydb.presentation.model.IngredientTypeViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import javax.inject.Inject

class Navigator @Inject constructor() {
  fun navigateToBeerDetail(from: NavigationContext, beerId: String) {
    val navigationCommand = BeerDetailNavigationCommand(beerId)
    navigate(from, navigationCommand)
  }

  fun navigateToIngredientDetail(from: NavigationContext, ingredientId: Int, ingredientType: IngredientTypeViewModel) {
    val navigationCommand = IngredientDetailNavigationCommand(ingredientId, ingredientType)
    navigate(from, navigationCommand)
  }

  fun navigateToBreweryDetail(from: NavigationContext, breweryId: String) {
    val navigationCommand = BreweryDetailNavigationCommand(breweryId)
    navigate(from, navigationCommand)
  }

  fun navigateToUrl(from: NavigationContext, url: String) {
    val navigationCommand = UrlNavigationCommand(url)
    navigate(from, navigationCommand)
  }

  fun navigateToSearch(from: NavigationContext) {
    val navigationCommand = SearchNavigationCommand()
    navigate(from, navigationCommand)
  }

  fun navigateToStyleSelector(from: NavigationContext) {
    val navigationCommand = StyleSelectorNavigationCommand()
    navigate(from, navigationCommand)
  }

  fun navigateToAbout(from: NavigationContext) {
    val navigationCommand = AboutNavigationCommand()
    navigate(from, navigationCommand)
  }

  private fun navigate(navigationContext: NavigationContext, command: NavigationCommand) {
    val options = prepareSharedElement(navigationContext)
    val from = navigationContext.from
    if (from is AbsFragment) {
      val intent = command.build(from.activity)
      if (command.navigateForResult) {
        from.startActivityForResult(intent, command.requestCode, options)
      } else {
        from.startActivity(intent, options)
      }
    }
  }

  private fun prepareSharedElement(navigationContext: NavigationContext): Bundle? {
    var result: Bundle? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val activity = navigationContext.activity
      result = activity?.let {
          val sharedElements = navigationContext.sharedElements.orEmpty()
                                      .map { Pair<View, String>(it, it.transitionName) }.toTypedArray()
          ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *sharedElements).toBundle()
      }
    }
    return result
  }
}