import request from '@/utils/request'

export function page(pageParam) {
  return request({
    url: '/anyard-of-jiangnan-order/m/order/refund/page',
    method: 'get',
    params: pageParam
  })
}
