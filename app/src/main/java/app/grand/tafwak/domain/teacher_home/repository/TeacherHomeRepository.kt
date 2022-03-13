package app.grand.tafwak.domain.teacher_home.repository
import app.grand.tafwak.domain.teacher_home.entity.TeacherHomeData
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface TeacherHomeRepository {
  suspend fun teacherHome(): Resource<BaseResponse<TeacherHomeData>>
}