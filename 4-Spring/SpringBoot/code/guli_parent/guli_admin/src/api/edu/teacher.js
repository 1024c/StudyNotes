import request from '@/utils/request'

const api_name = 'edu/teacher'

export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/select-all?page=${page}&limit=${limit}`,
      method: 'post',
      data: searchObj
    })
  },

  getList() {
    return request({
      url: `${api_name}/select-all`,
      method: 'get'
    })
  },

  deleteById(id) {
    return request({
      url: `${api_name}/delete/${id}`,
      method: 'delete'
    })
  },

  save(teacher) {
    return request({
      url: `${api_name}/save`,
      method: 'post',
      data: teacher
    })
  },

  // 编辑界面回显
  getById(id) {
    return request({
      url: `${api_name}/select/${id}`,
      method: 'get'
    })
  },

  // 更新保存
  update(teacher) {
    return request({
      url: `${api_name}/update`,
      method: 'put',
      data: teacher
    })
  }
}
