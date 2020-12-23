import axios from 'axios'

// 创建 axios 实例
const request = axios.create({
  baseURL: 'http://localhost:9001', // api 的 base_url
  timeout: 20000 // 请求超时时间
})

export default request
