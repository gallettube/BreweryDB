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
package com.txusballesteros.brewerydb.data.model

import com.txusballesteros.brewerydb.domain.model.Beer
import javax.inject.Inject

class BeerDataModelMapper @Inject constructor() {
  fun map(source: BeerDataModel): Beer
      = Beer(source.id,
             source.name,
             source.displayName,
             source.description,
             source.styleId,
             source.abv,
             source.ibu,
             source.glasswareId,
             source.isOrganic,
             source.status,
             map(source.label),
             source.servingTemperature,
             source.servingTemperatureDisplay)

  fun map(source: BeerDataModel.LabelDataModel?): Beer.Label?
    = source?.let {
      Beer.Label(source.icon,
                 source.medium,
                 source.large)
    }
}