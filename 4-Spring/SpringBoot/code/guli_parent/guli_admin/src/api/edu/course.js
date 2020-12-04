import request from '@/utils/request'

const api_name = '/edu/course'

export default {
  saveCourseInfo(courseInfo) {
    return request({
      url: `${api_name}/save-course-info`,
      method: 'post',
      data: courseInfo
    })
  },

  getCourseInfo(id) {
    return request({
      url: `${api_name}/course-info/${id}`,
      method: 'get'
    })
  },

  updateCourseInfo(courseInfo) {
    return request ({
      url: `${api_name}/update-course-info/${courseInfo.id}`,
      method: 'put',
      data: courseInfo
    })
  }
}
