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
package com.txusballesteros.brewerydb.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.Application
import com.txusballesteros.brewerydb.di.ApplicationComponent
import com.txusballesteros.brewerydb.view.di.DaggerViewComponent
import com.txusballesteros.brewerydb.view.di.ViewComponent

abstract class AbsFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater?.inflate(onRequestLayoutResourceId(), container, false)
  }

  abstract fun onRequestLayoutResourceId() : Int

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    onRequestInjection()
    onRequestViewComposition()
    onViewReady(savedInstanceState)
  }

  private fun onRequestInjection() {
    val applicationComponent = getApplicationComponent()
    val viewComponent = DaggerViewComponent.builder().applicationComponent(applicationComponent)
                              .build()
    onRequestInjection(viewComponent)
  }

  abstract protected fun onRequestInjection(viewComponent: ViewComponent)

  open fun onRequestViewComposition() { }

  private fun getApplicationComponent() : ApplicationComponent {
    val application = activity.application as Application
    return application.applicationComponent
  }

  open fun onViewReady(savedInstanceState: Bundle?) { }
}