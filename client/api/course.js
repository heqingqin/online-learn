import request from '@/utils/request'
export default {
  //分页条件课程查询
  getCoursePage(page,limit,seacherObj) {
    return request({
      url: `/eduservice/coursefront/getCourseFrontList/${page}/${limit}`,
      method: 'post',
      data:seacherObj
    })
  },
  //查询所有分类的方法
  getSubjectAll() {
    return request({
      url: `/eduservice/subject/getAllSubject`,
      method: 'get'
    })
  },

  //课程详情查询
  getCourseFrontInfo(id) {
    return request({
      url: `/eduservice/coursefront/getCourseFrontInfo/${id}`,
      method: 'get'
    })
  },
  //根据课程名查询课程
  getCourseByName(name){
    return request({
      url:`/eduservice/course/getCourseByName/${name}`,
      method: 'get'
    })
  }
}
