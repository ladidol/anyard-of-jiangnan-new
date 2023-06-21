import request from '@/utils/request'

export function list() {
  return request({
    url: '/anyard-of-jiangnan-delivery/m/delivery_company/list',
    method: 'get'
  })
}

