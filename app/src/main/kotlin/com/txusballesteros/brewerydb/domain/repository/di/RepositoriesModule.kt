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
package com.txusballesteros.brewerydb.domain.repository.di

import com.txusballesteros.brewerydb.data.beers.repository.BeersQueryRepositoryImpl
import com.txusballesteros.brewerydb.data.beers.repository.BeersRepositoryImpl
import com.txusballesteros.brewerydb.data.categories.repository.CategoriesRepositoryImpl
import com.txusballesteros.brewerydb.data.glassware.repository.GlasswareRepositoryImpl
import com.txusballesteros.brewerydb.data.styles.repository.StylesRepositoryImpl
import com.txusballesteros.brewerydb.domain.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {
  @Singleton @Provides
  fun provideCategoriesRepository(repository: CategoriesRepositoryImpl): CategoriesRepository = repository

  @Singleton @Provides
  fun provideStylesRepository(repository: StylesRepositoryImpl): StylesRepository = repository

  @Singleton @Provides
  fun provideBeersRepository(repository: BeersRepositoryImpl): BeersRepository = repository

  @Singleton @Provides
  fun provideBeersQueryRepository(repository: BeersQueryRepositoryImpl): BeersQueryRepository = repository

  @Singleton @Provides
  fun provideGlasswareRepository(repository: GlasswareRepositoryImpl): GlasswareRepository = repository
}