package formulario.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import formulario.data.repository.FormularioRepositoryImpl
import formulario.domain.repository.FormularioRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsFormularioRepository(
        impl: FormularioRepositoryImpl
    ) : FormularioRepository

}
