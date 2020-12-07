import request from '@/utils/request'
export default {
  //添加课程信息功能
  addCourseInfo(courseInfo){
    return request({
      url:`/eduservice/course/addCourseInfo`,
      method: 'post',
      data:courseInfo
    })
  },
  //查询所有讲师
  getTeacherList(){
    return request({
      url:`/eduservice/teacher/findAll`,
      method: 'get'
    })
  },
  //根据课程id查询课程基本信息
  getCourseId(courseId){
    return request({
      url:`/eduservice/course/getCourseInfo/${courseId}`,
      method: 'get'
    })
  },
  //修改课程信息
  updataCourseInfo(courseInfoVo){
    return request({
      url:`/eduservice/course/updateCourseInfo`,
      method: 'post',
      data:courseInfoVo
    })
  },
  //课程信息确认显示
  getPublicCourseInfo(id){
    return request({
      url:`/eduservice/course/getPubishCourseInfo/${id}`,
      method: 'get'
    })
  },
  //确认发布
  publishCourse(id){
    return request({
      url:`/eduservice/course/publishCourse/${id}`,
      method: 'post'
    })
  },
  //查询所有课程
  getlistCourse(id){
    return request({
      url:`/eduservice/course/getCourseList`,
      method: 'get'
    })
  },
  // //分页查询课程
  // getCourseListPage(current,limit){
  //   return request({
  //     url:`/eduservice/course/pageCourse/${current}/${limit}`,
  //     method: 'post'
  //   })
  // },
  //带条件分页查询课程
  getCourseListPage(current,limit,courseQuery){
    return request({
      url:`/eduservice/course/pageCourseCondition/${current}/${limit}`,
      method: 'post',
      data:courseQuery
    })
  },
  //删除课程
  deleteCourse(courseId){
    return request({
      url:`/eduservice/course/${courseId}`,
      method: 'delete'
    })
  },
}
