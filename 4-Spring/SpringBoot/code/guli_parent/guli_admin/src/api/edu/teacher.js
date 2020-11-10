import request from '@/utils/request'

const api_name_all = 'edu/edu-teacher/select-all'
const api_name_all_page = 'edu/edu-teacher/select-all-with-page'
// const api_name = '/vue-admin-template/edu/teacher'

export function getPageList(page, limit, searchObj) {
  return request({
    url: `${api_name_all_page}?page=${page}&limit=${limit}`,
    method: 'post',
    data: searchObj
  })
}

export function getList() {
  return request({
    url: api_name_all,
    method: 'get'
  })
}

export function deleteById(id) {
  return request({
    url: `delete/${id}`,
    method: 'delete'
  })
}
