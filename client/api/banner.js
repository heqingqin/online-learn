import request from '@/utils/request'
export default {
  //查询前2个banner
  getListBanner(){
    return request({
      url: '/educms/bannerfront/getAllBanner',
      method: 'get'
    })
  }
}
