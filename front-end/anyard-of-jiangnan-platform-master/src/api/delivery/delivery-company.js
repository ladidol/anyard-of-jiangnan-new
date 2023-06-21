import request from '@/utils/request'

export function list() {
  return request({
    url: '/anyard-of-jiangnan-delivery/p/delivery_company/list',
    method: 'get'
  })
}

export function get(deliveryCompanyId) {
  return request({
    url: '/anyard-of-jiangnan-delivery/p/delivery_company',
    method: 'get',
    params: {
      deliveryCompanyId
    }
  })
}

export function save(data) {
  return request({
    url: '/anyard-of-jiangnan-delivery/p/delivery_company',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/anyard-of-jiangnan-delivery/p/delivery_company',
    method: 'put',
    data
  })
}

export function deleteById(deliveryCompanyId) {
  return request({
    url: '/anyard-of-jiangnan-delivery/p/delivery_company',
    method: 'delete',
    params: {
      deliveryCompanyId
    }
  })
}
