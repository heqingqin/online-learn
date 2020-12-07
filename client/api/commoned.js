import request from '@/utils/request'
export default {
  // 分页查询数据
  getPageList(page, limit, courseId) {
    return request({
      url: `/eduservice/comment/${page}/${limit}/${courseId}`,
      method: 'get'
    })
  },
  //添加评论
  addComment(comment) {
    return request({
      url: `/eduservice/comment/save`,
      method: 'post',
      data: comment
    })
  }
}
