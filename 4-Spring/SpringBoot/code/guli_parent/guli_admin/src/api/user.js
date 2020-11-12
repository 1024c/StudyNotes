import request from '@/utils/request'

var base_url = '/edu/user'

export function login(data) {
  return request({
    url: `${base_url}/login`,
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: `${base_url}/info`,
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: `${base_url}/logout`,
    method: 'post'
  })
}
