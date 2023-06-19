import request from '@/utils/request'

export function page(pageParam) {
  return request({
    url: '/anyard-of-jiangnan-rbac/role/page',
    method: 'get',
    params: pageParam
  })
}

export function list() {
  return request({
    url: '/anyard-of-jiangnan-rbac/role/list',
    method: 'get'
  })
}

export function get(roleId) {
  return request({
    url: '/anyard-of-jiangnan-rbac/role',
    method: 'get',
    params: {
      roleId
    }
  })
}

export function save(data) {
  return request({
    url: '/anyard-of-jiangnan-rbac/role',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/anyard-of-jiangnan-rbac/role',
    method: 'put',
    data
  })
}

export function deleteById(roleId) {
  return request({
    url: '/anyard-of-jiangnan-rbac/role',
    method: 'delete',
    params: {
      roleId
    }
  })
}
