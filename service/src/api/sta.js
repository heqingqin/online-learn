import request from '@/utils/request'
export default {
  //统计某一天的注册人数，并把他它加到数据库中
  createStaData(day){
    return request({
      url: '/staservice/sta/registerCount/'+day,
      method: 'post'
    })
  },
  //获取统计数据
  getDataStatus(searchObj){
    return request({
      url: `/staservice/sta/showChart/${searchObj.begin}/${searchObj.end}/${searchObj.type}`,
      method: 'get'
    })
  }
}
