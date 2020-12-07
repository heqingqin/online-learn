import request from '@/utils/request'
export default {
  //登录
  submitLogin(userInfo) {
    return request({
      url: '/educenter/member/loginUser',
      method: 'post',
      data: userInfo
    })
  },
  //根据token获取用户信息
  getLoginInfo() {
    return request({
      url: '/educenter/member/getMemberInfo',
      method: 'get',
      // headers: {'token': cookie.get('guli_token')}
    })
    //headers: {'token': cookie.get('guli_token')}
  },
  //微信登录
  WxLogin() {
    return request({
      url: '/api/ucenter/wx/login',
      method: 'get'
    })
  }
}
