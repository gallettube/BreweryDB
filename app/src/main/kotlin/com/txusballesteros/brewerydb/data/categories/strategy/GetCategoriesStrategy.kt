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
package com.txusballesteros.brewerydb.data.categories.strategy

import com.txusballesteros.brewerydb.data.categories.datasource.CategoriesCloudDataSource
import com.txusballesteros.brewerydb.data.categories.datasource.CategoriesLocalDataSource
import com.txusballesteros.brewerydb.data.model.CategoryDataModel
import com.txusballesteros.brewerydb.data.strategy.LocalOrCloudStrategy
import javax.inject.Inject

class GetCategoriesStrategy private constructor(private val localDataSource: CategoriesLocalDataSource,
                                                private val cloudDataSource: CategoriesCloudDataSource):
                            LocalOrCloudStrategy<Void, List<CategoryDataModel>>() {

  override fun onRequestCallToLocal(params: Void?): List<CategoryDataModel>? {
    return localDataSource.getList()
  }

  override fun onRequestCallToCloud(params: Void?): List<CategoryDataModel>? {
    val result = cloudDataSource.getList()
    localDataSource.store(result)
    return result
  }

  override fun isValid(result: List<CategoryDataModel>?): Boolean {
    return result != null && !result.isEmpty()
  }

  class Builder @Inject constructor(private val localDataSource: CategoriesLocalDataSource,
                                    private val cloudDataSource: CategoriesCloudDataSource) {
    fun build(): GetCategoriesStrategy {
      return GetCategoriesStrategy(localDataSource, cloudDataSource)
    }
  }
}