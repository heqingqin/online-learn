import request from '@/utils/request'
export default {
  //课程列表
  getAllSubject(){
    return request({
      url:`/eduservice/subject/getAllSubject`,
      method: 'get',
    })
  }
}
