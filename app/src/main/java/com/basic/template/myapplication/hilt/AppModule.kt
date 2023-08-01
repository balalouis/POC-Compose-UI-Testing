package com.basic.template.myapplication.hilt

import com.basic.template.myapplication.api.ApiWebService
import com.basic.template.myapplication.login.data.datasource.LoginDataSource
import com.basic.template.myapplication.login.data.datasource.LoginDataSourceImpl
import com.basic.template.myapplication.login.data.repo.LoginRepoImpl
import com.basic.template.myapplication.login.domain.repo.LoginRepo
import com.basic.template.myapplication.userlist.data.datasource.UserListDataSource
import com.basic.template.myapplication.userlist.data.datasource.UserListDataSourceImpl
import com.basic.template.myapplication.userlist.data.repo.UserListRepoImpl
import com.basic.template.myapplication.userlist.domain.repo.UserListRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideLoginDataSource(apiWebService: ApiWebService): LoginDataSource {
        return LoginDataSourceImpl(apiWebService)
    }

    @Provides
    fun provideLoginRepository(loginDataSource: LoginDataSource): LoginRepo {
        return LoginRepoImpl(loginDataSource)
    }

    @Provides
    fun provideUserListDataSource(apiWebService: ApiWebService): UserListDataSource {
        return UserListDataSourceImpl(apiWebService)
    }

    @Provides
    fun provideUserListRepository(userListDataSource: UserListDataSource): UserListRepo {
        return UserListRepoImpl(userListDataSource)
    }
}