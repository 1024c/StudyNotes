import request from '@/utils/request'

const BASE_API = '/cms/front/crm-banner'

export default {
  getBannerList() {
    return request({
      url: `${BASE_API}/getAllBanner`,
      method: 'get'
    })
  }
}
