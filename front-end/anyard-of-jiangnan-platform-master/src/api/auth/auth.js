import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/anyard-of-jiangnan-auth/ua/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/anyard-of-jiangnan-auth/login_out',
    method: 'post'
  })
}
