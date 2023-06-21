import request from '@/utils/request'

export function page(pageParam) {
  return request({
    url: '/anyard-of-jiangnan-discount/m/discount/page',
    method: 'get',
    params: pageParam
  })
}

export function get(discountId) {
  return request({
    url: `/anyard-of-jiangnan-discount/m/discount/info/${discountId}`,
    method: 'get',
    params: {
    }
  })
}

export function save(data) {
  return request({
    url: '/anyard-of-jiangnan-discount/m/discount',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/anyard-of-jiangnan-discount/m/discount',
    method: 'put',
    data
  })
}

export function deleteById(discountId) {
  return request({
    url: `/anyard-of-jiangnan-discount/m/discount/${discountId}`,
    method: 'delete',
    params: {
    }
  })
}
