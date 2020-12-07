import request from '@/utils/request'
export default {
  //添加小节
  addVideo(video){
    return request({
      url:`/eduservice/video/addVideo`,
      method: 'post',
      data:video
    })
  },
  //根据id查询小节
  getVideo(videoId){
    return request({
      url:`/eduservice/video/getVideoInfo/${videoId}`,
      method: 'get'
    })
  },
  //更新小节
  updateVideo(video){
    return request({
      url:`/eduservice/video/updateVideo`,
      method: 'post',
      data:video
    })
  },
  //根据id删除小节
  deleteVideo(videoId){
    return request({
      url:`/eduservice/video/${videoId}`,
      method: 'delete'
    })
  },
  //根据id删除视频
  deleteAliyunVod(id){
    return request({
      url:`/eduvod/video/removeAlyVideo/${id}`,
      method: 'delete'
    })
  }
}
