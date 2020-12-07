import request from '@/utils/request'
export default {
  //查询热门课程和名师
  getIndexDate(limitT,limitC){
    return request({
      url: `/eduservice/indexfront/index/${limitT}/${limitC}`,
      method: 'get'
    })
  }
}
