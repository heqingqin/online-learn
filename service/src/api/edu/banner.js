import request from '@/utils/request'
export default {
  //根据id查询
  getBannerById(id){
    return request({
      url:`/educms/banneradmin/get/${id}`,
      method: 'get'
    })
  },
  //分页查询
  getPageBanner(page,limit){
    return request({
      url:`/educms/banneradmin/pageBanner/${page}/${limit}`,
      method: 'get'
    })
  },
  //添加贴片
  addBanner(banner){
    return request({
      url:`/educms/banneradmin/addPanner`,
      method: 'post',
      data:banner
    })
  },
  //更新贴片
  updateBanner(banner){
    return request({
      url:`/educms/banneradmin/update`,
      method: 'post',
      data:banner
    })
  },
  //删除贴片
  deleteVideo(id){
    return request({
      url:`/educms/banneradmin/remove/${id}`,
      method: 'delete'
    })
  },
}
