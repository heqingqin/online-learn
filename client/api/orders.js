import request from '@/utils/request'
export default {
  //生成订单
  createOrder(courseId){
    return request({
      url: `/eduorder/order/createOrder/${courseId}`,
      method: 'post'
    })
  },
  //根据订单id查询订单信息
  getOrderInfo(orderId){
    return request({
      url: `/eduorder/order/getOrderInfo/${orderId}`,
      method: 'get'
    })
  },
  //生成二维码的方法
  getNative(orderNo){
    return request({
      url: `/eduorder/paylog/createNative/${orderNo}`,
      method: 'get'
    })
  },

  //查询支付状态
  getPlayStatus(orderNo){
    return request({
      url: `/eduorder/paylog/queryPlayStatus/${orderNo}`,
      method: 'get'
    })
  },
}
