import request from '@/utils/request'

const BASE_API = '/edu/front/index'

export default {
  getIndex() {
    return request({
      url: `${BASE_API}/index`,
      method: 'get'
    })
  }
}
