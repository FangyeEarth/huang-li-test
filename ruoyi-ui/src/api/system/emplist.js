import request from '@/utils/request'

// 查询信息列表
export function listStudent(query) {
  return request({
    url: '/system/employee/list',
    method: 'get',
    params: query
  })
}

// 查询信息详细
export function getStudent(studentId) {
  return request({
    url: '/system/employee/' + studentId,
    method: 'get'
  })
}

// 新增信息
export function addStudent(data) {
  return request({
    url: '/system/employee',
    method: 'post',
    data: data
  })
}

// 修改信息
export function updateStudent(data) {
  return request({
    url: '/system/employee',
    method: 'put',
    data: data
  })
}

// 删除信息
export function delStudent(studentId) {
  return request({
    url: '/system/employee/' + studentId,
    method: 'delete'
  })
}


