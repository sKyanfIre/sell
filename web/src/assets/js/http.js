import axios from 'axios'
import router from '@/router'
const baseUrl = process.env.BASE_URL
axios.defaults.headers = {'Content-Type': 'application/json;charset=utf-8'}
axios.defaults.withCredentials = true
axios.defaults.timeout = 50000

// request拦截器
axios.interceptors.request.use(
  config => {
    config.data = JSON.stringify(config.data)
    return config
  },
  error => {
    return Promise.reject(error)
  }
)
// response拦截器
axios.interceptors.response.use(
  res => {
    const httpCode = Number(res.status)
    const respCode = res.data.RespCode
    if (httpCode !== 200) {
      return Promise.reject(new Error('系统错误，请稍后重试'))
    }
    if (respCode === '0003') {
      alert('用户尚未登录')
      // 跳转到登录页面
      router.push({
        path: '/login22',
        querry: {redirect: router.currentRoute.fullPath}// 从哪个页面跳转
      })
    }
    if (respCode === '0005') {
      alert('用户无访问权限')
    }
    return res
  },
  error => {
    return Promise.reject(error)
  }
)
// 封装get请求
export function get (url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.get(baseUrl + url, {
      params: params
    })
      .then(response => {
        resolve(response.data)
      }, err => {
        reject(err)
      })
  })
}
// 封装post请求
export function post (url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(baseUrl + url, data)
      .then(response => {
        resolve(response.data)
      }, err => {
        reject(err)
      })
  })
}
