package com.example.appricottesttask.di

import com.example.appricottesttask.ui.details_character.DetailsFragment
import com.example.appricottesttask.ui.list_characters.CharactersFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Qualifier
import javax.inject.Scope


@[Component(modules = [AppModule :: class]) AppScope]
interface AppComponent {
    fun inject(fragment: CharactersFragment)
    fun inject(fragment: DetailsFragment)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun baseUrl(@BaseUrlQualifier baseUrl : String) : Builder

        fun build() : AppComponent

    }

}

@Qualifier
annotation class BaseUrlQualifier

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope