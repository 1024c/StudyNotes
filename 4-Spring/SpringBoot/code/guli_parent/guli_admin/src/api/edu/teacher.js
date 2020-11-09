import request from '@/utils/request'

// const api_name = '/admin/edu/teacher'
const api_name = '/vue-admin-template/edu/teacher'

export function getPageList(page, limit, searchObj) {
  return request({
    url: `${api_name}/${page}/${limit}`,
    method: 'get',
    data: searchObj
  })
}

export function getList() {
  return request({
    url: `/vue-admin-template/edu/teacher`,
    method: 'get',
  })
}
