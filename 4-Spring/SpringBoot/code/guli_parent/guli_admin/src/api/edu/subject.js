import request from '@/utils/request'

const api_name = 'edu/subject'

export default {
  getSubjectTree() {
    return request({
      url: `${api_name}/get-subject-tree`,
      method: 'get'
    })
  }
}
