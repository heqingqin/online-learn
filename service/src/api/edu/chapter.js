import request from '@/utils/request'
export default {
  //根据课程id查询课程列表
  getAllChapterVideo(courseId){
    return request({
      url:`/eduservice/chapter/getChapterVideo/${courseId}`,
      method: 'get'
    })
  },

  //添加章节
  addChapter(chapter){
    return request({
      url:`/eduservice/chapter/addChapter`,
      method: 'post',
      data:chapter
    })
  },
  //根据id查询章节
  getChapter(chapterId){
    return request({
      url:`/eduservice/chapter/getChapterInfo/${chapterId}`,
      method: 'get'
    })
  },
  //更新章节
  updateChapter(chapter){
    return request({
      url:`/eduservice/chapter/updateChapter`,
      method: 'post',
      data:chapter
    })
  },
  //根据id删除章节
  deleteChapter(chapterId){
    return request({
      url:`/eduservice/chapter/${chapterId}`,
      method: 'delete'
    })
  },



}
