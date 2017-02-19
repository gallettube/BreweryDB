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
package com.txusballesteros.brewerydb.data.categories.repository

import com.txusballesteros.brewerydb.data.categories.strategy.GetCategoriesStrategy
import com.txusballesteros.brewerydb.data.model.CategoryDataModel
import com.txusballesteros.brewerydb.data.model.CategoryDataModelMapper
import com.txusballesteros.brewerydb.data.strategy.Strategy
import com.txusballesteros.brewerydb.domain.model.Category
import com.txusballesteros.brewerydb.domain.repository.CategoriesRepository
import com.txusballesteros.brewerydb.domain.repository.Repository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(private val getCategoriesStrategy: GetCategoriesStrategy.Builder,
                                                   private val mapper: CategoryDataModelMapper):
                               CategoriesRepository {

  override fun getCategories(callback: Repository.RepositoryCallback<List<Category>>) {
    getCategoriesStrategy.build().execute(callback = object: Strategy.Callback<List<CategoryDataModel>>() {
      override fun onResult(result: List<CategoryDataModel>?) {
        val categories = mapper.map(result)
        callback.onResult(categories!!)
      }
    })
  }
}