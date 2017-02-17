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
package com.txusballesteros.brewerydb.domain.usecase.beers

import com.txusballesteros.brewerydb.domain.model.Beer
import com.txusballesteros.brewerydb.domain.model.BeersQuery
import com.txusballesteros.brewerydb.domain.repository.BeersRepository
import com.txusballesteros.brewerydb.domain.repository.Repository
import com.txusballesteros.brewerydb.domain.usecase.UseCaseCallback
import com.txusballesteros.brewerydb.exception.ApplicationException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class GetNextPageBeersInteractor @Inject constructor(private val executor: ExecutorService,
                                                     private val beersRepository: BeersRepository): GetNextPageBeersUseCase {
  override fun execute(query: BeersQuery, callback: UseCaseCallback<List<Beer>>) {
    doAsync(executorService = executor) {
      try {
        beersRepository.getNextPageBeers(query, object : Repository.RepositoryCallback<List<Beer>> {
          override fun onResult(result: List<Beer>) {
            uiThread {
              callback.onResult(result)
            }
          }
        })
      } catch (error: ApplicationException) {
        uiThread {
          callback.onError(error)
        }
      }
    }
  }
}