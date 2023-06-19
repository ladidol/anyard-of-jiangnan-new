import request from '@/utils/request'

export function page(pageParam) {
  return request({
    url: '/anyard-of-jiangnan-product/m/spu_detail/page',
    method: 'get',
    params: pageParam
  })
}

export function get(spuId) {
  return request({
    url: '/anyard-of-jiangnan-product/m/spu_detail',
    method: 'get',
    params: {
      spuId
    }
  })
}

export function save(data) {
  return request({
    url: '/anyard-of-jiangnan-product/m/spu_detail',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/anyard-of-jiangnan-product/m/spu_detail',
    method: 'put',
    data
  })
}

export function deleteById(spuId) {
  return request({
    url: '/anyard-of-jiangnan-product/m/spu_detail',
    method: 'delete',
    params: {
      spuId
    }
  })
}
