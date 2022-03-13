package app.grand.tafwak.core.di.module

import app.grand.tafwak.data.account.data_source.remote.AccountRemoteDataSource
import app.grand.tafwak.data.account.repository.AccountRepositoryImpl
import app.grand.tafwak.data.auth.data_source.remote.AuthRemoteDataSource
import app.grand.tafwak.data.auth.repository.AuthRepositoryImpl
import app.grand.tafwak.data.countries.data_source.CountriesRemoteDataSource
import app.grand.tafwak.data.countries.repository.CountriesRepositoryImpl
import app.grand.tafwak.data.educational.data_source.EducationalRemoteDataSource
import app.grand.tafwak.data.educational.repository.EducationalRepositoryImpl
import app.grand.tafwak.data.exams.data_source.ExamsRemoteDataSource
import app.grand.tafwak.data.exams.repository.ExamsRepositoryImpl
import app.grand.tafwak.data.general.data_source.remote.GeneralRemoteDataSource
import app.grand.tafwak.data.general.repository.GeneralRepositoryImpl
import app.grand.tafwak.data.groupDetails.data_source.GroupDetailsRemoteDataSource
import app.grand.tafwak.data.groupDetails.repository.GroupDetailsRepositoryImpl
import app.grand.tafwak.data.home.data_source.remote.HomeRemoteDataSource
import app.grand.tafwak.data.home.repository.HomeRepositoryImpl
import app.grand.tafwak.data.intro.data_source.IntroRemoteDataSource
import app.grand.tafwak.data.intro.repository.IntroRepositoryImpl
import app.grand.tafwak.data.local.preferences.AppPreferences
import app.grand.tafwak.data.profile.data_source.ProfileDataSource
import app.grand.tafwak.data.profile.repository.ProfileRepositoryImpl
import app.grand.tafwak.data.reviews.data_source.ReviewsRemoteDataSource
import app.grand.tafwak.data.reviews.repository.ReviewsRepositoryImpl
import app.grand.tafwak.data.settings.data_source.remote.SettingsRemoteDataSource
import app.grand.tafwak.data.settings.repository.SettingsRepositoryImpl
import app.grand.tafwak.data.studentTeacher.data_source.StudentTeacherRemoteDataSource
import app.grand.tafwak.data.studentTeacher.repository.StudentTeacherRepositoryImpl
import app.grand.tafwak.data.teacher_group.data_source.TeacherGroupsRemoteDataSource
import app.grand.tafwak.data.teacher_group.repository.TeacherGroupsRepositoryImpl
import app.grand.tafwak.data.teacher_home.data_source.TeacherHomeRemoteDataSource
import app.grand.tafwak.data.teacher_home.repository.TeacherHomeRepositoryImpl
import app.grand.tafwak.data.teacher_profile.data_source.TeacherProfileRemoteDataSource
import app.grand.tafwak.data.teacher_profile.repository.TeacherProfileRepositoryImpl
import app.grand.tafwak.domain.account.repository.AccountRepository
import app.grand.tafwak.domain.auth.repository.AuthRepository
import app.grand.tafwak.domain.countries.repository.CountriesRepository
import app.grand.tafwak.domain.educational.repository.EducationalRepository
import app.grand.tafwak.domain.exams.repository.ExamsRepository
import app.grand.tafwak.domain.general.repository.GeneralRepository
import app.grand.tafwak.domain.groups.repository.GroupDetailsRepository
import app.grand.tafwak.domain.home.repository.HomeRepository
import app.grand.tafwak.domain.intro.repository.IntroRepository
import app.grand.tafwak.domain.profile.repository.ProfileRepository
import app.grand.tafwak.domain.reviews.repository.ReviewsRepository
import app.grand.tafwak.domain.settings.repository.SettingsRepository
import app.grand.tafwak.domain.student_teacher.repository.StudentTeacherRepository
import app.grand.tafwak.domain.teacher_groups.repository.TeacherGroupsRepository
import app.grand.tafwak.domain.teacher_home.repository.TeacherHomeRepository
import app.grand.tafwak.domain.teacher_profile.repository.TeacherProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideGeneralRepository(
    remoteDataSource: GeneralRemoteDataSource,
    appPreferences: AppPreferences
  ): GeneralRepository = GeneralRepositoryImpl(remoteDataSource, appPreferences)

  @Provides
  @Singleton
  fun provideAuthRepository(
    remoteDataSource: AuthRemoteDataSource,
  ): AuthRepository = AuthRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideCountriesRepository(
    remoteDataSource: CountriesRemoteDataSource,
  ): CountriesRepository = CountriesRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideEducationalRepository(
    remoteDataSource: EducationalRemoteDataSource,
  ): EducationalRepository = EducationalRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideAccountRepository(
    remoteDataSource: AccountRemoteDataSource,
    appPreferences: AppPreferences
  ): AccountRepository = AccountRepositoryImpl(remoteDataSource, appPreferences)

  @Provides
  @Singleton
  fun provideSettingsRepository(
    remoteDataSource: SettingsRemoteDataSource
  ): SettingsRepository = SettingsRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideHomeRepository(
    remoteDataSource: HomeRemoteDataSource
  ): HomeRepository = HomeRepositoryImpl(remoteDataSource)


  @Provides
  @Singleton
  fun provideIntroRepository(
    remoteDataSource: IntroRemoteDataSource
  ): IntroRepository = IntroRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideStudentTeacherRepository(
    remoteDataSource: StudentTeacherRemoteDataSource
  ): StudentTeacherRepository = StudentTeacherRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideTeacherProfileRepository(
    remoteDataSource: TeacherProfileRemoteDataSource
  ): TeacherProfileRepository = TeacherProfileRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideReviewsRepository(
    remoteDataSource: ReviewsRemoteDataSource
  ): ReviewsRepository = ReviewsRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideGroupDetailsRepository(
    remoteDataSource: GroupDetailsRemoteDataSource
  ): GroupDetailsRepository = GroupDetailsRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideUpdateProfileRepository(
    remoteDataSource: ProfileDataSource
  ): ProfileRepository = ProfileRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideExamsRepository(
    remoteDataSource: ExamsRemoteDataSource
  ): ExamsRepository = ExamsRepositoryImpl(remoteDataSource)

  //Teacher
  @Provides
  @Singleton
  fun provideTeacherHomeRepository(
    remoteDataSource: TeacherHomeRemoteDataSource
  ): TeacherHomeRepository = TeacherHomeRepositoryImpl(remoteDataSource)
@Provides
  @Singleton
  fun provideTeacherGroupsRepository(
    remoteDataSource: TeacherGroupsRemoteDataSource
  ): TeacherGroupsRepository = TeacherGroupsRepositoryImpl(remoteDataSource)

}