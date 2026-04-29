package formulario.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://kfjvikepvpogdvtwtytt.supabase.co",
            supabaseKey = "sb_publishable_gJfMHMA0ZF4wmvL625BPYA_uXiVa1OH"
        ) {
            install(Postgrest)
        }
    }
}
