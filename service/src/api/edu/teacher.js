import request from '@/utils/request'
export default {
  //讲师列表，分页查询
  /**
   *current:当前页
   * limit:分页数
   * teacherQuery:查询条件
   */
  getTeacherListPage(current,limit,teacherQuery){
    return request({
      // url: '/eduservice/teacher/pageTeacherCondition/'+current+"/"+limit,
      url:`/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把数据转化成json数据传递到接口
      data:teacherQuery
    })
  },
  //删除讲师
  deleteTeacherById(id){
    return request({
      url:`/eduservice/teacher/${id}`,
      method: 'delete',
    })
  },
  //添加讲师
  addTeacher(eduTeacher){
    return request({
      url:`/eduservice/teacher/addTeacher`,
      method: 'post',
      //teacherQuery条件对象，后端使用RequestBody获取数据
      //data表示把数据转化成json数据传递到接口
      data:eduTeacher
    })
  },
  //根据id查询讲师信息
  getTeacherInfo(id){
    return request({
      url:`/eduservice/teacher/getTeacher/${id}`,
      method: 'get'
    })
  },
  //修改讲师信息
  updateTeacher(teacher){
    return request({
      url:`/eduservice/teacher/updateTeacher`,
      method: 'post',
      data:teacher
    })
  }
}
