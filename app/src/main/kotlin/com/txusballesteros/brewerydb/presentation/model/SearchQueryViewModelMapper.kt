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
package com.txusballesteros.brewerydb.presentation.model

import com.txusballesteros.brewerydb.domain.model.SearchQuery
import javax.inject.Inject

class SearchQueryViewModelMapper @Inject constructor() {
  fun map(source: SearchQueryViewModel): SearchQuery
      = SearchQuery(source.keyword,
                    source.abvMin,
                    source.abvMax,
                    source.ibuMin,
                    source.ibuMax,
                    source.isOrganic,
                    source.breweryId,
                    source.styleId,
                    source.styleName,
                    source.withLabels)

  fun map(source: SearchQuery): SearchQueryViewModel
      = SearchQueryViewModel(source.keyword,
                             source.abvMin,
                             source.abvMax,
                             source.ibuMin,
                             source.ibuMax,
                             source.isOrganic,
                             source.breweryId,
                             source.styleId,
                             source.styleName,
                             source.withLabels)
}